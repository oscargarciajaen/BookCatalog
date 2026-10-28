package Book.Catalog.BookCatalog.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Removed the semicolon here
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String titulo;

    @Column(nullable = false, unique = false)
    private Integer numPaginas;

    public Book(String nombre, Integer numPaginas){
        this.titulo = nombre;
        this.numPaginas = numPaginas;
    }

    public Book(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public Integer getNumPaginas() {
        return this.numPaginas;
    }

    public Boolean isBook() {
        Boolean valido = false;
        if (this.titulo != null && !this.titulo.isEmpty() && numPaginas > 0){
            valido = true;
        }
        return valido;
    }
}