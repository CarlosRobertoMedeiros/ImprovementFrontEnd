package br.com.roberto.gerenciadorfinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorFinanceiro {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorFinanceiro.class, args);
	}

}

//Verificar a dúvida no ví 2deo da aula 3.10
//Iniciar o video 4.4. Implementando atualização parcial com PUT
//Tem que corrigir o seguinte erro
//  - Ao solicitar o put de um código inexistente gera exceção "java.util.NoSuchElementException: No value present"