package gui;

import java.awt.Graphics;
import java.awt.Image;
/**
 * �������ר����
 * @author duan
 * @version 1.1
 */
public class PerformPanel extends ImagePanel{

	private static final long serialVersionUID = -3683914630705948513L;
	private Image[] tileImages;
	private int[][] matrix;
	/**
	 * 
	 * @param backImage ����ͼ��
	 * @param tiles	С��ͼ��
	 * @throws Exception 
	 */
	public PerformPanel(Image backImg,Image[] tiles) {
		super(backImg);
		tileImages=tiles;
		matrix=null;
	}
	/**
	 * ��ͼƬ�ļ����ķ�ʽ��ʼ��
	 * @param backFile ����ͼƬ
	 * @param tileFiles С��ͼƬ
	 * @throws Exception 
	 */
	public PerformPanel(String backFile,String[] tileFiles){
		super(backFile);
		tileImages=new Image[tileFiles.length];
		for(int i=0;i<tileFiles.length;i++){
			tileImages[i]=Resource.loadImage(tileFiles[i]);
		}
		matrix=null;
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
		if(f==0)
			return null;
		int id=(int)(Math.log(f)/Math.log(2));
		return tileImages[id-1];
	}
	/**
	 * �ػ�������С��
	 */
	protected void paintComponent(Graphics g) {
		int w=getWidth();
		int h=getHeight();
		int tw=w/Window.matWidth;
		int th=h/Window.matHeight;
		super.paintComponent(g);
		if(matrix!=null){
			for(int i=0;i<Window.matHeight;i++){
				for(int j=0;j<Window.matWidth;j++){
					Image tileimg=getTileImg(j,i);
					if(tileimg!=null)
						g.drawImage(tileimg, j*tw, i*th, tw, th,null);
				}
			}
		}
	}
}
