import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

/*
 * Esta classe contém um método 'cria()'. O objetivo deste método é criar uma nova imagem a partir de uma imagem
 * original, escrevendo uma frase em baixo da imagem e salvando-a em um arquivo.
 */
public class GeradoraFigurinhas {

  /**
   * O método recebe como parâmetros um objeto 'InputStream' que representa a imagem original e uma 'String' com
   * o nome do arquivo de saída. A imagem original é lida com o método 'ImageIO.read()' e armazenada em um objeto
   * 'BufferedImage'.
   * Em seguida, é criado um novo objeto BufferedImage com a mesma largura da imagem original e altura aumentada
   * em 200 pixels. A imagem original é copiada para a nova imagem usando o método 'Graphics.drawImage()'.
   * Depois disso, é configurada a fonte para a frase a ser escrita na imagem. A frase é definida como "TOPZERA"
   * e escrita na nova imagem usando o método 'Graphics.drawString()'. Em seguida, é criado um contorno em torno
   * da frase usando os métodos 'TextLayout.getOutline()' e 'Graphics.draw()'. Finalmente, a nova imagem é escrita
   * em um arquivo com o nome específico usando o método 'ImageIO.write()'.
   */
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
    
    /*
     * Leitura da imagem
     * ----------------------------------------
     */
    //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
    //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);


    /*
     * Cria nova imagem em memória com transparência e com tamanho novo
     * ----------------------------------------
     */
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    
    /*
     * Copiar imagem original pra nova imagem (em memória)
     * ----------------------------------------
     */
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);


    /*
     * Configurar a fonte
     * ----------------------------------------
     */
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

 
    /*
     * Escrever uma frase na nova imagem
     * ----------------------------------------
     */
    String texto = "TOPZERA";
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();

    int posicaoTextoX = (largura - larguraTexto) / 2;
    int posicaoTextoY = (novaAltura - 100);

    graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    var textLayout = new TextLayout(texto, fonte, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(posicaoTextoX, posicaoTextoY);
    graphics.setTransform(transform);

    BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
    graphics.setStroke(outlineStroke);

    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);


    /*
     * Escrever a nova imagem em um arquivo
     * ----------------------------------------
     */
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

  }

}
