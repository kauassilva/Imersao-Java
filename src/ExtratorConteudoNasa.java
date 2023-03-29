import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo {

  /*
   * Extrair só os dados que interessam (Título, poster)
   * ----------------------------------------
   */
  public List<Conteudo> extraiConteudos(String json) {
    var parser = new JsonParser();
    List<Map<String, String>> listaAtributos = parser.parse(json);

    List<Conteudo>  listaConteudos = new ArrayList<>();

    /*
     * Popular a lista de conteudos
     * ----------------------------------------
     */
    for (Map<String, String> atributos : listaAtributos) {
      String titulo = atributos.get("title");
      String urlImagem = atributos.get("url");

      var conteudo = new Conteudo(titulo, urlImagem);

      listaConteudos.add(conteudo);
    }

    return listaConteudos;
  }

}
