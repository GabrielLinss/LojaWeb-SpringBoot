package com.ufc.br.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Compra;
import com.ufc.br.repository.CompraRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	public void salvarCompra(Compra compra) {
		compraRepository.save(compra);
	}
	
	public Compra buscarPorId(Long id) {
		return compraRepository.getOne(id);
	}
	
	public List<Compra> buscarPorIdCliente(Long idCliente){
		return compraRepository.findByIdCliente(idCliente);
	}
}
