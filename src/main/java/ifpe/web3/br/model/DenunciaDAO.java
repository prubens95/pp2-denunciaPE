package ifpe.web3.br.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ifpe.web3.br.model.CategoriaDAO;

public interface DenunciaDAO extends JpaRepository<Denuncia, Integer> {
	
	List<Denuncia> findByCategoria(Integer id_categoria);
	
	List<Denuncia> existsByStatus(boolean status);
	
	@Query("SELECT d FROM Denuncia d")
	public List<Denuncia> listarDenuncias();
	
	@Query("SELECT d FROM Denuncia d where d.usuario.id like:id")
	public List<Denuncia> listarDenunciasMi(Integer id);
	
	@Query("SELECT d FROM Denuncia d where d.protocolo = :protocolo")
	public List<Denuncia> procurarProtocolo(Integer protocolo);
	
	@Query("SELECT d FROM Denuncia d where d.categoria.id = :id")
	public List<Denuncia> filtrarCategoria(Integer id);

}
