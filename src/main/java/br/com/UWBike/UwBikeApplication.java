package br.com.UWBike;

import br.com.UWBike.service.FuncionarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UwBikeApplication {

	public static void main(String[] args) {

		SpringApplication.run(UwBikeApplication.class, args);
	}


		@Bean
		public CommandLineRunner runner(FuncionarioService funcionarioService) {
			return args -> {

				funcionarioService.atualizarSenhasParaBCrypt();
			};
		}
}




