package br.edu.ifsp.biblioteca;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.repository.*;

// Isso daqui é uma anotação, não um decorator. Em python, isso faz um wrapper da função que vem abaixo
// Em java, isso daqui não tem efeito nenhum se alguém não estiver procurando por ela
// No caso, essa é uma anotação própria do spring onde ele procura as classes 
// Isso daqui está mais para type hint do que qualquer outra coisa

// Em paralelo, isso daqui é um alias para outras três coisas
// @Configuration - esta classe pode fabricar objetos para o spring
// @EnableAutoConfiguration - configure sozinho o que encontrar instalado. Pegar as dependências do pom.xml e se virar
// @ComponentScan - a partir dessa classe busque todas as outras que puder encontrar
// @SpringBootApplication 
public class BibliotecaApplication {
    public static void main(String[] args) {
        // Comentando out o spring por hora
        // SpringApplication.run(BibliotecaApplication.class, args);

        ILivroRepository repo = new LivroRepositoryEmMemoria();

        // O L na frente do integer é literal de long btw
        Livro livro = new Livro("9788508145607", "Dom Casmurro", 2026);
        Livro livro2 = new Livro("9788573264579", "Dom Quixote", 2026);
        Livro livro3 = new Livro("9788573266467", "Crime e Castigo", 2026);

        repo.salvar(livro);
        repo.salvar(livro2);
        repo.salvar(livro3);

        System.out.println(repo.listarTodos());
        System.out.println(repo.buscarPorTitulo("dom"));
        System.out.println(repo.buscarPorIsbn("000"));
        // Arrow function do java
        repo.buscarPorId(1L).ifPresent(l -> System.out.println(l.getTitulo()));
    }
}
