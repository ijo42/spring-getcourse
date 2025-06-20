package ru.ijo42.springdemo.service;

import org.springframework.stereotype.Service;
import ru.ijo42.springdemo.entity.Sale;
import ru.ijo42.springdemo.entity.SaleDto;
import ru.ijo42.springdemo.repository.SaleRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SalesJpaService {

    private final SaleRepository saleRepository;

    public SalesJpaService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public long getSalesCount() {
        return saleRepository.count();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale saveSale(SaleDto saleDto) {
        var sale = new Sale(saleDto);
        return saleRepository.save(sale);
    }

    public List<Sale> getSalesWithAmountGreaterThan100() {
        return saleRepository.findByAmountGreaterThan(new BigDecimal("100"));
    }
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }
}