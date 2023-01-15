package ifpe.web3.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/denuncias/{categoria}")
	@ResponseBody
	public List<Denuncia> filtrar(@PathVariable Integer categoria) {
		
		return denunciaDAO.findByCategoria(categoria);
	
	}
	
	@GetMapping("/filtrarCategoria")
	public String filtrarCategoria(@RequestParam Integer categoria, Denuncia denuncia, Model model) {
		denuncia = (Denuncia) this.filtrar(categoria);
		model.addAttribute("denuncia", denuncia);
		model.addAttribute("categoria", categoria);
		model.addAttribute("denuncias", this.denunciaDAO.filtrarCategoria(categoria));
		return "visualizar";
	
	}
	
	@GetMapping("/denuncias/{status}")
	public List<Denuncia> filtrarStatus(@PathVariable boolean status) {
		
		return denunciaDAO.existsByStatus(status);
	}
		
	
	
	
}
