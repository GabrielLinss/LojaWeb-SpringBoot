package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvarUsuario(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
	}
	
	public List<Usuario> listarTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.getOne(id);
	}
	
	public void excluirUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
}
