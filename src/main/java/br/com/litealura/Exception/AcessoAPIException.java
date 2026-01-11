package br.com.litealura.Exception;

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
