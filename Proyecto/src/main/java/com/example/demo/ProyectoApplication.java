package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.Usuario;
import com.example.demo.servicios.CuentaBancariaServiceI;
import com.example.demo.servicios.UsuarioServicel;


@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner{

	@Autowired
	private UsuarioServicel usuarioServicel;
	@Autowired
	private CuentaBancariaServiceI cuentasService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		
		Usuario usu = new Usuario();
		usu.setNif("91283812as");
		usu.setEmail("victormfdezfdez@gmail.com");	
		usu.setNombre("VictorM");
		usu.setTlf("955666333");
		usu.setApellidos("asdasdas");
		
		usuarioServicel.addUser(usu);
	
		

	}

}
