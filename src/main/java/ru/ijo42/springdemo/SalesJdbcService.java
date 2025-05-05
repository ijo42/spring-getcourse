package ru.ijo42.springdemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesJdbcService {

    private final JdbcTemplate jdbcTemplate;

    public SalesJdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long getSalesCount() {
        String sql = "SELECT COUNT(*) FROM sales";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Optional<Sale> getSaleById(Long id) {
        String sql = "SELECT id, amount, receipt_date, sale_date, product_id FROM sales WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getLong("id"));
                sale.setAmount(rs.getBigDecimal("amount"));
                sale.setReceiptDate(rs.getDate("receipt_date"));
                sale.setSaleDate(rs.getDate("sale_date"));
                sale.setProductId(rs.getInt("product_id"));
                return Optional.of(sale);
            }
            return Optional.empty();
        });
    }

    public List<Sale> getSalesWithAmountGreaterThan100() {
        String sql = "SELECT id, amount, receipt_date, sale_date, product_id FROM sales WHERE amount > 100";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Sale sale = new Sale();
            sale.setId(rs.getLong("id"));
            sale.setAmount(rs.getBigDecimal("amount"));
            sale.setReceiptDate(rs.getDate("receipt_date"));
            sale.setSaleDate(rs.getDate("sale_date"));
            sale.setProductId(rs.getInt("product_id"));
            return sale;
        });
    }

    public static class Sale {
        private Long id;
        private BigDecimal amount;
        private Date receiptDate;
        private Date saleDate;
        private Integer productId;

        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public Date getReceiptDate() { return receiptDate; }
        public void setReceiptDate(Date receiptDate) { this.receiptDate = receiptDate; }
        public Date getSaleDate() { return saleDate; }
        public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }
        public Integer getProductId() { return productId; }
        public void setProductId(Integer productId) { this.productId = productId; }

        @Override
        public String toString() {
            return "Sale{" +
                    "id=" + id +
                    ", amount=" + amount +
                    ", receiptDate=" + receiptDate +
                    ", saleDate=" + saleDate +
                    ", productId=" + productId +
                    '}';
        }
    }
}