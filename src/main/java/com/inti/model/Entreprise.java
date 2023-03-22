package com.inti.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Entreprise {
	private int id;
	private String nom;
	private String adr;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Salarie> listSalarie;

}
