package com.ufc.br.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.PersistenceConstructor;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCompra;
	
	private Long idCliente;
	private Long idProduto;
	private Date dataCompra;
	private Double total;
	
	public Compra() {
	}
	
	@PersistenceConstructor
	public Compra(Long idCliente, Long idProduto, Date dataCompra, Double total) {
		super();
		this.idCliente = idCliente;
		this.idProduto = idProduto;
		this.dataCompra = dataCompra;
		this.total = total;
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
