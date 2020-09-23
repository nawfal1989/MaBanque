package org.sid.web;

import org.sid.entities.*;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;

	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}

	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte,
			@RequestParam(name="page", defaultValue = "0" )int page,
			@RequestParam(name="size", defaultValue = "5" )int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte compte = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperations = banqueMetier.listOperations(codeCompte, page, size);
			int[] pages = new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("compte", compte);
			model.addAttribute("listOperations", pageOperations.getContent());
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "comptes";
	}

	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String save(Model model, @RequestParam(name = "typeOperation") String typeOperation,
			@RequestParam(name = "codeCompte") String codeCompte, @RequestParam(name = "montant") double montant,
			@RequestParam(name = "codeCompte2") String codeCompte2) {

		try {
			switch (typeOperation) {
			case "VERS":
				banqueMetier.verser(codeCompte, montant);
				break;

			case "RETR":
				banqueMetier.retirer(codeCompte, montant);
				break;

			case "VIR":
				banqueMetier.virement(codeCompte, codeCompte2, montant);
				break;
			}
		} catch (Exception e) {
				model.addAttribute("error", e);
				return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}

		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
}
