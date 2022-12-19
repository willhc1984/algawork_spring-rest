package com.algaworks.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.algaworks.springrest.repositories.ClienteRepository;
import com.algaworks.springrest.repositories.EntregaRepository;

@SpringBootApplication
public class SemanaSpringrestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SemanaSpringrestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Cliente cliente1 = new Cliente(1L, "José da Silva", "jose@ig.com.br", "1565874587");
		//Cliente cliente2 = new Cliente(2L, "Maria da Silva", "maria@ig.com.br", "1598745236");
		//Cliente cliente3 = new Cliente(3L, "João Pereira", "joaoa@hotmail.com.br", "1545876325");
		//Cliente cliente4 = new Cliente(4L, "Marcia Antunes", "marcia@microsoft.com.br", "157458962");
		
		//clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4));
		
		//Destinatario dest1 = new Destinatario("josé", "rua 15", "39", "casa", "jd tatiana");
		//Destinatario dest2 = new Destinatario("maria", "rua 15", "39", "casa", "jd tatiana");
		
		//Entrega entrega1 = new Entrega(1L, new BigDecimal(50.5), StatusEntrega.PENDENTE, 
				//LocalDateTime.now(), 
				//LocalDateTime.now(), cliente4, dest2);
		
		//entregaRepository.save(entrega1);
		
	}

}
