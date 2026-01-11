package br.com.litealura.Service;

import br.com.litealura.Model.Book;
import br.com.litealura.Model.Record.ConsultaGutendex;

import java.util.Optional;

public class GutendexService {

    //Consumo da api
    private ConsumoAPIGutendex consumoApi = new ConsumoAPIGutendex();
    //Endereço padrão da API
    private final String address = "https://gutendex.com/books/?";
    //Conversor de dados da API
    private ConverteDados conversor = new ConverteDados();

    //======= REALIZA UMA BUSCA POR UM LIVRO =======
    public Book buscaLivro(String livro){
        //Pede um livro para realizar a busca

        //Concatena o endereço com base nas informações prestadas da busca
        String newAddress = address+"search="+livro.replace(" ","+");

        //Guarda a resposta da busca da API para a solicitação do usuário
        ConsultaGutendex response = conversor.obterDados(
                //Realiza o consumo da API para buscar o JSON que contém as informações do livro
                consumoApi.consumirAPI(newAddress),ConsultaGutendex.class);

        //Pega a primeira instância do livro procurado com base no nome do livro
        Optional<Book> livroEncontrado = response.results().stream()
                .filter(e -> e.getTitle().equalsIgnoreCase(livro))
                .limit(1).findFirst();

        //Se o livro for encontrado, retorna ele na função, caso não, retorna null.
        if (livroEncontrado.isPresent()){
            return livroEncontrado.get();
        }

        return null;

    }

}
