package ifpe.web3.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifpe.web3.br.model.Categorias;
import ifpe.web3.br.model.Denuncia;
import ifpe.web3.br.model.Usuario;
import ifpe.web3.br.model.UsuarioDAO;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
	
	@Autowired
	public UsuarioDAO usuarioDAO;
	
	@GetMapping("/usuario")
	public String usuario() {
		return "usuario";
	}

	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}	
	
	@GetMapping("/editarPerfil")
	public String editarPerfil(Integer id_usuario, Model model) {
		Usuario usuario = usuarioDAO.findById(id_usuario).orElse(null);
		model.addAttribute("usuario", usuario);
		return "/editarPerfil";
	}
	
	@PostMapping("/salvarUsuario")
	public String salvarUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
		return "redirect:/login";
	}
	
	@GetMapping("/listaUsuario")
	public String listaUsuario(Model model) {
		model.addAttribute("lista",usuarioDAO.findAll());
		return "listaUsuario";
	}
	
	@GetMapping("/removerUsuario")
	public String removerUsuario(Integer id_usuario, RedirectAttributes ra) {
		Usuario usuario = usuarioDAO.findById(id_usuario).orElse(null);
		if (usuario.isAdm() == false) {
			usuarioDAO.deleteById(id_usuario);
			ra.addFlashAttribute("deletado", usuario);
		} else {
			ra.addFlashAttribute("erro", usuario);
		}
		
		return "redirect:/listaUsuario";
	}
	
	@PostMapping("/salvarPerfil")
	public String salvarPerfil(Usuario usuario) {
		usuarioDAO.save(usuario);
		return "redirect:/";
	}
	
	@RequestMapping("/auth")
	 public String efetuarLogin(Usuario usuario, RedirectAttributes ra, HttpSession session) {
		usuario = this.usuarioDAO.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		
		if(usuario != null) {
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("tipo", "usuario");
			return "redirect:/denunciar";
		}	else {
			ra.addFlashAttribute("naoAutorizado", "Dados incorretos ou não existe");
			return "redirect:/login";
		}		
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
