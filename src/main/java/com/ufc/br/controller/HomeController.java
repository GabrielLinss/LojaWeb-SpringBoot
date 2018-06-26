package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Produto;
import com.ufc.br.service.ProdutoService;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/")
	public ModelAndView listarProdutos() {
		List<Produto> produtos = produtoService.listar();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("todosOsProdutos", produtos);
		
		return mv;
	}
	
	@RequestMapping("/sobre")
	public String sobre() {
		return "sobre";
	}
}
