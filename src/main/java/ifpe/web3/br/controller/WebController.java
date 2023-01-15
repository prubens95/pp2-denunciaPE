package ifpe.web3.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ifpe.web3.br.model.Usuario;
import ifpe.web3.br.model.UsuarioDAO;

@Controller
public class WebController {
	
	@Autowired
	public UsuarioDAO usuarioDAO;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
}
