package br.com.litealura.Model.Interface;

import java.util.List;

//Interface de conversão de dados
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
