import java.io.InputStream;
import java.net.URL;
import java.util.List;

/*
 * A classe realiza uma chamada a uma API para obter dados sobre os filmes mais populares. Em seguida, faz o download
 * das imagens dos filmes encontrados e, em seguida, usa uma classe chamada 'GeradoraDeFigurinhas' para criar
 * figurinhas com as informações dos filmes. O código, uma nova pasta 'figurinhas' no diretório atual e salva as
 * figurinhas nessa pasta. As informações sobre os filmes são exibidas no console durante o processo.
 */
public class App {

    public static void main(String[] args) throws Exception {
        /*
         * Define a API a ser utilizada (API.NASA ou API.IMDB_TOP_MOVIES) e a URL correspondente.
         */ 
        API api = API.LANGUAGES;
        String url = api.getUrl();

        /*
         * Obtém o 'ExtratorConteudo' a ser utilizado a partir da API.
         */
        ExtratorConteudo extrator = api.getExtrator();
        
        /*
         * Utiliza a classe ClienteHttp para fazer uma requisição HTTP à URL definida e obter um JSON como resposta.
         */
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        /*
         * Utiliza o ExtratorConteudo para extrair uma lista de conteúdos a partir do JSON obtido.
         */
        List<Conteudo> listaConteudos = extrator.extraiConteudos(json);

        /*
         * Utiliza a classe 'GeradoraFigurinhas' para gerar até três figurinhas a partir das imagens dos três
         * primeiros conteúdos da lista.
         */
        var geradora = new GeradoraFigurinhas();

        for (int i=0; i<3; i++) {
            Conteudo conteudo = listaConteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/"+conteudo.titulo()+".png";

            geradora.cria(inputStream, nomeArquivo); 

            /*
             * Imprime o título e a URL da imagem de cada Conteúdo.
             */
            System.out.println();
            System.out.println("\u001b[1m\u001b[33mTítulo:\u001b[m "+conteudo.titulo());
            System.out.println("\u001b[1m\u001b[33mURL da imagem:\u001b[m "+conteudo.urlImagem());
        }

    }

}
