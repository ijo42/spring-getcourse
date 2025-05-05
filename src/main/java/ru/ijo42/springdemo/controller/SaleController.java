package ru.ijo42.springdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ijo42.springdemo.SalesJdbcService;
import ru.ijo42.springdemo.SalesJpaService;
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

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setAmount(saleDto.getAmount());
        sale.setReceiptDate(saleDto.getReceiptDate());
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setProductId(saleDto.getProductId());

        Sale savedSale = jpaService.saveSale(sale);
        return ResponseEntity.ok(savedSale);
    }

    @GetMapping("/jdbc/count")
    public ResponseEntity<Long> getJdbcSalesCount() {
        long count = jdbcService.getSalesCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/jdbc/{id}")
    public ResponseEntity<SalesJdbcService.Sale> getJdbcSaleById(@PathVariable Long id) {
        Optional<SalesJdbcService.Sale> sale = jdbcService.getSaleById(id);
        return sale.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/jdbc/amount-greater-than-100")
    public ResponseEntity<List<SalesJdbcService.Sale>> getJdbcSalesWithAmountGreaterThan100() {
        List<SalesJdbcService.Sale> sales = jdbcService.getSalesWithAmountGreaterThan100();
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