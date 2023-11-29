CREATE TABLE IF NOT EXISTS produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(50),
    preco_custo REAL,
    preco_venda REAL,
    estoque INTEGER,
    fabricante VARCHAR(40)
);