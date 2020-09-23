package org.sid.metier;

import java.time.LocalDateTime;
import java.util.Optional;

import org.sid.dao.*;
import org.sid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		Optional<Compte> cp = compteRepository.findById(codeCpte);
		if(cp.isEmpty()) {
			throw new RuntimeException("Compte introuvable");
		}
		return cp.get();
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(LocalDateTime.now(), montant, cp);
		Versement vInserted = operationRepository.save(v);
		cp.setSolde(cp.getSolde() + vInserted.getMontant());
		compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp = consulterCompte(codeCpte);
		double facilitesDeCaisse=0;
		if(cp instanceof CompteCourant) {
			facilitesDeCaisse = ((CompteCourant) cp).getDecouvert();
		}
		if(cp.getSolde() + facilitesDeCaisse<montant) {
			throw new RuntimeException("Solde insuffisent");
		}
		Retrait r = new Retrait(LocalDateTime.now(), montant, cp);
		Retrait rInserted = operationRepository.save(r);
		cp.setSolde(cp.getSolde() - rInserted.getMontant());
		compteRepository.save(cp);

	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equals(codeCpte2)) {
			throw new RuntimeException("On ne peut pas effectuer un virement sur le même compte.");
		}
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);

	}

	@Override
	public Page<Operation> listOperations(String codeCpte1, int page, int size) {
		return operationRepository.listOperations(codeCpte1, PageRequest.of(page, size));
	}

}
