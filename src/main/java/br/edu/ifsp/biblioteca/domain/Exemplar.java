package br.edu.ifsp.biblioteca.domain;

import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;

import jakarta.persistence.*;

@Entity 
@Table(name="exemplar")
public class Exemplar {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true, length=30)
    private String codigo;

    @Enumerated (EnumType.STRING)
    @Column(nullable=false, length=20)
    private EStatusExemplar status;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "livro_id", nullable = false)
    private Livro livro;

    public Exemplar() {}

    public Exemplar(String codigo, Livro livro) {
        this.codigo = codigo;
        this.status = EStatusExemplar.DISPONIVEL;
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Exemplar [id=" + id + ", codigo=" + codigo + ", status=" + status + ", livro=" + livro + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public EStatusExemplar getStatus() {
        return status;
    }

    public void setStatus(EStatusExemplar status) {
        this.status = status;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void devolver() {
        this.status = EStatusExemplar.DISPONIVEL;
    }

    public void emprestar() {
        if (this.status == EStatusExemplar.EMPRESTADO) {
            throw new RegraDeNegocioException("Exemplo " + this.codigo + " já está emprestado!!!");
        }

        this.status = EStatusExemplar.EMPRESTADO;
    }

    public boolean estaDisponivel() {
        return this.status == EStatusExemplar.DISPONIVEL;
    }
}
