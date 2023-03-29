public enum API {
  
  IMDB_TOP_MOVIES(
    "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", 
    new ExtratorConteudoIMDB()
  ),
  NASA(
    "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2004-05-01&end_date=2004-05-3", 
    new ExtratorConteudoNasa()
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
