import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Essa classe é usada para analisar um JSON e transformá-lo em uma lista de mapas de Strings, onde cada mapa
 * representa um objeto JSON e cada chave do mapa representa um atributo do objeto JSON com seu respectivo valor.
 */
public class JsonParser {

  private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
  private static final Pattern REGEX_ATTRIBUTES_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
  
  /*
   * O método utiliza duas expressões regulares para realizar a análise do JSON. A primeira expressão regular é
   * usada para extrair o conteúdo dentro dos colchetes '[' e ']' do JSON e a segunda expressão regular é usada
   * para extrair os atributos e seus valores dentro de cada objeto JSON.
   * Além disso, O método usa o método 'Matcher.find()' para procurar a ocorrência dos colchetes no JSON. Se esses
   * colchetes não forem encontrados, uma exceção será lançada indicando que os itens não foram encontrados. Depois
   * disso, o método divide a String JSON em uma matriz de itens, onde cada item representa um objeto JSON.
   */
  public List<Map<String, String>> parse(String json) {
    Matcher matcher = REGEX_ITEMS.matcher(json);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Não encontrou items.");
    }

    String[] items = matcher.group(1).split("\\},\\{");

    List<Map<String, String>> data = new ArrayList<>();

    /*
     * Para cada item na matriz de itens, o método utiliza o Matcher novamente para extrair os atributos e seus
     * respectivos valores, e adiciona esses atributos e valores a um mapa de Strings. O mapa é adicionado a uma
     * lista, que é retornada quando todos os itens são processados.
     */
    for (String item : items) {
      Map<String, String> itemAttribute = new HashMap<>();

      Matcher matcherAttributeJson = REGEX_ATTRIBUTES_JSON.matcher(item);
      
      while (matcherAttributeJson.find()) {
        String attribute = matcherAttributeJson.group(1);
        String value = matcherAttributeJson.group(2);
        itemAttribute.put(attribute, value);
      }

      data.add(itemAttribute);
    }

    return data;
  }

}
