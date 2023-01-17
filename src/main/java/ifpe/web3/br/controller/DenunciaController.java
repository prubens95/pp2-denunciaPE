package ifpe.web3.br.controller;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifpe.web3.br.model.CategoriaDAO;
import ifpe.web3.br.model.Categorias;
import ifpe.web3.br.model.Denuncia;
import ifpe.web3.br.model.DenunciaDAO;
import ifpe.web3.br.model.EnderecoDAO;
import ifpe.web3.br.model.Usuario;
import ifpe.web3.br.model.UsuarioDAO;
import jakarta.servlet.http.HttpSession;

@Controller
public class DenunciaController {
	
	@Autowired
	public DenunciaDAO denunciaDAO;	
	
	@Autowired
	public EnderecoDAO enderecoDAO;
	
	@Autowired 
	public CategoriaDAO categoriaDAO;
	
	@Autowired
	public UsuarioDAO usuarioDAO;
	
	@GetMapping("/denunciar")
	public String denunciar(Model model, Denuncia denuncia) {
		model.addAttribute("categorias", categoriaDAO.findAll());	
		return "denunciar";
	}		
	
	@PostMapping("/salvarDenuncia")
	public String salvarDenuncia(Denuncia denuncia, HttpSession session, RedirectAttributes ra,
			@RequestParam("ImgDenuncia") MultipartFile anexos) {

		 if (session.getAttribute("tipo") != null && session.getAttribute("tipo").equals("usuario")) {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			
			Random ale = denuncia.numeroDenuncia();
			denuncia.setProtocolo(ale.nextInt(10000000));
			denuncia.setUsuario(usuario);
			try {
				denuncia.setAnexos(anexos.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (anexos.isEmpty()) {
				denuncia.setAnexos(null);
			}
			System.out.println(denuncia.getProtocolo());
			ra.addFlashAttribute("proto", denuncia.getProtocolo());
					
			denunciaDAO.save(denuncia);
			
		} else {
			Random ale = denuncia.numeroDenuncia();
			denuncia.setProtocolo(ale.nextInt(10000000));
			System.out.println(denuncia.getProtocolo());
			try {
				denuncia.setAnexos(anexos.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (anexos.isEmpty()) {
				denuncia.setAnexos(null);
			}
			ra.addFlashAttribute("proto", denuncia.getProtocolo());
			denunciaDAO.save(denuncia);
		}
		 
		 return "redirect:/denunciar";
	}
	
	@GetMapping("/anexos/{id_denuncia}")
	@ResponseBody
	public byte[] exibirImagem(@PathVariable("id_denuncia") Integer id_denuncia) {
		Denuncia denuncia = denunciaDAO.getReferenceById(id_denuncia);
		return denuncia.getAnexos();
		
	}
	
	
	@PostMapping("/salvarDenunciaEdit")
	public String salvarDenuncia(Integer id_denuncia, Denuncia denuncia, Model model,
			@RequestParam("ImgDenuncia") MultipartFile anexos, byte[] arquivo) {
		model.addAttribute("denuncia", this.denunciaDAO.findById(id_denuncia).orElse(null));
		arquivo = this.exibirImagem(id_denuncia);
		try {
			denuncia.setAnexos(anexos.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (anexos.isEmpty()) {
			denuncia.setAnexos(arquivo);
		}
		denunciaDAO.save(denuncia);
		return "redirect:/denuncias";
	}
	
	@GetMapping("/denuncias")
	public String denuncias(Model model, Denuncia denuncia, Categorias categoria) {
		model.addAttribute("denuncia", denuncia);
		model.addAttribute("categoria", categoria);
		model.addAttribute("denuncias", this.denunciaDAO.listarDenuncias());
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "visualizar";
	}
	@GetMapping("/minhasDenuncias")
	public String minhasDenuncias(HttpSession session, Model model, Denuncia denuncia, Categorias categoria) {
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("denuncia", denuncia);
		model.addAttribute("categoria", categoria);
		model.addAttribute("categorias", categoriaDAO.findAll());
		model.addAttribute("denuncias", this.denunciaDAO.listarDenunciasMi(usuario.getId_usuario()));
		return "visualizarMinhas";
	}
	
	@GetMapping("/removerDenuncia")
	public String removerDenuncia(Integer id_denuncia) {
		denunciaDAO.deleteById(id_denuncia);
		return "redirect:/minhasDenuncias";
	}
	
	@GetMapping("/removerDenunciaAdm")
	public String removerDenunciaAdm(Integer id_denuncia) {
		denunciaDAO.deleteById(id_denuncia);
		return "redirect:/denuncias";
	}
	
	@GetMapping("/editarDenuncia")
	public String editarDenuncia(Integer id_denuncia, Model model, Categorias categoria) {
		Denuncia denuncia = denunciaDAO.findById(id_denuncia).orElse(null);
		model.addAttribute("denuncia", denuncia);
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "/editarDenuncia";
	}
	
	@GetMapping("/editarDenunciaAdm")
	public String editarDenunciaAdm(Integer id_denuncia, Model model, Categorias categoria) {
		Denuncia denuncia = denunciaDAO.findById(id_denuncia).orElse(null);
		model.addAttribute("denuncia", denuncia);
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "/editarDenuncia";
	}

}
