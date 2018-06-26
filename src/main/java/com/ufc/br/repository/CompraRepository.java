package com.ufc.br.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	List<Compra> findByIdCliente(Long idCliente);
}
