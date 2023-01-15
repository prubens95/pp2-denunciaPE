package ifpe.web3.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ifpe.web3.br.model.CategoriaDAO;
import ifpe.web3.br.model.Categorias;
import ifpe.web3.br.model.Denuncia;
import ifpe.web3.br.model.DenunciaDAO;
import ifpe.web3.br.model.Endereco;
import ifpe.web3.br.model.EnderecoDAO;

public class FiltragemController {

	@Autowired
	public CategoriaDAO categoriaDAO;
	
	@Autowired
	public EnderecoDAO bairro;
	
	@Autowired
	public DenunciaDAO denunciaDAO;
	
	
	@GetMapping("/FiltrarCateg")
	public List<Categorias>selecionar(){
		
		return categoriaDAO.findByNome(null);
		
		
	}
	
	@GetMapping("/selecionarEndereco")
	public List<Endereco> selecionarEndereco(){
		
		return bairro.findByBairro(null);
		
	}
	
	@PostMapping("/filtrar")
	public String filtrar(Integer id, Model model,
			Denuncia denuncia, Categorias categoria) {
		model.addAttribute("categoria", categoria);
		model.addAttribute("denuncias", denunciaDAO.filtrarCategoria(id));
		model.addAttribute("categorias", categoriaDAO.findAll());
		return "redirect:/denuncias";
		
	}
	
	
}
