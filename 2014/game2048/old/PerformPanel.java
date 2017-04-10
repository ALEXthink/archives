package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
/**
 * �������ר����
 * @author duan
 * @version 1.0
 */
public class PerformPanel extends JPanel{

	private static final long serialVersionUID = -3683914630705948513L;
	private Image backImage;
	private Image[] tileImages;
	private Image buffImage;//����ͼƬ������ʵ��˫����
	private Graphics buffGrap;
	private int width,height;
	private int[][] matrix;
	/**
	 * 
	 * @param backImage ����ͼ��
	 * @param tiles	С��ͼ��
	 * @throws Exception 
	 */
	public PerformPanel(Image backImg,Image[] tiles,int w,int h) throws Exception {
		backImage=backImg;
		tileImages=tiles;
		width=w;
		height=h;
		createBuffer();
	}
	/**
	 * ��ͼƬ�ļ����ķ�ʽ��ʼ��
	 * @param backFile ����ͼƬ
	 * @param tileFiles С��ͼƬ
	 * @throws Exception 
	 */
	public PerformPanel(String backFile,String[] tileFiles,int w,int h) throws Exception{
		backImage=getToolkit().getImage(backFile);
		tileImages=new Image[tileFiles.length];
		for(int i=0;i<tileFiles.length;i++){
			tileImages[i]=getToolkit().getImage(tileFiles[i]);
		}
		width=w;
		height=h;
		createBuffer();
	}
	/**
	 * ���¾�����������
	 * @param m ע���СҪһ��
	 */
	public void setMatrix(int[][] m){
		matrix=m;
	}
	/**
	 * ����ھ���ָ������Ӧ���ֵ�ͼ��
	 * @param x
	 * @param y
	 * @return 
	 * @return 
	 */
	private Image getTileImg(int x,int y){
		int f=matrix[y][x];
		int id=(int)(Math.log(f)/Math.log(2));
		return tileImages[id-1];
	}
	private void createBuffer(){
		this.setVisible(true);
		buffImage=createImage(width,height);
		buffGrap=buffImage.getGraphics();
	}
	/**
	 * ˫���弼�����ػ�������С��
	 */
	protected void paintComponent(Graphics g) {
		int w=width;
		int h=height;
		int tw=w/Window.matWidth;
		int th=h/Window.matHeight;
		buffGrap.drawImage(backImage, 0, 0, w,h,null);
		for(int i=0;i<Window.matHeight;i++){
			for(int j=0;j<Window.matWidth;j++){
				buffGrap.drawImage(getTileImg(j,i), j*tw, i*th, tw, th,null);
			}
		}
		g.drawImage(buffImage, 0, 0, w, h, null);
		buffGrap.clearRect(0, 0, w, h);
	}
}
