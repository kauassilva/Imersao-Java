/*
 * A classe Record chamada 'Conteudo', tem dois campos como parâmetros para o construtor, o "titulo" e "urlImagem".
 * Como um Record é imutável, esses valores não podem ser alterados após a criação do objeto.
 */
public record Conteudo(String titulo, String urlImagem) {}


// O Record subistitui todo este código:
/*
public class Conteudo {
  
  private final String titulo;
  private final String urlImagem;

  public Conteudo(String titulo, String urlImagem) {
    this.titulo = titulo;
    this.urlImagem = urlImagem;
  }

  public String getTitulo() {
    return titulo;
  }
  public String getUrlImagem() {
    return urlImagem;
  }  

}
*/
