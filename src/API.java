/*
 * Essa Enum define dois valores possíveis, 'IMDB_TOP_MOVIES' e 'NASA', que representam APIs diferentes. Cada
 * valor é inicializado com uma URL e um extrator de conteúdo associado a essa API.
 * Essa Enum é útil para representar APIs diferentes de maneira estruturada e para facilitar o uso das APIs no 
 * programa, pois centraliza a definição da URL e do extrator de conteúdo associados a cada API.
 */
public enum API {
  
  IMDB_TOP_MOVIES(
    "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", 
    new ExtratorConteudoIMDB()
  ),
  NASA(
    "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2004-05-01&end_date=2004-05-3", 
    new ExtratorConteudoNasa()
  ),
  LANGUAGES(
    "http://localhost:8080/linguagens",
    new ExtratorConteudoIMDB()
  );

  private String url;
  private ExtratorConteudo extrator;

  API(String url, ExtratorConteudo extrator) {
    this.url = url;
    this.extrator = extrator;
  }

  public String getUrl() {
    return url;
  }

  public ExtratorConteudo getExtrator() {
    return extrator;
  }

}
