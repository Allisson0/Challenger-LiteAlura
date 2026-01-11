package br.com.litealura.Service;

import br.com.litealura.Model.Exception.AcessoAPIException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPIGutendex {

    //Cria um cliente para requisição de API
    private HttpClient client = HttpClient.newHttpClient();

    //======= REALIZAR CONSUMO DA API =======
    public String consumirAPI(String address){
        //Realiza a requisição no endereço acima
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();

        //Cria a resposta
        HttpResponse<String> response;

        //Tenta recuperar a resposta do endereço da requisição pelo cliente
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        //SE não conseguir, retorna um erro
        } catch (IOException | InterruptedException e){
            throw new AcessoAPIException("Erro no acesso da API." + e.getMessage());
        }

        //SE não der erro, retorna o json em formato de 'String'
        return response.body();
    }
}
