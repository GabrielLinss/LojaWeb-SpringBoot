package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Produto;
import com.ufc.br.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping("/salvar")
	public ModelAndView salvarProduto(Produto produto, @RequestParam(value="imagem") MultipartFile imagem) {
		produtoService.salvarProduto(produto, imagem);
		ModelAndView mv = new ModelAndView("redirect:/produto/produtosCadastrados");
		
		return mv;
	}
	
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscarProduto(@PathVariable Long id) {
		Produto produto = produtoService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("produto");
		mv.addObject("produto", produto);
		
		return mv;
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluirProduto(@PathVariable Long id) {
		produtoService.excluirPorId(id);
		ModelAndView mv = new ModelAndView("redirect:/produto/produtosCadastrados");
		
		return mv;
	}
	
	@RequestMapping("/cadastrar")
	public String formularioProduto() {
		return "cadastrar-produto";
	}
	
	@GetMapping("/produtosCadastrados")
	public ModelAndView listarTodosProdutos() {
		List<Produto> produtos = produtoService.listar();
		ModelAndView mv = new ModelAndView("lista-produtos");
		mv.addObject("todosOsProdutos", produtos);
		
		return mv;
	}
}
