package br.edu.ifsp.biblioteca.domain;

public class Autor {
    private Long id;
    private String nome;

    public Autor(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Faz o override do toString do objeto para aparecer o nome bonitinho
    @Override 
    public String toString() {
        return this.nome;
    }

}
