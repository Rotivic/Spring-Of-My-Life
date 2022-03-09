package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Usuario;
import com.example.demo.repositorios.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioServicel{

	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@Override
	public List<Usuario> findAllUsers() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findUserById(long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).get();
	}

	
	@Override
	public Usuario getUserByNif(String nif) {
		// TODO Auto-generated method stub
		return usuarioRepository.getByNif(nif);
	}

	@Override
	public List<Usuario> findUserByNifLike(String nif) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByNifLike(nif);
	}

	@Override
	public List<Usuario> findUserByNombreLike(String name) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByNombreLike(name);
	}

	@Override
	public void addUser(Usuario c) {
		// TODO Auto-generated method stub
		usuarioRepository.save(c);
	}

	@Override
	public void removeUserById(long IdUsuario) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(IdUsuario);
	}

	@Override
	public void updateUser(Usuario c) {
		// TODO Auto-generated method stub
		usuarioRepository.save(c);
	}

	
}
