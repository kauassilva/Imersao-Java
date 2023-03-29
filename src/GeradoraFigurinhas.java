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
 * Essa classe é responsável por criar novas figurinhas a partir de uma imagem original e adicionando o texto
 * "TOPZERA" na parte inferior da imagem. A classe usa a Classe 'Graphics2D' para manipulação de imagens e texto.
 */
public class GeradoraFigurinhas {

  /**
   * O método recebe um InputStream de uma imagem original e um nome para o arquivo da nova imagem gerada. Primeiro,
   * a imagem original é lida usando a classe ImageIO e suas dimensões são obtidas. Em seguida, é criada uma nova
   * imagem com a mesma largura que a original, mas com uma altura adicional de 200 pixels.
   */
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
    
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    /*
     * O método então desenha a imagem original na nova imagem usando o método 'Graphics2D.drawImage()'.
     */
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    /*
     * Em seguida, ele configura uma fonte, uma cor e desenha o texto 'TOPZERA' na parte inferior da nova imagem.
     */
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    String texto = "TOPZERA";
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();

    int posicaoTextoX = (largura - larguraTexto) / 2;
    int posicaoTextoY = (novaAltura - 100);

    graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

    /*
     * Em seguida, o método cria um TextLayout do texto e obtém a sua forma para usar como contorno. É aplicada uma
     * transformação na nova imagem para que o contorno seja desenhado na posição correta.
     */
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
     * Finalmente, a nova imagem é salva em um arquivo com o nome especificado pelo usuário usando 'ImageIO.write()'.
     */
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

  }

}
