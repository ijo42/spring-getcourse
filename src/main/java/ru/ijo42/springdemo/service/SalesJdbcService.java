package ru.ijo42.springdemo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.ijo42.springdemo.entity.Sale;
import ru.ijo42.springdemo.entity.SaleDto;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
                sale.setReceiptDate(rs.getDate("receipt_date").toLocalDate());
                sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
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
            sale.setReceiptDate(rs.getDate("receipt_date").toLocalDate());
            sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
            sale.setProductId(rs.getInt("product_id"));
            return sale;
        });
    }

    public Sale saveSale(SaleDto saleDto) {
        String sql = "INSERT INTO sales (amount, receipt_date, sale_date, product_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, saleDto.getAmount());
            ps.setDate(2, java.sql.Date.valueOf(saleDto.getReceiptDate()));
            ps.setDate(3, java.sql.Date.valueOf(saleDto.getSaleDate()));
            ps.setLong(4, saleDto.getProductId());
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();

        Sale sale = new Sale(saleDto);
        sale.setId(generatedId);

        return sale;
    }

}