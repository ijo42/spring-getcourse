package ru.ijo42.springdemo.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class SaleDto {

    private BigDecimal amount;
    private Date receiptDate;
    private Date saleDate;
    private Integer productId;

    // Getters and setters
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Date getReceiptDate() { return receiptDate; }
    public void setReceiptDate(Date receiptDate) { this.receiptDate = receiptDate; }
    public Date getSaleDate() { return saleDate; }
    public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
}