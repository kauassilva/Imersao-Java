import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDB implements ExtratorConteudo {
  
  public List<Conteudo> extraiConteudos(String json) {
    // Extrair os dados que interessam
    var parser = new JsonParser();
    List<Map<String, String>> listaAtributos = parser.parse(json);

    // Popular a lista de conteudos
    return listaAtributos.stream()
      .map(atributo -> new Conteudo(atributo.get("title"), atributo.get("image")))
      .toList();
  }

}
