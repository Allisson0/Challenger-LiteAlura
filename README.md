<div>
  <h1 align="center">
    :blue_book: LiteAlura :blue_book:
    <br>
    <img width="851" height="315" align="center" alt="Dois livros um em cima do outro, com um chapéu de universitário em cima dele, logo abaixo o nome do projeto 'LiteAlura' e o autor" src="https://github.com/user-attachments/assets/b6513e76-1cf8-4640-94eb-525e2ee502fa" />
  </h1>
</div>

<p align = "center">
  <img alt="Status em desenvolvimento" src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-green"> <img alt="Java 25 LTS" src="https://img.shields.io/badge/JDK-25%20LTS-blue"> <img alt="Challenger da alura" src="https://img.shields.io/badge/Alura-Challenger-darkblue"> <img alt="versão spring" src="https://img.shields.io/badge/Spring-4.0.1-lime"> <br><img alt="Gutendex" src="https://img.shields.io/badge/API-Gutendex-chocolate"> <img alt="Jackson" src="https://img.shields.io/badge/Jackson-2.20.1-brown"> <img alt="banco de dados" src="https://img.shields.io/badge/BD-postgreSQL-lavender">
</p>

<h1 id="desc">Descrição do projeto :books:</h1> 
<p>
  O projeto a seguir, é parte de um challenger do curso Spring Boot da alura com Oracle One. O mesmo é uma forma de aplicarmos os conhecimentos adquiridos durante os cursos e aplica-los em um projeto prático. O challenger da LiteAlura, é feito se baseando na Api <a>Gutendex</a>, que possui uma biblioteca de livros gratuitos online, onde precisaremos criar um banco de dados para procurar livros dentro dessa API e salvar dentro do banco de dados criado. O mesmo possui funções de pesquisa de livro, pesquisa de autor, visualização dos livros e autores no banco de dados e pesquisa por lingua de livro. A aplicação usufrui de boas práticas de programação e é construida guardar os livros pesquisados e os autores do livro automaticamente.
</p>

<h2>Sumário :bookmark_tabs:</h2>
<ul>
  <li><a href="#desc">Descrição do projeto</a></li>
  <li><a href="funcs">Funcionalidades e aplicação</a></li>
  <li><a href="docs">Documentação</a></li>
  <li><a href="acss">Acesso ao projeto</a></li>
  <li><a href="license">Licença</a></li>
  <li><a href="conc">Conclusão</a></li>
</ul>

<h1 id="funcs">Funcionalidades e aplicação :hammer_and_wrench:</h1>
<p>
  O projeto segue permite ao usuário realizar 5 principais ações:<br>
  <div align="center">
    <img  width="411" height="228" alt="funcionalidades" src="https://github.com/user-attachments/assets/0ee0c0c9-fb43-43d3-8289-d860c98656dd" />
  </div>
  
  <ul>
    <li>
      <strong>Funcionalidade 1:</strong> Buscar livros pelo titulo e salvar no banco de dados <br><br>
      <div>
        <img width="288" height="231" alt="buscar livro pelo titulo" src="https://github.com/user-attachments/assets/057119fc-4b86-4f13-ac2b-02bb5b71099f" />
      </div>
    </li>
    <li>
      <strong>Funcionalidade 2:</strong> Listar livros registrados
      <div>
        <img width="312" height="342" alt="listar livros do banco de dados" src="https://github.com/user-attachments/assets/7e26b50f-4d55-45f7-a0a0-9d337fc34a0d" />
      </div>
    </li>
    <li>
      <strong>Funcionalidade 3:</strong> Listar autores registrados
      <div>
        <img width="246" height="228" alt="listar autores registrados" src="https://github.com/user-attachments/assets/454949cf-0b92-49f8-ad3b-74d08bc2a117" />
      </div>
    </li>
    <li>
      <strong>Funcionalidade 4:</strong> Listar autores vivos em um determinado ano
      <div>
        <img width="428" height="215" alt="autores vivos em um determinado ano" src="https://github.com/user-attachments/assets/c23ecec5-864e-43cb-a929-157adf5461aa" />
      </div>
    </li>
    <li>
      <strong>Funcionalidade 5:</strong> Listar livros de um determinado idioma
      <div>
        <img width="307" height="473" alt="Listar livros de um determinado idioma" src="https://github.com/user-attachments/assets/5074823a-f2ea-4616-8b93-41289fc1e974" />
      </div>
    </li>
  </ul>
</p>



<h1 id="docs">Documentação :open_book:</h1>
<div>
  <p>O projeto foi criado a partir do Spring 4.0.1, usando o banco de dados PostgresSQL e utilizando da API do gutendex para acesso gratuito dos livros. O acesso da api pode ser encontrado por meio deste link: <a href="https://gutendex.com">Clique aqui</a>. Além do uso da API Jackson Databind 2.20.1 que converte textos json em objetos do java.
  </p>

<p>Árvore de arquivos: :evergreen_tree:</p>
  <ul>
    <li>Main</li>
    <li>
      Model
      <ul>
        <li>
          Exception
          <ul>
            <li>AcessoApiException</li>
          </ul>
        </li>
        <li>
          Interface
          <ul>
            <li>IConverteDados</li>
          </ul>
        </li>
        <li>
          Record
          <ul>
            <li>ConsultaGutendex</li>
          </ul>
        </li>
        <li>AuthorBook</li>
        <li>Book</li>
      </ul>
    </li>
    <li>
      Repository
      <ul>
        <li>BookRepository</li>
      </ul>
    </li>
    <li>
      Service
      <ul>
        <li>ConsumoApiGutendex</li>
        <li>ConverteDados</li>
        <li>GutendexService</li>
      </ul>
    </li>
    <li>LiteAluraApplication</li>
  </ul>
</div>

<h2>Main :house:</h2>
<p>Pasta que contem a classe main, onde é apresentado o menu e contém os principais métodos de acesso das ações.</p>

<h2>Model :toolbox:</h2>
<p>
  Pasta onde contém as principais classes padrão da aplicação (Classes, Records, Interfaces e Exceptions).<br>
  <h3>Exception</h3>
  Contém a exception de acesso da api com erro.<br>
  <h3>Interface</h3>
  Contém uma interface de conversão de dados.<br>
  <h3>Record</h3>
  Contém um record de consultaGutendex (pois a api gutendex retorna um json com alguns atributos, do qual utilizamos apenas "results" que tem uma lista de livros).<br>
  <h3>Classes</h3>
  Author que recupera as informações padrão do author e relaciona com o banco de dados. <br>
  Book que recupera as informações da lista de livros da api, cria uma relação de chave estrangeira com author (ManyToMany) e salva no banco de dados.
</p>

<h2>Repository :floppy_disk:</h2>
<p>
  Pasta que contém uma interface de uso da JPA, para acesso ao banco de dados. O mesmo tem as ações de busca por nome do autor, busca por id de livro, busca por autores, busca de autores vivos em determinado ano e busca por código de língua.
</p>

<h2>Service :hammer:</h2>
<p>
  A pasta contém 3 classes principais, sendo elas: ConsumoAPIGutendex, que realizar a consulta na API com base num endereço; ConverteDados, que converte dados e listas da API para uma classe determinada; e GutendexService que realiza a pesquisa na API através de ConsumoAPIGutendex e retorna a resposta encontrada com base no nome livro ou semelhantes.
</p>

<h2>LiteAluraApplication :computer:</h2>
<p>Classe gerenciada pelo Spring, onde roda a aplicação por meio do CommadLineRunner e o método run. Também intancia um objeto gerenciado pelo Spring, sendo ele a interface BookRepository.</p>

<h1 id="acss">Acesso ao projeto :open_file_folder:</h1>

<h1 id="license">Licença :pushpin:</h1>
<p>Este projeto foi criado para fins didáticos e para compor o challenger necessário do curso Oracle One + Alura. <br> Criado por Allisson Silva.</p>

<h1 id="conc">Conclusão :chart_with_upwards_trend:</h1>
