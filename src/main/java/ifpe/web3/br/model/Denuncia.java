package ifpe.web3.br.model;

import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Denuncia {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_denuncia;
	
	private String assunto;
	private String ponto_ref;
	private String descricao;
	private Integer contador;
	private int protocolo;
	private boolean status;
	private String resposta;
	
	@Lob @Column(columnDefinition = "BLOB")
	private byte[] anexos;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categorias categoria;
	
	public Integer getId_denuncia() {
		return id_denuncia;
	}
	public void setId_denuncia(Integer id_denuncia) {
		this.id_denuncia = id_denuncia;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getPonto_ref() {
		return ponto_ref;
	}
	public void setPonto_ref(String ponto_ref) {
		this.ponto_ref = ponto_ref;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getContador() {
		return contador;
	}
	public void setContador(Integer contador) {
		this.contador = contador;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setAnexos(byte[] anexos) {
		this.anexos = anexos;
	}
	public byte[] getAnexos() {
		return anexos;
	}
	public Categorias getCategoria() {
		return categoria;
	}
	public void setUsuario(Categorias categoria) {
		this.categoria = categoria;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}
	public int getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(int protocolo) {
		this.protocolo = protocolo;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Random numeroDenuncia() {
		 Random protocolo = new Random();
		 
		 return protocolo;
	 }
	
	public String getResposta() {
		return resposta;
	}
	
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	
	
}
