package br.com.litealura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @JsonAlias("birth_date")
    private Integer anoNascimento;

    @JsonAlias("death_year")
    private Integer anoMorte;

    @JsonAlias("name")
    private String nome;

    public String getNome() {
        return nome;
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
