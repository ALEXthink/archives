package gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * ��ֻ��һ����ͼƬ��Panel!
 * @author duan
 *
 */
public class ImagePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7910110413151813590L;
	protected Image img;
	public ImagePanel(Image image){
		img=image;
	}
	/**
	 * ���ļ��м���ͼƬ
	 * @param file
	 */
	public ImagePanel(String file){
		super();
		img=Resource.loadImage(file);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}

}
