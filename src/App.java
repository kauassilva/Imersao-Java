import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        /*
         * Extrair só os dados que interessam (Título, poster, classificação)
         * ----------------------------------------
         */
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);


        /*
         * Exibir e manipular os dados
         * ----------------------------------------
         */
        var geradora = new GeradoraDeFigurinhas();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        for (Map<String, String> movie : movieList) {
            String urlImagem = movie.get("image");
            String titulo = movie.get("title");
            String nota = movie.get("imDbRating");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/"+titulo+".png";

            geradora.cria(inputStream, nomeArquivo); 

            System.out.println();
            System.out.println("\u001b[1m\u001b[33mTítulo:\u001b[m "+titulo);
            System.out.println("\u001b[1m\u001b[33mURL da imagem:\u001b[m "+urlImagem);
            System.out.println("\u001b[1m\u001b[33mNota:\u001b[m "+nota);
        }

    }

}
