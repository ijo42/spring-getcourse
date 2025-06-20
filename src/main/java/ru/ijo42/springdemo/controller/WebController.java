package ru.ijo42.springdemo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ijo42.springdemo.service.SalesJpaService;
import ru.ijo42.springdemo.entity.SaleDto;
import ru.ijo42.springdemo.entity.SellerDto;

@Controller
public class WebController {

    private final SalesJpaService jpaService;

    public WebController(SalesJpaService jpaService) {
        this.jpaService = jpaService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("seller", new SellerDto());
        return "index";
    }

    @PostMapping("/seller")
    public String submitSeller(@ModelAttribute SellerDto sellerDto, HttpSession session) {
        session.setAttribute("sellerName", sellerDto.getName());
        return "redirect:/sales";
    }

    @GetMapping("/sales")
    public String sales(Model model, HttpSession session) {
        String sellerName = (String) session.getAttribute("sellerName");
        model.addAttribute("sellerName", sellerName != null ? sellerName : "Unknown");
        model.addAttribute("sales", jpaService.getSalesWithAmountGreaterThan100());
        model.addAttribute("saleDto", new SaleDto());
        return "sales";
    }
}