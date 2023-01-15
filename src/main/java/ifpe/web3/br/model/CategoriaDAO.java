package ifpe.web3.br.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDAO extends JpaRepository<Categorias, Integer> {
	
	public List<Categorias>findByNome(String nome);
}
