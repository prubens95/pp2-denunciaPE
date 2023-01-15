package ifpe.web3.br.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoDAO extends JpaRepository<Endereco, Integer> {
	
	 public List<Endereco>findByBairro(String bairro);

}
