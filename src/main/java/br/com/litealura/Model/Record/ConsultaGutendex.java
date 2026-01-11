package br.com.litealura.Model.Record;

import br.com.litealura.Model.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConsultaGutendex(
        List<Book> results
) {
}
