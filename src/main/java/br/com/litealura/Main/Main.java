package br.com.litealura.Main;

import br.com.litealura.Model.Book;
import br.com.litealura.Service.GutendexService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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
            }

            //Com base na escolha, realiza uma ação.
            switch (choose){
                case 1:
                    procuraLivroPorNome();
                    break;

                case 2:
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
    private void procuraLivroPorNome(){
        //Pede um livro para encontrar na API
        System.out.println("Digite um livro para pesquisa: ");
        String busca = input.nextLine();
        //Retorna o livro encontrado através do serviço de trabalho gutendex.
        Book livroEncontrado = gutendex.buscaLivro(busca);
        System.out.println(livroEncontrado);
    }
}
