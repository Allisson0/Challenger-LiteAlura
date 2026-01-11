package br.com.litealura.Repository;

import br.com.litealura.Model.Author;
import br.com.litealura.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE a.nome ILIKE %:name% LIMIT 1")
    Optional<Author> findAuthorByName(String name);

    List<Book> findByIdEquals(Long id);

    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> findAuthors();

}
