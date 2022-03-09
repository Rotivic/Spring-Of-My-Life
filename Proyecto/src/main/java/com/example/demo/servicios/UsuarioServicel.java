package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Usuario;


public interface UsuarioServicel {
	public List<Usuario> findAllUsers();
	
	public Usuario getUserByNif(String nif);
	
	public Usuario findUserById(long id);
	
	public List<Usuario> findUserByNifLike(String nif);
	
	public List<Usuario> findUserByNombreLike(String name);
	
	public void addUser(Usuario c);
	
	public void removeUserById(long IdUsuario);
	
	public void updateUser(Usuario c);	
}
