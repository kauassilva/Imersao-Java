
/*
 * Essa classe define uma exceção personalizada, 'ClienteHttpException', que estende a classe 'RuntimeException'.
 */
public class ClienteHttpException extends RuntimeException {

  /*
   * O construtor da classe aceita uma mensagem de erro como parâmetro e a pasa para o construtor da classe pai
   * 'RuntimeException', que define a mensagem de erro associada à exceção.
   */
  public ClienteHttpException(String message) {
    super(message);
  }

}
