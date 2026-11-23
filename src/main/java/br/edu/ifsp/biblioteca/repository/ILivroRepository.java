package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILivroRepository extends JpaRepository<Livro, Long> {
    
    Optional<Livro> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    // Containing vira no LIKE operator %%, ignoreCase vira LOWER
    List<Livro> findByTituloContainingIgnoreCase(String trechoTitulo);
    // Signature do método acima, paginada, overload
    Page<Livro> findByTituloContainingIgnoreCase(String trechoTitulo, Pageable pagina);
}
