package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario getByNif(String nif);
	
	//List<Usuario> findById(long id);
	
	List<Usuario> findByNifLike(String nif);
	
	List<Usuario> findByNombreLike(String nombre);	
	
	
	
}
