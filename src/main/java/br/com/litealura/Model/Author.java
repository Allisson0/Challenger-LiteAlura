package br.com.litealura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "autores")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("birth_year")
    @Column(name = "ano_nascimento")
    private Integer anoNascimento;

    //Anotação forte para definir o nome do json para conversão
    @JsonProperty("death_year")
    @Column(name = "ano_morte")
    private Integer anoMorte;

    @Column(unique = true)
    @JsonAlias("name")
    private String nome;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author(){}

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    //======= RETORNO DE OBJETO =======
    @Override
    public String toString(){
        return """
                Nome: %s
                Ano de nascimento: %d
                Ano de morte: %d
                """.formatted(
                        this.nome, this.anoNascimento, this.anoMorte);
    }

}
