package br.com.litealura.Service;

import br.com.litealura.Model.Interface.IConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    //======= CONVERTE JSON PARA UM OBJETO =======
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    //======= CONVERTE JSON PARA UMA LISTA DE OBJETOS =======
    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try{
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
