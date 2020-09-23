package org.sid.metier;

import org.sid.entities.*;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	/*
	 * Les cas d'utilisations de l'application sont traduites par l'interface de la
	 * couche metier
	 */
	public Compte consulterCompte(String codeCpte);

	public void verser(String codeCpte, double montant);

	public void retirer(String codeCpte, double montant);

	public void virement(String codeCpte1, String codeCpte2, double montant);

	public Page<Operation> listOperations(String codeCpte1, int page, int size);
}
