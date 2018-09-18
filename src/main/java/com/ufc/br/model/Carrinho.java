package com.ufc.br.model;

import java.util.List;

public class Carrinho {
	private List<ItemCarrinho> itens;

	public Carrinho() {
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}
}
