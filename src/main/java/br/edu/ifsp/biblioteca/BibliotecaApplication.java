package br.edu.ifsp.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Isso daqui é uma anotação, não um decorator. Em python, isso faz um wrapper da função que vem abaixo
// Em java, isso daqui não tem efeito nenhum se alguém não estiver procurando por ela
// No caso, essa é uma anotação própria do spring onde ele procura as classes 
// Isso daqui está mais para type hint do que qualquer outra coisa

// Em paralelo, isso daqui é um alias para outras três coisas
// @Configuration - esta classe pode fabricar objetos para o spring
// @EnableAutoConfiguration - configure sozinho o que encontrar instalado. Pegar as dependências do pom.xml e se virar
// @ComponentScan - a partir dessa classe busque todas as outras que puder encontrar
@SpringBootApplication 
public class BibliotecaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }
}
