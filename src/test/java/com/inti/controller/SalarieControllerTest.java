package com.inti.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Salarie;
import com.inti.repository.ISalarieRepository;

@WebMvcTest(SalarieController.class)
public class SalarieControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private ISalarieRepository isr;
	
	@Test
	@DisplayName("Test d'inscription d'un salarié")
	public void inscriptionSalarie() throws Exception
	{
		mock.perform(get("/inscription"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test d'enregistrement d'un salarié")
	public void enregistrementSalarie() throws Exception
	{
		mock.perform(post("/inscription").sessionAttr("salarie", new Salarie("Dupont", "Jean", "test@tst.fr")))
		.andExpect(status().is3xxRedirection())
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test d'affichage de tous les salariés")
	public void getAllSalarie() throws Exception
	{
		mock.perform(get("/listeSalarie"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Email")))
		.andDo(print());
	}
	
	@Test
	@DisplayName("Test de suppression d'un salarié")
	public void deleteSalarie() throws Exception
	{
		mock.perform(get("/deleteSalarie/2"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeSalarie"))
		.andDo(print());
	}
	
	// Ecrire les autres tests
}
