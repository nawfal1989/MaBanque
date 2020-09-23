package org.sid.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//ajout d'une colonne typeCompte
@DiscriminatorColumn(name="TYPE_CPTE", 
	discriminatorType = DiscriminatorType.STRING,
	length = 2)
public abstract class Compte implements Serializable {
	@Id
	@Column(length = 15)
	private String codeCompte;
	private LocalDateTime dateCreation;
	private double solde;
	@ManyToOne
	// definir la clé étranger
	@JoinColumn(name="CODE_CLI")
	private Client client;
	
	@OneToMany(mappedBy = "compte", fetch=FetchType.LAZY)
	private Collection<Operation> operations;

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte(String codeCompte, LocalDateTime dateCreation, double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}

	public String getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

}
