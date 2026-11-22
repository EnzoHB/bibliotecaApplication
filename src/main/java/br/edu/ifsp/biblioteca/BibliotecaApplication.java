package br.edu.ifsp.biblioteca;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifsp.biblioteca.domain.Livro;

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

        // O L na frente do integer é literal de long btw
        Livro livro = new Livro(1L, "9788508145607", "Dom Casmurro", 2026);
        Livro livro2 = new Livro(2L, "9788573264579", "Dom Quixote", 2026);
        Livro livro3 = new Livro(3L, "9788573266467", "Crime e Castigo", 2026);

        System.out.println(livro);
        System.out.println(livro2);
        System.out.println(livro3);
    }
}
