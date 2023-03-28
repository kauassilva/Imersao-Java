import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * A classe 'JsonParser' contém um método 'parse()' que recebe uma String JSON como entrada e retorna uma lista de
 * mapas que contém os atributos e seus respectivos valores do objeto JSON.
 * A classe pode ser útil para analisar e manipular objetos JSON de estrutura semelhante.
 */
public class JsonParser {

  private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
  private static final Pattern REGEX_ATTRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
  
  /*
   * O método usa expressões regulares para analisar a String JSON. A primeira expressão regular (REGEX_ITEMS)
   * encontra a lista de itens dentro do objeto JSON, enquanto a segunda expressão regular (REGEX_ATTRIBUTES_JSON)
   * encontra os atributos e seus respectivos valores dentro de cada item.
   * O método retorna uma lista de mapas, onde cada mapa representa um item na lista de itens JSON. O mapa contém
   * os atributos e seus respectivos valores, onde a chave é o nome do atributo e o valor é o valor correspondente
   * do atributo.
   * Se a lista de itens não for encontrada na String JSON, o método lança uma exceção 'IllegalArgumentException'.
   */
  public List<Map<String, String>> parse(String json) {
    // Encontra a lista de itens na String JSON usando a expressão regular
    Matcher matcher = REGEX_ITEMS.matcher(json);
    // Lança uma exceção se a lista não for encontrada na String JSON
    if (!matcher.find()) {
      throw new IllegalArgumentException("Não encontrou items.");
    }

    // Divide a lista de itens em uma matriz de Strings, onde cada elemento é um item JSON
    String[] items = matcher.group(1).split("\\},\\{");

    // Cria uma lista vazia para armazenar os mapas de atributos e valores de cada item JSON
    List<Map<String, String>> data = new ArrayList<>();

    // Loop através de cada item JSON na matriz de itens
    for (String item : items) {
      // Cria um mapa vazio para armazenar os atributos e seus respectivos valores para o item atual
      Map<String, String> itemAttribute = new HashMap<>();

      // Encontra os atributos e seus respectivos valores no item atual usando a expressão regular
      Matcher matcherAttributeJson = REGEX_ATTRIBUTES_JSON.matcher(item);
      while (matcherAttributeJson.find()) {
        // Obtém o nome do atributo e o valor correspondente
        String attribute = matcherAttributeJson.group(1);
        String value = matcherAttributeJson.group(2);
        // Adiciona o atributo e o valor correspondente ao mapa de atributos e valores para o item atual
        itemAttribute.put(attribute, value);
      }

      // Adiciona o mapa de atributos e valores do item atual à lista de dados
      data.add(itemAttribute);
    }

    // Retorna a lista de dados
    return data;
  }

}
