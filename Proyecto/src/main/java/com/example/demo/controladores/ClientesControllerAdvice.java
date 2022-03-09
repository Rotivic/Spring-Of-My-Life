package com.example.demo.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientesControllerAdvice {
	
	 
	
	/**
	 * Controla las excepciones que ocurren para que no se colapse la aplicación
	 * @param req
	 * @param e, recoge la excepción que ha sucedido
	 * @param model, es el modelo 
	 * @return Retorna el error que ha captado a la vista de error
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {

		// Respuesta.
		model.addAttribute("errorMsg", e.getMessage());

		return "error";
	}
	
	

}
