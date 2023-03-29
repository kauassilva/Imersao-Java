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
        * Extrair dados do IMDB ou da NASA
        * ----------------------------------------
        */ 
        API api = API.NASA;
        String url = api.getUrl();

        ExtratorConteudo extrator = api.getExtrator();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        /*
         * Exibir e manipular os dados
         * ----------------------------------------
         */
        List<Conteudo> listaConteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraFigurinhas();

        for (int i=0; i<3; i++) {
            Conteudo conteudo = listaConteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/"+conteudo.titulo()+".png";

            geradora.cria(inputStream, nomeArquivo); 

            System.out.println();
            System.out.println("\u001b[1m\u001b[33mTítulo:\u001b[m "+conteudo.titulo());
            // Imprime a URL da imagem
            //System.out.println("\u001b[1m\u001b[33mURL da imagem:\u001b[m "+conteudo.getUrlImagem());
        }

    }

}
