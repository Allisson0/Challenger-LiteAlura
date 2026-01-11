package br.com.litealura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private Long id;

    private String title;

    private List<Author> authors;

    private List<String> languages;

    @JsonAlias("download_count")
    private Integer qtdDownload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Integer getQtdDownload() {
        return qtdDownload;
    }

    public void setQtdDownload(Integer qtdDownload) {
        this.qtdDownload = qtdDownload;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }


    //======= RETORNO DE OBJETO =======
    @Override
    public String toString(){

        //Concatenação de nome de autor ou autores
        String concatAuthor = this.getAuthors().size() <= 1 ? "Autor" : "Authors";

        //Para cada autor encontrado na lista de autores do livro, transforma
        //em uma 'String' separada por vírgula.
        String autores = this.getAuthors().stream()
                .map(Author::getNome)
                .collect(Collectors.joining(", "));

        //Para cada linguagem dentro de linguagens do livro, concatena numa
        //lista separada por vírgula.
        String languages = String.join(", ", this.getLanguages());

        //Retorna, de maneira concatenada, as informações do livro
        return """
                ======= LIVRO =======
                Titulo: %s
                %s: %s
                Idioma: %s
                Número de Downloads: %d
                """.formatted(
                        this.getTitle(),
                        concatAuthor,
                        autores,
                        languages,
                        this.getQtdDownload());
    }

}
