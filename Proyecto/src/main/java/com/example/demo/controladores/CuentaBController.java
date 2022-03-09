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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.CuentaBancariaModelo;
import com.example.demo.entidades.Cuentas_Usuarios;
import com.example.demo.entidades.OperacionB;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioModelo;
import com.example.demo.servicios.CuentaBancariaServiceI;
import com.example.demo.servicios.CuentasUsuariosServiceI;
import com.example.demo.servicios.UsuarioServicel;

@Controller

public class CuentaBController {

	
	@Autowired
	private CuentaBancariaServiceI cuentas;
	@Autowired
	private UsuarioServicel userService;
	@Autowired
	private CuentasUsuariosServiceI cuentaService;

	@GetMapping("/AccountsView")
	public String mostrarCuentas(Model model) {
		
		final List<Usuario> usuarios = userService.findAllUsers();
		final List<CuentaBancaria> cuentasB = new ArrayList<>();
		
		model.addAttribute("users", usuarios);
		model.addAttribute("accounts", cuentasB);
			
		return "Cuentas/Accounts";
		
	}
	
	/**
	 * Redirige a la vista NewAccount con los datos de usuarios
	 * @param model Es el modelo con los datos necesarios
	 * @return Retorna la vista con los datos cargados
	 */
	
	@GetMapping("/addAccountUser")
	public String addCuentasUser(Model model) {
		
		final List<Usuario> usuarios = userService.findAllUsers();
		
		
		model.addAttribute("users", usuarios);
		
			
		return "Cuentas/NewAccount";
		
	}
	
	
	/**
	 * El metodo carga datos, los asigna al modelo y devuelve la vista
	 * @param model
	 * @return Retorna la vista deleteTitular
	 */
	@GetMapping("/delTitular")
	public String delTitular(Model model) {
		
		// 1.- Cargo las Accounts
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccounts();
		final List<Cuentas_Usuarios> usuarios = new ArrayList<>();
		model.addAttribute("cuentasB",cuentasB);
		model.addAttribute("titulares", usuarios);
		// 2.- Recogo la selected y encuentro sus titulares
		// 3.- Recogo la idAccount y la idUser y borro en la n:m
		// cuentaService.findAllTitulatesByAccountId(1);	
		return "Cuentas/deleteTitular";
	}
	
	/**
	 * El metodo carga datos, los asigna al modelo y devuelve la vista
	 * @param model
	 * @return Retorna la vista addTitular
	 */
	@GetMapping("/addTitular")
	public String addTitular(Model model) {
		
		// 1.- Cargo las Accounts
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccounts();
		final List<Usuario> usuarios =  userService.findAllUsers();
		model.addAttribute("cuentasB",cuentasB);
		model.addAttribute("titulares", usuarios);
		// 2.- Recogo la selected y encuentro sus titulares
		// 3.- Recogo la idAccount y la idUser y borro en la n:m
		// cuentaService.findAllTitulatesByAccountId(1);	
		return "Cuentas/addTitular";
	}
	
	/**
	 * Añade un nuevo titular
	 * @param id Id de la cuenta
	 * @param clientId Id del cliente
	 * @param model Modelo
	 * @return Retorna la vista index tras guardar
	 */
	@PostMapping("/addTitulares")
	public String addTitulares(@RequestParam String id, @RequestParam String clientId, Model model) {
		
		CuentaBancaria e  = cuentas.findAccountById(Long.parseLong(id));
	
	
		
		cuentas.insertNewAccountUser(Integer.parseInt(clientId), e);
		
		
		return "redirect:index";
	}
	
	/**
	 * Recibe una id y devuelve datos a una en función de la id que tome
	 * @param id: Id del cliente 
	 * @param model: modelo 
	 * @return Retorna la vista con los datos del modelo cargados en función de la id que le llega
	 */
	@PostMapping("/findTitulares")
	public String mostrarTitulares(@RequestParam String id, Model model) {
		
		final List<Cuentas_Usuarios> usuarios = cuentaService.findAllTitulatesByAccountId(Float.parseFloat(id));	
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccounts();
		
		model.addAttribute("accountId",Integer.parseInt(id));
		model.addAttribute("titulares", usuarios);
		model.addAttribute("cuentasB",cuentasB);
		
		return "Cuentas/deleteTitular";
	}
	
	/**
	 * Recibe una id y devuelve datos a una vista en función de la id que tome
	 * @param id: Id del cliente 
	 * @param idAccount: Id de la cuenta
	 * @param model: Modelo
	 * @return Retorna la vista con los datos del modelo cargados en función de las id que le llegan
	 */
	@PostMapping("/ultDelTitular")
	public String ultDelTitular(@RequestParam String id,@RequestParam String idAccount, Model model) {
		
		
		cuentaService.deleteTitularUser(Integer.parseInt(idAccount), Integer.parseInt(id));
		final List<Cuentas_Usuarios> usuarios = cuentaService.findAllTitulatesByAccountId(Float.parseFloat(id));	
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccounts();
		
		model.addAttribute("accountId",Float.parseFloat(idAccount));
		model.addAttribute("titulares", usuarios);
		model.addAttribute("cuentasB",cuentasB);
		
		
	
		return "redirect:index";
	}
	
	/**
	 * Recibe la id de la cuenta bancaria, asigna los datos al modelo y devuelve una vista
	 * @param id Id de la cuenta
	 * @param model Modelo
	 * @return Devuelve la vista de cuentas
	 */
	@PostMapping("/cuentasByUser")
	public String mostrarCuentasPorId(@RequestParam String id, Model model) {
		
		final List<Usuario> usuarios = userService.findAllUsers();
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccountsByUser(Float.parseFloat(id));
		
		model.addAttribute("users", usuarios);
		model.addAttribute("accounts", cuentasB);
		
		return "Cuentas/Accounts";
	}
	
	/**
	 * Guarda la cuenta
	 * @param newAccount Modelo de la cuenta que se está guardando
	 * @param id Id del cliente con el que se relaciona la cuenta
	 * @param result resultado de la operacion
	 * @return Devuelve a la vista tras el guardado
	 * @throws Exception
	 */
	@PostMapping("/addNewAccount")
	private String adearAccount(@Valid @ModelAttribute CuentaBancariaModelo newAccount,@RequestParam String id, BindingResult result) throws Exception {
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = formatter.parse(newAccount.getCreateAt());
		
		CuentaBancaria e = new CuentaBancaria();
			
		
		e.setNumAccount(newAccount.getNumAccount());
		e.setBalance(newAccount.getBalance());
		e.setCreateAt(date);
		
		
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros de cliente erróneos");
		} else {
				
			cuentas.insertNewAccountUser(Integer.parseInt(id), e);
		}

		return "redirect:ClientView";
	}
	
}
