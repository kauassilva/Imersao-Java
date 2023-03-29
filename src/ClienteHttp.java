import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/*
 * Essa classe define um cliente HTTP simples que pode ser usado para fazer solicitações GET para URLs e retornar o
 * conteúdo da resposta como uma String.
 */
public class ClienteHttp {

  /*
   * O método aceita uma String que representa a URL a ser consultada. Ele tenta criar um objeto 'URI' usando a 
   * URL, em seguida, usa a classe 'HttpClient' para criar um cliente HTTP. Um objeto 'httpRequest' é construído
   * para solicitar o recurso na URL fornecida usando o método GET. Em seguida, o método 'send' do cliente HTTP
   * é chamado para enviar a solicitação e obter uma resposta. A resposta é lida como uma String usando o 
   * 'BoduHandlers.ofString()' e retornada. 
   */
  public String buscaDados(String url) {
    try {
      URI address = URI.create(url);
      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder(address).GET().build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      String body = response.body();
      return body;
    } 
    /*
     * Se ocorrer uma exceção durante a criação da solicitação HTTP, uma 'ClienteHttpException' é lançada com uma
     * mensagem de erro indicando que houve erro ao consultar a URL. As exceções que podem ser lançadas são
     * 'IOException' e 'InterruptedException'.
     */
    catch (IOException | InterruptedException ex) {
      throw new ClienteHttpException("Erro ao consultar a URL...");
    }

  }

}
