package ru.ijo42.springdemo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleDto {

    private BigDecimal amount;
    private LocalDate receiptDate;
    private LocalDate saleDate;
    private Long productId;

    // Getters and setters
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDate getReceiptDate() { return receiptDate; }
    public void setReceiptDate(LocalDate receiptDate) { this.receiptDate = receiptDate; }
    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
}