package ifpe.web3.br.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_empresa;
	private String nome;
	private String cnpj;
	private String senha;
	
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
//	private Categorias categorias;
	
	
	
}
