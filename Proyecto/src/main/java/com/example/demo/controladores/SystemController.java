package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("*")
public class SystemController {

		
		@GetMapping
		public String showIndex() {
			return "index";
		}
	
		@GetMapping("/UsuariosView")
		public String redirectToUsuarioDealershipController() {
			return "redirect:ClientView";
		}
		
		@GetMapping("/addCliente")
		public String redirectToUsuarioAddDealershipController() {
			return "redirect:addClient";
		}
		
		@GetMapping("/OperacionView")
		public String redirectToOperacionDealershipController() {
			return "redirect:OperationsView";
		}
		
		
		@GetMapping("/CuentasView")
		public String redirectToAccountDealershipController() {
			return "redirect:AccountsView";
		}
		
		@GetMapping("/newCuentasUserView")
		public String redirectTonewAccountDealershipController() {
			return "redirect:addAccountUser";
		}
		
		@GetMapping("/delAccountByUser")
		public String redirectToDelAccountDealershipController() {
			return "redirect:delAccountFromUser";
		}
		
		@GetMapping("/transactionAccount")
		public String redirectToTransactionAccountDealershipController() {
			return "redirect:transactionBeAccount";
		}
		
}
