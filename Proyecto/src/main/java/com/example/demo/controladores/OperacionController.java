package com.example.demo.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.OperacionB;
import com.example.demo.entidades.Usuario;
import com.example.demo.servicios.CuentaBancariaServiceI;
import com.example.demo.servicios.OperacionBServiceI;
import com.example.demo.servicios.UsuarioServicel;

@Controller

public class OperacionController {

	@Autowired
	private OperacionBServiceI operacion;
	@Autowired
	private CuentaBancariaServiceI cuentas;
	@Autowired
	private UsuarioServicel userService;
	
	/**
	 * Asigna datos al modelo y devuelve la vista
	 * @param model Modelo
	 * @return Devuelve la vista OperationsView
	 */
	@GetMapping("/OperationsView")
	public String mostrarOperaciones(Model model) {
		
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccounts();
		final List<OperacionB> listaOperaciones = new ArrayList<OperacionB>();
		
		model.addAttribute("listaDatos",listaOperaciones);
		model.addAttribute("accounts", cuentasB);
		return "Operaciones/OperationsView";
	}
	
	/**
	 * Asigna datos de operaciones segun la id de cuenta que le llega
	 * @param id Id de cuenta
	 * @param model Modelo
	 * @return Devuelve la vista con el modelo 
	 */
	@PostMapping("/accounts")
	public String mostrarCuentasPorId(@RequestParam String id, Model model) {

		final List<CuentaBancaria> cuentasB = cuentas.findAllAccounts();
		final List<OperacionB> listaOperaciones = operacion.findAllOperationsByAccount(Float.parseFloat(id));
		
		model.addAttribute("listaDatos",listaOperaciones);
		model.addAttribute("accounts", cuentasB);
		
		return "Operaciones/OperationsView";
	}

	/**
	 * Asigna datos al modelo y devuelve la vista
	 * @param model Modelo
	 * @return Devuelve la vista MakeOperations con el modelo cargado
	 */
	@GetMapping("/MakeOperations")
	public String hacerOperaciones(Model model) {
		
		final List<Usuario> usuarios = userService.findAllUsers();
		final List<CuentaBancaria> cuentasB = new ArrayList<>();
		
		model.addAttribute("users", usuarios);
		model.addAttribute("accounts", cuentasB);
		return "Operaciones/MakeOperations";
	}
	
	/**
	 * Carga datos al modelo y los retorna con la vista
	 * @param model Modelo
	 * @return Devuelve la vista TransactionAcc 
	 */
	@GetMapping("/transactionBeAccount")
	public String transEntreCuentas(Model model) {
	
		final List<Usuario> usuarios = userService.findAllUsers();
		final List<CuentaBancaria> cuentasB = new ArrayList<>();
		
		model.addAttribute("users", usuarios);
		model.addAttribute("accounts", cuentasB);
		
		
		return "Operaciones/TransactionAcc";
	}
	
	/**
	 * Asigna datos en funcion de la id de cliente que le llega
	 * @param id Id de cliente
	 * @param model Modelo
	 * @return Retorna la vista TransactionAcc
	 */
	@PostMapping("/renewTransactionData")
	public String transEntreCuentas2(@RequestParam String id, Model model) {
		System.out.println("asd");
		final List<Usuario> usuarios = userService.findAllUsers();
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccountsByUser(Float.parseFloat(id));
		final List<CuentaBancaria> cuentasA = cuentas.findAllAccounts();
		
		model.addAttribute("users", usuarios);
		model.addAttribute("accounts", cuentasB);
		model.addAttribute("allAccounts",cuentasA);
		
		
		return "Operaciones/TransactionAcc";
	}
	
	/**
	 * Recibe una id de cliente, asigna las cuentas segun la id al modelo y manda a la vista MakeOperations 
	 * @param id Id de cliente
	 * @param model Modelo
	 * @return Devuelve la visa MakeOperations
	 */
	@PostMapping("/renewDataAccounts")
	public String renovarDatosNewAccount(@RequestParam String id, Model model) {

		final List<Usuario> usuarios = userService.findAllUsers();
		final List<CuentaBancaria> cuentasB = cuentas.findAllAccountsByUser(Float.parseFloat(id));
		
		model.addAttribute("users", usuarios);
		model.addAttribute("accounts", cuentasB);
		
		return "Operaciones/MakeOperations";
	}
	
	/**
	 * Actualiza una cuenta y genera la operacion
	 * @param id Id de cuenta
	 * @param dinero Cantida del movimiento
	 * @param option Tipo de operacion
	 * @param model Modelo
	 * @return Devuelve a la vista index tras añadir los registros
	 */
	@PostMapping("/makeMoneyMovement")
	public String moverDinero(@RequestParam String id,@RequestParam String dinero,@RequestParam String option, Model model) {

		
		
		Date date = new Date();
		
	   
		
		//Actualizo la cuenta
		CuentaBancaria cB = cuentas.findAccountById(Long.parseLong(id));
		
		if(option.equalsIgnoreCase("ingresar")) {
			cB.setBalance(cB.getBalance() + Double.parseDouble(dinero));
		}else {
			cB.setBalance(cB.getBalance() - Double.parseDouble(dinero));
		}
		
		cuentas.updateAccount(cB);
		
		//Inserto la operacion realizada
		OperacionB opB = new OperacionB();
		opB.setCantidad(dinero);
		opB.setTipoOperacion(option);
		opB.setFechaRealizacion(date);		
		opB.setCuentaBancaria(cB);
		operacion.addOperation(opB);
		
		
		return "redirect:index";
	}
	
	/**
	 * Produce la transaccion entre cuentas
	 * @param id Id de la primera cuenta
	 * @param dinero Cantidad de la transaccion
	 * @param idDos Id de la segunda cuenta
	 * @param model Modelo
	 * @return Retorna a la vista index 
	 */
	@PostMapping("/makeMoneyMoveBetAccounts")
	public String moverDineroEntreCuentas(@RequestParam String id,@RequestParam String dinero,@RequestParam String idDos, Model model) {

		
		
		Date date = new Date();
		
	   	
		//Actualizo la cuenta
		CuentaBancaria cB1 = cuentas.findAccountById(Long.parseLong(id));
		CuentaBancaria cB2 = cuentas.findAccountById(Long.parseLong(idDos));
		
		cB2.setBalance(cB2.getBalance() + Double.parseDouble(dinero));
		
		cB1.setBalance(cB1.getBalance() - Double.parseDouble(dinero));
		
		cuentas.updateAccount(cB1);
		cuentas.updateAccount(cB2);
		
		//Inserto la primera operacion realizada
		OperacionB opB1 = new OperacionB();
		opB1.setCantidad(dinero);
		opB1.setTipoOperacion("Traspaso de Dinero a la cuenta " + cB2.getNumAccount());
		opB1.setFechaRealizacion(date);	
		opB1.setCuentaBancaria(cB1);
		operacion.addOperation(opB1);
		
		//Inserto la segunda operacion realizada
		OperacionB opB2 = new OperacionB();
		opB2.setCantidad(dinero);
		opB2.setTipoOperacion("Traspaso de Dinero desde la cuenta " + cB1.getNumAccount());
		opB2.setFechaRealizacion(date);	
		opB2.setCuentaBancaria(cB2);
		operacion.addOperation(opB2);
		
		return "redirect:index";
	}
	
}
