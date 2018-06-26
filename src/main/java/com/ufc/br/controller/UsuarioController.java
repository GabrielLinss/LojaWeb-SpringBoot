package com.ufc.br.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Compra;
import com.ufc.br.model.Produto;
import com.ufc.br.model.Usuario;
import com.ufc.br.service.CompraService;
import com.ufc.br.service.ProdutoService;
import com.ufc.br.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	//lista temporaria de compras
	List<Produto> produtos = new ArrayList<Produto>();
	Double total = 0.0;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping("/formulario")
	public ModelAndView formularioUsuario() {
		ModelAndView mv = new ModelAndView("cadastrar-usuario");
		mv.addObject("usuario", new Usuario());
		
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarUsuario(Usuario usuario) {
		usuarioService.salvarUsuario(usuario);
		ModelAndView mv = new ModelAndView("redirect:/usuario/login");
		
		return mv;
	}
	
	@RequestMapping("/comprar/{id}")
	public ModelAndView adicionarAoCarrinho(@PathVariable Long id) {
		Produto produto = produtoService.buscarPorId(id);
		produtos.add(produto);
		total += produto.getPreco();
		
		ModelAndView mv = new ModelAndView("carrinho");
		mv.addObject("produtosDoCarrinho", produtos);
		mv.addObject("total", total);
		
		return mv;
	}
	
	@RequestMapping("/finalizarCompra")
	public ModelAndView finalizarCompra() {
		
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Usuario usuario = usuarioService.buscarPorLogin(user.getUsername());
		
		for (Produto produto : produtos) {
			Long idProduto = produto.getId();
			Double totalCompra = produto.getPreco();
			Long idCliente = usuario.getId();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date dataCompra = new Date();
			dateFormat.format(dataCompra);
			
			Compra compra = new Compra(idCliente,idProduto,dataCompra,totalCompra);
			compraService.salvarCompra(compra);
			
		}
		
		produtos.clear();
		total = 0.0;
		ModelAndView mv = new ModelAndView("redirect:/usuario/historico");
		
		return mv;
	}
	
	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		Produto produto = produtoService.buscarPorId(11L);
		
		ModelAndView mv = new ModelAndView("carrinho");
		mv.addObject("produtosDoCarrinho", produtos);
		mv.addObject("total", total);
		
		return mv;
	}
	
	@RequestMapping("/login")
	public String logar() {
		return "login";
	}
	
	
	@RequestMapping(value = "/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    produtos.clear();
	    
	    return "redirect:/";
	
	}
	
	@RequestMapping("/historico")
	public ModelAndView produtosComprados() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Usuario usuario = usuarioService.buscarPorLogin(user.getUsername());
		
		List<Compra> compras = compraService.buscarPorIdCliente(usuario.getId());
		
		ModelAndView mv = new ModelAndView("produtos-comprados");
		mv.addObject("todasAsCompras", compras);
		
		return mv;
	}
	
	@RequestMapping("/removerProduto/{id}")
	public ModelAndView removerProdutoCarrinho(@PathVariable Long id) {
		for(Produto produto : produtos) {
			if(produto.getId() == id) {
				total -= produto.getPreco();
				produtos.remove(produto);
				ModelAndView mv = new ModelAndView("redirect:/usuario/carrinho");
				//mv.addObject("produtosDoCarrinho", produtos);
				//mv.addObject("total", total);
				
				return mv;
			}
		}
		
		return null;
	}
}
