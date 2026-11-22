package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

// Implementa a interface
@Repository 
@Profile("memoria")
public class LivroRepositoryEmMemoria implements ILivroRepository {
    // Final é tipo um const
    private final Map<Long, Livro> livros = new HashMap<Long, Livro>();
    private Long sequenciaId = 0L;

    @Override 
    public Livro salvar(Livro livro) {

        if (livro.getId() == null) {
            this.sequenciaId = this.sequenciaId + 1;
            livro.setId(this.sequenciaId);
        }

        this.livros.put(livro.getId(), livro);
        return livro;
    }

    @Override 
    public List<Livro> listarTodos() {
        // O Java deduz qual é o tipo, porque o tipo vem do Livro do HashMap 
        // O values compartilha a memória com o hasmap, alteraria a memória dos livros
        return new ArrayList<>(this.livros.values());

        /**
         * List<livro> colecaoLivros = new ArrayList<>(this.livros.values());
         * return colecaoLivros;
         * 
         * List<Livro> todosOsLivros = new ArrayList<>();
         * 
         * // For each
         * for (Livro livro : this.livros.values()) {
         *      todosOsLivros.add(livro);
         * }
         * 
         * return todosOsLivros;
         */
    }

    @Override 
    public Optional<Livro> buscarPorId(Long id) {
        // Permite null sem exception e obriga o caller a tratar o null
        return Optional.ofNullable(this.livros.get(id));
    }

    @Override 
    public Optional<Livro> buscarPorIsbn(String isbn) {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        for (int i = 0; i < colecaoLivros.size(); i++) {
            Livro livro = colecaoLivros.get(i);
            String livroIsbn = livro.getIsbn();

            if (livroIsbn.equalsIgnoreCase(isbn)) {
                return Optional.of(livro);
            }
        }

        return Optional.empty();
    }
    
    @Override 
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> encontrados = new ArrayList<>();

        for (Livro livro : this.livros.values()) {

            String livroTitulo = livro.getTitulo().toLowerCase();

            if (livroTitulo.contains(titulo.toLowerCase())) {
                encontrados.add(livro);
            }
        }

        return encontrados;
    }
}
