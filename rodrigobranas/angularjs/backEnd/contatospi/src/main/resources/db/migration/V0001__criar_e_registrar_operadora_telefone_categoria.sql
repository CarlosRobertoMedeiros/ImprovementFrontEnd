CREATE TABLE tb_operadora_telefone_categoria(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_operadora_telefone_categoria(nome) values('Celular');
INSERT INTO tb_operadora_telefone_categoria(nome) values('Fixo');