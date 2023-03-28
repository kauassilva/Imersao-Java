import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

/*
 * A classe realiza uma chamada a uma API para obter dados sobre os filmes mais populares. Ele usa a classe
 * HttpClient para fazer uma solicitação GET a um URL que aponta para um arquivo JSON que contém informações
 * sobre esses filmes. Ele armazena a resposta como uma String usando a classe HttpResponse e o manipulador
 * de corpo BodyHandlers
 */
public class App {

    public static void main(String[] args) throws Exception {
        /*
         * Fazer uma conexão HTTP e buscar os top filmes
         * ----------------------------------------
         */ 
        // Define a URL do arquivo JSON
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // Cria um objeto URI com a URL
        URI address = URI.create(url);
        // Cria um cliente HTTP
        var client = HttpClient.newHttpClient();
        // Cria uma solicitação HTTP GET com o URI definido
        var request = HttpRequest.newBuilder(address).GET().build();
        // Envia a solicitação e obtém a resposta como uma String
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        // Armazena a String de resposta em uma variável
        String body = response.body();
        // Imprime a String de resposta na saída padrão (console)
        //System.out.println(body);


        /*
         * Extrair só os dados que interessam (Título, poster, classificação)
         * ----------------------------------------
         */
        // Cria um objeto JsonParser para analisar a resposta da API e extrair os dados dos filmes
        var parser = new JsonParser();
        // Analisa a String JSON da resposta e retorna uma lista de mapas contendo informações dos filmes
        List<Map<String, String>> movieList = parser.parse(body);

        /*
         * Exibir e manipular os dados
         * ----------------------------------------
         */
        for (Map<String, String> movie : movieList) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }

    }

}
