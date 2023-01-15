package ifpe.web3.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;

import ifpe.web3.br.model.Categorias;
import ifpe.web3.br.model.Denuncia;
import ifpe.web3.br.model.DenunciaDAO;

@Controller

public class AcompanhamentoController {
	
	@Autowired
	public DenunciaDAO denunciaDAO;	

	@GetMapping("/acom")
	
	public String acom() {
		
		return "acompanhamento";
		
	}
	
	
		@GetMapping("/protocolo")
		public String protocolo(Integer protocolo, Denuncia denuncia, Categorias categoria, Model model) {
			
			model.addAttribute("denuncia", denuncia);
			model.addAttribute("categoria", categoria);
			model.addAttribute("denuncias", this.denunciaDAO.procurarProtocolo(protocolo));
		
			return "protocolo";
			
		}
		
		
		@GetMapping("/novaBusca")
		public String novaBusca() {
			
			return "acompanhamento";
			
		}
	
	
	
	
}
