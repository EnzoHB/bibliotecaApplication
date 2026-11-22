package br.edu.ifsp.biblioteca.cli;

// Interface de um método só onde basicamente depois que tudo estiver OK no framework inteiro, ele procura
// Todo mundo que implementa essa interface e chama o run de cada um
import org.springframework.boot.CommandLineRunner;
// Sterotype é um namespace onde tem anotações que o spring guarda para classes que ele geerncia
// @Component
// @Service
// @Repository
// @Controller/ @RestController
// São como tags semânticas do html
// "Spring, crie e guarde um objeto desta classe (bean)"
import org.springframework.stereotype.Component;

@Component 
public class AmbienteRunner implements CommandLineRunner {
    
    // Overrido impede que um método sem correspondente na interface não sobrescreva nada
    @Override 
    // Variable args notation in Java
    // Somente aceita strings
    // Tem que ser o último parâmetro se não não compila
    public void run(String... args) {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("  Biblioteca IFSP - ambiente OK");
        System.out.println("Java: " + System.getProperty("java.version"));
        System.out.println("=========================================");
        System.out.println();
    }
}
