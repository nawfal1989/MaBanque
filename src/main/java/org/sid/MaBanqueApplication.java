package org.sid;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.*;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.time.LocalDateTime;


@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MaBanqueApplication.class, args);
//		ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
//		clientRepository.save(new Client("hassan","hassan@gmail.com"));
	}

	@Override
	public void run(String... arg0) throws Exception {
		/*
		Client c1 = clientRepository.save(new Client("hassan","hassan@gmail.com"));
		Client c2 = clientRepository.save(new Client("imane","imane@gmail.com"));
		
		Compte cp1 = compteRepository.save(new CompteCourant("c1", LocalDateTime.now(), 9000, c1, 600));
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", LocalDateTime.now(), 6000, c2, 2.5));

		operationRepository.save(new Versement(LocalDateTime.now(), 9000, cp1));
		operationRepository.save(new Versement(LocalDateTime.now(), 6000, cp1));
		operationRepository.save(new Versement(LocalDateTime.now(), 2000, cp1));
		operationRepository.save(new Retrait(LocalDateTime.now(), 9000, cp1));
		
		operationRepository.save(new Versement(LocalDateTime.now(), 2300, cp2));
		operationRepository.save(new Versement(LocalDateTime.now(), 5000, cp2));
		operationRepository.save(new Versement(LocalDateTime.now(), 1500, cp2));
		operationRepository.save(new Retrait(LocalDateTime.now(), 7000, cp2));
		
		banqueMetier.verser("c1", 11111111);*/
	}
}
