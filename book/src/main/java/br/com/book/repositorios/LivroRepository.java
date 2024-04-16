package br.com.book.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.book.entidades.LivroEntidade;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntidade, Long>{
    public List<LivroEntidade> findByTitulo(String titulo);
}
