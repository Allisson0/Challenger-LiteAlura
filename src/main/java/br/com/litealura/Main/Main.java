package br.com.litealura.Main;

import br.com.litealura.Model.Author;
import br.com.litealura.Model.Book;
import br.com.litealura.Repository.BookRepository;
import br.com.litealura.Service.GutendexService;

import java.util.*;

public class Main {

    //Repositório de acesso do banco de dados
    private final BookRepository repository;
    //Scanner para conversa com o usuário
    private Scanner input = new Scanner(System.in);
    private final GutendexService gutendex = new GutendexService();
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
    public Main(BookRepository repository) {
        this.repository = repository;
    }



    //======= MENU PRINCIPAL =======
    public void menu() {
        //Enquanto a escolha for diferente de 0, o programa executará
        while (choose != 0) {
            System.out.println(menu);
            System.out.println("Escolha uma das opções acima: ");

            //Verifica se a opção é um inteiro de fato, caso não, retorna
            //um aviso.
            try {
                choose = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número.");
                //Resolução de 'bug' de looping de pedido de digitação
                input.nextLine();
            }

            //Com base na escolha, realiza uma ação.
            switch (choose) {
                case 1 -> findBookByName();

                case 2 -> findBooksRegistered();

                case 3 -> findAuthorsRegistered();

                case 4 -> findAuthorsAliveIn();

                case 5 -> findBooksByLanguage();

                //Ação de término de comandos
                case 0 -> System.out.println("Saindo...");

                //Opção diferente das indicadas
                default -> System.out.println("Informe uma opção válida, por favor.");
            }
        }

        //Finalização do programa.
        System.out.println("Programa finalizado com sucesso!");
    }



    //======= REALIZA A BUSCA DE UM LIVRO POR NOME =======
    private void findBookByName() {
        //Pede um livro para encontrar na API
        System.out.println("Digite um livro para pesquisa: ");
        String find = input.nextLine();
        //Retorna o livro encontrado através do serviço de trabalho gutendex.
        Optional<Book> bookFind = gutendex.buscaLivro(find);

        //Se o livro for encontrado, retorna no terminal e salva
        //no banco de dados
        if (bookFind.isPresent()) {

            //Imprime o livro encontrado
            System.out.println(bookFind.get());

            //Procura por livros com mesmo Id no banco de dados
            List<Book> books = repository.findByIdEquals(bookFind.get().getId());
            //Verifica se o livro já não está presente no banco de dados
            if (books.isEmpty()) {
                //Se não estiver...
                //Define o autor do livro, e caso já tenha no banco
                //de dados, salva por meio da referência do mesmo.
                //Salva bookFind.
                repository.save(saveAuthor(bookFind.get()));
            }
        } else {
            //Retorna que não foi encontrado
            System.out.println("Livro não encontrado na biblioteca da API.");
        }
    }



    //======= VERIFICA SE O AUTOR JÁ EXISTE NO BANCO DE DADOS =======
    private Book saveAuthor(Book book) {

        // Cria uma lista relacionando autores existentes no banco de dados e
        // autores novos para o banco de dados
        List<Author> authorsManaged = new ArrayList<>();

        for (Author author : book.getAuthors()) {
            // Busca o autor no banco pelo nome
            Optional<Author> existingAuthor = repository.findAuthorByName(author.getNome());

            if (existingAuthor.isPresent()) {
                // SE já existe o autor no banco de dados, salva a instância que veio
                // do banco de dados dentro da lista
                authorsManaged.add(existingAuthor.get());
            } else {
                // SE não existe no banco de dados, salva como novo autor
                authorsManaged.add(author);
            }
        }

        //Atualiza a lista de autores seguindo a nova lista.
        book.getAuthors().clear();
        book.getAuthors().addAll(authorsManaged);
        //Retorna o livro com os ajustes de autores
        return book;
    }



    //======= RETORNAR LISTA DE LIVROS DO BANCO DE DADOS =======
    private void findBooksRegistered() {
        //Procura todas as instâncias de livro no repositório
        List<Book> booksPresent = repository.findAll();

        // SE não encontrar nenhum livro, retorna esta mensagem
        if (booksPresent.isEmpty()) {
            System.out.println("Nenhum livro encontrado no banco de dados.");
        } else {
            // SE encontrar, retorna esses achados
            booksPresent.forEach(System.out::println);
        }
    }



    //======= RETORNAR LISTA DE AUTORES DO BANCO DE DADOS ========
    private void findAuthorsRegistered() {
        //Procura todas as instâncias de autores no repositório
        List<Author> authorsPresent = repository.findAuthors();

        // SE não encontrar nenhum autor no banco de dados, retorna uma mensagem
        if (authorsPresent.isEmpty()) {
            System.out.println("Nenhum autor encontrado no banco de dados.");
        } else {
            //SE encontrar, retorna esses achados
            authorsPresent.forEach(System.out::println);
        }
    }



    //======= PROCURA POR AUTORES VIVOS EM DETERMINADO ANO =======
    private void findAuthorsAliveIn() {

        // Verifica se há autores salves no banco de dados
        if (repository.findAuthors().isEmpty()) {
            // SE não há autores, retorna uma mensagem
            System.out.println("Nenhum autor salvo no banco de dados");
        }
        // SE há autores, continua o processo.
        else {
            System.out.println("Digite o ano de base para busca de autores vivos: ");
            Integer choose;

            //Verifica se o que foi digitado é de fato um número
            try {
                choose = input.nextInt();
                input.nextLine();

                //Procura pelos autores vivos na época buscada
                List<Author> authorsAlive = repository.findAuthorsAlive(choose);

                //SE encontrar:
                if (!authorsAlive.isEmpty()) {
                    System.out.println("Autores vivos em " + choose + ":");
                    authorsAlive.forEach(System.out::println);
                }
                //SE não encontrar:
                else {
                    System.out.println("Nenhum autor salvo vivo nesta época.");
                }

                //ERRO de digitação de número inválido
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um ano válido.");
                input.nextLine();
            }
        }
    }



    //======= PROCURA LIVROS EM UM DETERMINADO IDIOMA =======
    private void findBooksByLanguage() {

        // Verifica se há livros salvos no banco de dados
        if (repository.findAll().isEmpty()) {
            // SE não houver livros salvos, retorna uma mensagem.
            System.out.println("Não há nenhum livro salvo no banco de dados" +
                    "para realizar a pesquisa.");
        } else {
            // SE há livros salvos, continua:
            int chooseLan = -1;
            String language = "";
            boolean exit = false;

            //Enquanto saída for false, continua o comando
            while (!exit) {
                String languageMenu = """
                        Escolha a língua:
                        1 - Inglês
                        2 - Português
                        3 - Francês
                        4 - Espanhol
                        5 - Outro
                        
                        0 - Voltar ao menu principal
                        """;

                //Pede para o usuário informar um código de linguagem
                System.out.println(languageMenu);

                //Verifica se a escolha foi um número ou não.
                try {
                    chooseLan = input.nextInt();
                    input.nextLine();

                    //Verifica qual lingua foi escolhida
                    switch (chooseLan) {
                        case 1:
                            language = "en";
                            exit = true;
                            break;

                        case 2:
                            language = "pt";
                            exit = true;
                            break;

                        case 3:
                            language = "fr";
                            exit = true;
                            break;

                        case 4:
                            language = "es";
                            exit = true;
                            break;

                        case 5:
                            System.out.println("Digite o código da língua: ");
                            language = input.nextLine();
                            exit = true;
                            break;
                        case 0:
                            language = "none";
                            System.out.println("Voltando ao menu principal");
                            exit = true;
                            break;

                        default:
                            System.out.println("Escolha uma opção válida.");
                            break;
                    }

                    //Verifica se exit é igual true, se for, significa que há uma escolha
                    //de língua para finalização
                    if (exit) {
                        //Procura por livros com este código
                        List<Book> booksFind = repository.findBooksByLanguage(language);

                        //SE encontrar, imprime eles com uma mensagem de livros encontrados
                        if (!booksFind.isEmpty()) {

                            //Imprime a quantidade de livros encontrados
                            System.out.println("Quantidades de livros encontrados: " + booksFind.size());

                            //Mostra os livros encontrados
                            System.out.println("Livros encontrados para a língua: " + language);
                            booksFind.forEach(System.out::println);
                        }
                        //SE não encontrar, imprime que nenhum livro foi encontrado
                        else {
                            System.out.println("Nenhum livro encontrado para a língua: " + language);
                        }
                    }
                //Exceção de erro de conversão de string para número
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, digite um número.");
                    //Resolução de 'bug' de looping de pedido de digitação
                    input.nextLine();
                }
            }
        }
    }
}