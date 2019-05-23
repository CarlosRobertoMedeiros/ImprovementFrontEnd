CREATE TABLE tb_aluno(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	numeropassaporte VARCHAR(25),
	datacadastro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into tb_aluno(nome,numeropassaporte) values('Carlos Roberto','A1234');
insert into tb_aluno(nome,numeropassaporte) values('Luciene Alves','A4321');