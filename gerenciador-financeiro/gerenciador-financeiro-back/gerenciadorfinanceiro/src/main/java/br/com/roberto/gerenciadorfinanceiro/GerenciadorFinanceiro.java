package br.com.roberto.gerenciadorfinanceiro;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorFinanceiro {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorFinanceiro.class, args);
	}

	// Ajusta o TimeZone para UTC
	// Todos os campos LocalDate ou LocalDateTime vão ser modificados
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	
	//TODO:Aplicando a liberação de CrossOrigin a todo o projeto
	//Ele ainda não está legal ao usar oauth2 por isso está desabilitado
	//Por enquanto o melhor é criar um filtro para implementar o cors 
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:8000");
            }
        };
    }*/
	
	
}

// Verificar a dúvida no vídeo da aula 3.10
// Continuar do 5.3. Desafio: Cadastrando o primeiro lançamento
// O Código está implementado, porém não estou sabendo fazer o post corretamente
// Continuar no 6.12 - Adicionando Permissões de Acesso

//Utilizando OAuth2
    // 	user
	// client
    // autorization server
	//	    - Envia o Token para o client

    // resource server
    // 		- Guarda as funcionalidades do sistema(lancamentos,categorias,pessoas)
// Links para leitura: https://www.digitalocean.com/community/tutorials?q=Oauth2
//