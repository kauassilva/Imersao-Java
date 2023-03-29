import java.util.List;

/*
 * Essa Interface declara um método chamado 'extraiConteudos'. A interface contém uma única assinatura de método que
 * recebe uma String 'json' como parâmetro e retorna uma lista de objetos do tipo 'Conteudo'.
 */
public interface ExtratorConteudo {
  
  /*
   * O método deve ser implementado por qualquer classe que implemente essa Interface, para que psosa extrair o
   * conteúdo do JSON e retornar uma lista de objetos do tipo 'Conteudo'.
   * Cada objeto 'Conteudo' contém atributos como 'titulo' e 'urlImagem'.
   */
  List<Conteudo> extraiConteudos(String json);

}
