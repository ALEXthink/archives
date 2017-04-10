package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Game;
import core.Matrix;

/**
 * �����࣬ʵ����Ϸ����������
 * @author duan
 * @version 2.0
 */
public class Window extends JFrame implements KeyListener,MouseListener,MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8076255828372462197L;
		//һЩ����
		public static final int wndWidth=640;
		public static final int wndHeight=480;

		public static final int matWidth=Matrix.Width;
		public static final int matHeight=Matrix.Height;
		public static final String[] tileFiles={"res/2.png","res/4.png","res/8.png",
												"res/16.png","res/32.png","res/64.png",
												"res/128.png","res/256.png","res/512.png",
												"res/1024.png","res/2048.png","res/4096.png",
												"res/8192.png","res/16384.png",};
		//�ؼ�
		private JPanel pnMain;
		private JPanel pnPerform;
		private JLabel lbScore;
		private JButton btRestart;
		private JButton btAi;
		/**
		 * ���ںͿؼ��Ĺ���
		 * @throws Exception
		 */
		public Window() throws Exception{
			pnMain=new ImagePanel("res/mainback.png");
			pnPerform=new PerformPanel("res/back.jpg",tileFiles);
			lbScore=new JLabel("����:");
			btRestart=new ImageButton("res/Restart-1.png","res/Restart-1.png","res/Restart-2.png");
			btAi=new ImageButton("res/Auto Run-1.png","res/Auto Run-1.png","res/Auto Run-2.png");
			//���þ��Բ���
			getContentPane().setLayout(new BorderLayout(0, 0));
			getContentPane().add(pnMain);
			pnMain.setLayout(null);
			
			pnMain.add(pnPerform);
			pnMain.add(lbScore);
			pnMain.add(btRestart);
			pnMain.add(btAi);
			
			this.addKeyListener(this);
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			btRestart.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.theGame.restart();
				}
				
			});
			btAi.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.theGame.startAi();;
				}
				
			});			adjust();
			lbScore.setFocusable(false);
			btRestart.setFocusable(false);
			btAi.setFocusable(false);
			setFocusable(true);
		}
		/**
		 * ���ڡ��ؼ�����
		 */
		public void adjust(){
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//�رմ������˳�
			this.setResizable(false);	//��ֹ������С
			this.setUndecorated(true);	//ȥ��������
			//������һЩ��Сλ�õ���
			this.setSize(wndWidth, wndHeight);
			final double consoleX=0.75;
			final double buttonW=0.2;
			final double buttonH=0.15;
			final double labelW=buttonW;
			final double labelH=buttonH;
			pnPerform.setLocation((int)(0.1*wndWidth), (int)(0.1*wndHeight));
			pnPerform.setSize	((int)(0.6*wndWidth), (int)(0.8*wndHeight));
			
			lbScore.setLocation((int)(consoleX*wndWidth), (int)(0.1*wndHeight));
			lbScore.setSize	((int)(labelW*wndWidth), (int)(labelH*wndHeight));
			
			lbScore.setFont(new Font("����",0,24));
			
			btRestart.setLocation((int)(consoleX*wndWidth), (int)(0.5*wndHeight));
			btRestart.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndHeight));
			
			btAi.setLocation((int)(consoleX*wndWidth), (int)(0.7*wndHeight));
			btAi.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndHeight));
			
			this.setVisible(true);
			
		}
		/**
		 * �������ݸ��´���
		 * @param matrix С�����־���
		 * @param score ����
		 */
		public void refresh(int[][] matrix,int score){
			((PerformPanel) pnPerform).setMatrix(matrix);
			lbScore.setText("����:"+Integer.toString(score));
			pnPerform.repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				Game.theGame.moveLeft();
				break;
			case KeyEvent.VK_UP:
				Game.theGame.moveUp();
				break;
			case KeyEvent.VK_RIGHT:
				Game.theGame.moveRight();
				break;
			case KeyEvent.VK_DOWN:
				Game.theGame.moveDown();
				break;
			}				
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		/**
		 * ���������ק�������
		 */
		private Point downPoint;
		private boolean drag=false; 	//������ק
		private boolean action=false;//����ƶ�����
		/**
		 * ������ھ����ʱ��ʵ����궯���ƶ�������ʵ����ק
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			downPoint=e.getPoint();
			if(e.getX()>=pnPerform.getX()
				&& e.getX()<pnPerform.getX()+pnPerform.getWidth()
				&& e.getY()>=pnPerform.getY()
				&& e.getY()<pnPerform.getY()+pnPerform.getHeight()){
				action=true;
			}
			else {
				drag=true;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			drag=false;
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			/**
			 * ʵ�������ק
			 */
			Point p = this.getLocation();
			if(drag){
				this.setLocation(p.x + e.getX() - downPoint.x, p.y + e.getY() - downPoint.y);
					
			}else if(action && e.getPoint().distance(downPoint.x,downPoint.y)>30){
				/**
				 * ʵ�ֶ���
				 */
				int dx=e.getX()-downPoint.x;
				int dy=e.getY()-downPoint.y;
				if(Math.abs(dx)>Math.abs(dy)){//�Ƚ�ˮƽ�ʹ�ֱ�ƶ�����
					if(dx<0)
						Game.theGame.moveLeft();
					else 
						Game.theGame.moveRight();
				}else{
					if(dy<0)
						Game.theGame.moveUp();
					else 
						Game.theGame.moveDown();
				}
				action=false;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}
}
