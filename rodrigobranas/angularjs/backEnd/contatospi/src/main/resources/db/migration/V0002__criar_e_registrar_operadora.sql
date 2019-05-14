CREATE TABLE tb_operadora(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigooperadora VARCHAR(2) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	valorporminuto DECIMAL(5,2),
	codigooperadoratelefonecategoria BIGINT(20),
	constraint fk_operadoratelefonecategoria FOREIGN KEY(codigooperadoratelefonecategoria) REFERENCES tb_operadora_telefone_categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_operadora(codigooperadora,nome,valorporminuto,codigooperadoratelefonecategoria) values('14','Oi',2.00,1);
INSERT INTO tb_operadora(codigooperadora,nome,valorporminuto,codigooperadoratelefonecategoria) values('15','Vivo',1.00,1);
INSERT INTO tb_operadora(codigooperadora,nome,valorporminuto,codigooperadoratelefonecategoria) values('41','Tim',3.20,1);
INSERT INTO tb_operadora(codigooperadora,nome,valorporminuto,codigooperadoratelefonecategoria) values('25','GVT',1.70,2);
INSERT INTO tb_operadora(codigooperadora,nome,valorporminuto,codigooperadoratelefonecategoria) values('21','Embratel',2.51,2);



