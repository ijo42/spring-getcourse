package ru.ijo42.springdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ijo42.springdemo.service.SalesJdbcService;
import ru.ijo42.springdemo.service.SalesJpaService;
import ru.ijo42.springdemo.entity.Sale;
import ru.ijo42.springdemo.entity.SaleDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SalesJpaService jpaService;
    private final SalesJdbcService jdbcService;

    public SaleController(SalesJpaService jpaService, SalesJdbcService jdbcService) {
        this.jpaService = jpaService;
        this.jdbcService = jdbcService;
    }

    @PostMapping("/jpa")
    public ResponseEntity<Sale> createSaleJPA(@RequestBody SaleDto saleDto) {
        Sale savedSale = jpaService.saveSale(saleDto);
        return ResponseEntity.ok(savedSale);
    }

    @PostMapping("/jdbc")
    public ResponseEntity<Sale> createSaleJDBC(@RequestBody SaleDto saleDto) {
        Sale savedSale = jdbcService.saveSale(saleDto);
        return ResponseEntity.ok(savedSale);
    }

    @GetMapping("/jdbc/count")
    public ResponseEntity<Long> getJdbcSalesCount() {
        long count = jdbcService.getSalesCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/jdbc/{id}")
    public ResponseEntity<Sale> getJdbcSaleById(@PathVariable Long id) {
        Optional<Sale> sale = jdbcService.getSaleById(id);
        return sale.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/jdbc/amount-greater-than-100")
    public ResponseEntity<List<Sale>> getJdbcSalesWithAmountGreaterThan100() {
        List<Sale> sales = jdbcService.getSalesWithAmountGreaterThan100();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/jpa/count")
    public ResponseEntity<Long> getJpaSalesCount() {
        long count = jpaService.getSalesCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<Sale> getJpaSaleById(@PathVariable Long id) {
        Optional<Sale> sale = jpaService.getSaleById(id);
        return sale.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/jpa/amount-greater-than-100")
    public ResponseEntity<List<Sale>> getJpaSalesWithAmountGreaterThan100() {
        List<Sale> sales = jpaService.getSalesWithAmountGreaterThan100();
        return ResponseEntity.ok(sales);
    }
}