import java.util.List;
import java.util.Map;

/*
 * Esta classe implementa a Interface 'ExtratorConteudo' e usa um objeto 'JsonParser' para extrair o conteúdo 
 * do JSON da API do IMDB. Em seguida, ele cria uma lista de objetos 'Conteudo' a partir dos dados extraídos
 * do JSON e retorna essa lista.
 */
public class ExtratorConteudoIMDB implements ExtratorConteudo {
  
  /*
   * O método cria uma instância de JsonParser e usa seu método 'parse()' para obter uma lista de mapas de String 
   * que representam os objetos JSON do IMDB. Em seguida, a lista de mapas de Strings é convertida em uma lista 
   * de objetos 'Conteudo' usando o método 'map' da Interface Stream.
   */
  public List<Conteudo> extraiConteudos(String json) {

    var parser = new JsonParser();
    List<Map<String, String>> listaAtributos = parser.parse(json);

    /*
     * O método 'map()' transforma cada mapa de Strings em um objeto 'Conteudo', onde o titulo e a URL da imagem
     * são extraídos do mapa usando o método 'get()'. Por fim, o método 'toList()' é chamado para converter a Stream 
     * resultante em uma lista de objetos 'Conteudo'.
     */
    return listaAtributos.stream()
      .map(atributo -> new Conteudo(atributo.get("title"), atributo.get("image")))
      .toList();

  }

}
