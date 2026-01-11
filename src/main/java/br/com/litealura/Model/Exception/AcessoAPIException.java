package br.com.litealura.Model.Exception;

//Classe para retornar erro no acesso da API
public class AcessoAPIException extends RuntimeException {
    private String message;
    public AcessoAPIException(String erro){
        this.message = erro;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
