package ru.ijo42.springdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ijo42.springdemo.entity.Sale;

import java.math.BigDecimal;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByAmountGreaterThan(BigDecimal amount);
}