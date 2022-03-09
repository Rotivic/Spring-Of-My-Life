package com.example.demo.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioModelo;
import com.example.demo.servicios.CuentaBancariaServiceI;
import com.example.demo.servicios.CuentasUsuariosServiceI;
import com.example.demo.servicios.UsuarioServicel;

@Controller

public class UsuarioController {


	@Autowired
	private UsuarioServicel userService;
	@Autowired 
	private CuentaBancariaServiceI cuentaService;
	@Autowired 
	private CuentasUsuariosServiceI cuentaUSuService;
	
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	/**
	 * Carga la vista ClientesView con los datos del modelo cargados
	 * @param model Modelo
	 * @return Retorna la vista ClientesView 
	 */
	@GetMapping("/ClientView")
	public String mostrarUsuarios(Model model) {

		final List<Usuario> listaClientes = userService.findAllUsers();
		
		model.addAttribute("listadoClientes", listaClientes);
		
		return "Usuarios/ClientesView";
	}

	/**
	 * Carga los datos al modelo segun la id del usuario
	 * @param id Id del usuario
	 * @param model Modelo
	 * @return Retorna la vista editClient
	 * @throws Exception
	 */
	@GetMapping("/client/{id}")
	public String buscarUsuario(@PathVariable String id, Model model) throws Exception {

		Usuario a = userService.findUserById(Long.valueOf(id));
		
		
		model.addAttribute("cliente",a);
		
		return "Usuarios/editClient";
	}
	
	/**
	 * Nos manda a la vista addCliente
	 * @param model
	 * @return Retorna la vista addUser
	 */
	@GetMapping("/addClient")
	public String addUsuarios(Model model) {
		
		return "Usuarios/addUser";
	}
	
	/**
	 * Carga los datos en el modelo y nos manda a la vista
	 * @param model Modelo
	 * @return Retorna a la vista delAccountFromUser
	 */
	@GetMapping("/delAccountFromUser")
	public String delAcountFromUsuarios(Model model) {
		
		List<CuentaBancaria> cuentas = new ArrayList<>();
		List<Usuario> usuarios = userService.findAllUsers();
		
		model.addAttribute("accounts",cuentas);		
		model.addAttribute("usuarios",usuarios);
		
		return "Usuarios/delAccountFromUser";
	}
	
	/**
	 * Carga datos segund el id del cliente
	 * @param clientId Id del cliente
	 * @param model Modelo
	 * @return Retorna la vista delAccountFromUser con los datos cargados en el modelo
	 */
	@PostMapping("/gettingTheAccounts")
	public String gettingAccounts(@RequestParam String clientId, Model model) {

		List<CuentaBancaria> cuentas = cuentaService.findAllAccountsByUser(Float.parseFloat(clientId));
		List<Usuario> usuarios = userService.findAllUsers();
		
		model.addAttribute("accounts",cuentas);		
		model.addAttribute("usuarios",usuarios);

		return "Usuarios/delAccountFromUser";

	}
	
	/**
	 * Borra una cuenta, antes borrando la relacion en la tabla n:m
	 * @param clientId Id del cliente
	 * @param model Modelo
	 * @return Retorna a la vista index
	 */
	@PostMapping("/deletingTheAccounts")
	public String deletingAccounts(@RequestParam String clientId, Model model) {

		List<CuentaBancaria> cuentas = cuentaService.findAllAccountsByUser(Float.parseFloat(clientId));
		List<Usuario> usuarios = userService.findAllUsers();
		
		model.addAttribute("accounts",cuentas);		
		model.addAttribute("usuarios",usuarios);

	
		
		cuentaUSuService.deleteMiddleUser(Integer.parseInt(clientId));
		cuentaService.removeAccountById(Long.parseLong(clientId));
		
		
		
		return "redirect:index";

	}
	
	/**
	 * Borra un cliente por su Id
	 * @param clientId Id del cliente
	 * @param model Modelo
	 * @return Retorn a la vista ClientView
	 */
	@PostMapping("/actDropCar")
	public String eliminarCoche(@RequestParam String clientId, Model model) {

		
		userService.removeUserById(Long.valueOf(clientId));

		return "redirect:ClientView";

	}
	
	/**
	 * Añade un nuevo cliente
	 * @param newClient Cliente a añadir
	 * @param result Resutado del add
	 * @return Retorn a la vista ClientView
	 * @throws Exception
	 */
	@PostMapping("/addearClient")
	private String adearCliente(@Valid @ModelAttribute UsuarioModelo newClient, BindingResult result) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		Date date = formatter.parse(newClient.getAnioNac());
		
		Usuario e = new Usuario();
				
		e.setNombre(newClient.getNombre());
		e.setApellidos(newClient.getApellidos());
		e.setDireccion(newClient.getDireccion());
		e.setAnioNac(date);
		e.setEmail(newClient.getEmail());
		e.setTlf(newClient.getTlf());
		e.setNif(newClient.getNif());
		
		
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros de cliente erróneos");
		} else {
			
			userService.addUser(e);
		}

		return "redirect:ClientView";
	}
	
	/**
	 * Actualiza los datos del cliente
	 * @param updClient Cliente a actualizar
	 * @param result Resultado del update
	 * @return Retorna a la vista ClientView
	 * @throws Exception
	 */
	@PostMapping("/updateClient")
	private String updatearCliente(@Valid @ModelAttribute UsuarioModelo updClient, BindingResult result) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		Date date = formatter.parse(updClient.getAnioNac());
		
		Usuario e = new Usuario();
				
		e.setId(updClient.getId());
		e.setNombre(updClient.getNombre());
		e.setApellidos(updClient.getApellidos());
		e.setDireccion(updClient.getDireccion());
		e.setAnioNac(date);
		e.setEmail(updClient.getEmail());
		e.setTlf(updClient.getTlf());
		e.setNif(updClient.getNif());
		
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros de cliente erróneos");
		} else {
			
			userService.updateUser(e);
		}

		return "redirect:ClientView";
	}
	
	
	
	
}
