package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Produto;
import com.ufc.br.repository.ProdutoRepository;
import com.ufc.br.util.ProdFileUtils;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void salvarProduto(Produto produto, MultipartFile imagem) {
		String caminho = "img/" + produto.getNome() + ".jpg";
		ProdFileUtils.salvarImagem(caminho, imagem);
		produtoRepository.save(produto);
	}
	
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	public Produto buscarPorId(Long id) {
		return produtoRepository.getOne(id);
	}
	
	public void excluirPorId(Long id) {
		produtoRepository.deleteById(id);
	}
}
