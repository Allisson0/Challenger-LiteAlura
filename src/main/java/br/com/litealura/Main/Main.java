package br.com.litealura.Main;

import br.com.litealura.Model.Author;
import br.com.litealura.Model.Book;
import br.com.litealura.Repository.BookRepository;
import br.com.litealura.Service.GutendexService;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    //Repositório de acesso do banco de dados
    private final BookRepository repository;
    //Scanner para conversa com o usuário
    private Scanner input = new Scanner(System.in);
    private GutendexService gutendex = new GutendexService();
    //Menu padrão
    private final String menu = """
                =========== Lite - Alura ==========
                
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                
                0 - Sair
                """;
    private int choose = -1;

    //Recupera o acesso do Spring para o repositório do banco de dados
    public Main(BookRepository repository){
        this.repository = repository;
    }

    //======= MENU PRINCIPAL =======
    public void menu(){
        //Enquanto a escolha for diferente de 0, o programa executará
        while(choose!=0) {
            System.out.println(menu);
            System.out.println("Escolha uma das opções acima: ");

            //Verifica se a opção é um inteiro de fato, caso não, retorna
            //um aviso.
            try {
                choose = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número.");
                //Resolução de bug de looping de pedido de digitação
                input.nextLine();
            }

            //Com base na escolha, realiza uma ação.
            switch (choose){
                case 1:
                    findBookByName();
                    break;

                case 2:
                    findBooksRegistered();
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                    //Ação de término de comandos
                case 0:
                    System.out.println("Saindo...");
                    break;

                    //Opção diferente das indicadas
                default:
                    System.out.println("Informe uma opção válida, por favor.");
            }
        }

        //Finalização do programa.
        System.out.println("Programa finalizado com sucesso!");
    }

    //======= REALIZA A BUSCA DE UM LIVRO POR NOME =======
    private void findBookByName(){
        //Pede um livro para encontrar na API
        System.out.println("Digite um livro para pesquisa: ");
        String find = input.nextLine();
        //Retorna o livro encontrado através do serviço de trabalho gutendex.
        Optional<Book> bookFind = gutendex.buscaLivro(find);

        //Se o livro for encontrado, retorna no terminal e salva
        //no banco de dados
        if (bookFind.isPresent()){

            //Imprime o livro encontrado
            System.out.println(bookFind.get());

            List<Book> books = repository.findByIdEquals(bookFind.get().getId());
            //Verifica se o livro já não está presente no banco de dados
            if (books.isEmpty()) {
                //Se não estiver...
                //Define o autor do livro, e caso já tenha no banco
                //de dados, salva por meio da referência do mesmo.
                //Salva bookFind.
                repository.save(saveAuthor(bookFind.get()));
            }
        } else{
            //Retorna que não foi encontrado
            System.out.println("Livro não encontrado na biblioteca da API.");
        }
    }

    //======= VERIFICA SE O AUTOR JÁ EXISTE NO BANCO DE DADOS =======
    private Book saveAuthor(Book book){
        //Recupera a lista de autores do livro
        List<Author> authors = book.getAuthors();

        //Filtra essa lista de atores, e encontra os presentes no
        //banco de dados, e salva apenas os presentes.
        List<Author> authorsInBd = authors.stream()
                .filter(a -> repository.findAuthorByName(a.getNome()).isPresent())
                .collect(Collectors.toList());

        //Se houver autores na lista de autores no banco de
        //dados, ele irá relacionar os presentes para uma lista
        //com os que não tem no livro.
        if (!authorsInBd.isEmpty()) {
            //Pega a lista dos autores não presentes no Banco de
            //dados
            List<Author> authorsOutBd = authorsInBd.stream()
                    .filter(a -> !a.equals(authors))
                    .collect(Collectors.toList());

            //Adiciona na lista de autores do livro
            authorsInBd.addAll(authorsOutBd);
            //Seta como autores no banco de dados
            book.setAuthors(authorsOutBd);
        }

        //Retorna o livro
        return book;
    }

    //======= RETORNAR LISTA DE LIVROS DO BANCO DE DADOS =======
    private void findBooksRegistered(){
        List<Book> booksPresent = repository.findAll();
        booksPresent.forEach(System.out::println);
    }
}
