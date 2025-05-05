CREATE TABLE sales (
                       id SERIAL PRIMARY KEY,
                       amount DECIMAL(10, 2) NOT NULL,
                       receipt_date DATE NOT NULL,
                       sale_date DATE NOT NULL,
                       product_id INTEGER NOT NULL
);

INSERT INTO sales (amount, receipt_date, sale_date, product_id) VALUES
                                                                    (100.50, '2025-01-01', '2025-01-05', 1),
                                                                    (200.75, '2025-02-01', '2025-02-10', 2),
                                                                    (150.00, '2025-03-01', '2025-03-15', 3);