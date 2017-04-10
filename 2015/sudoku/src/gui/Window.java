package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import core.Game;


/**
 * �����࣬ʵ����Ϸ����������
 * @author duan
 * @version 2.0
 */
public class Window extends JFrame implements MouseListener,MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8076255828372462197L;
		//һЩ����
		public static final int wndWidth=800;
		public static final int wndHeight=600;
		//�ؼ�
		private JPanel pnMain;
		public PerformPanel pnPerform;
	//	private JLabel lbScore;
		private JButton btOpen;
		private JButton btRestart;
		private JButton btAi;
		private JButton btExit;
		private JButton btNext;
		private JLabel lbInfo;
		private JLabel lbAllNum;
		private JLabel lbTime;
		/**
		 * ���ںͿؼ��Ĺ���
		 * @throws Exception
		 */
		public Window() throws Exception{
			pnMain=new ImagePanel(Resource.imgMainback);
			pnPerform=new PerformPanel(Resource.imgPanel);
			
			lbInfo=new JLabel();
			lbAllNum=new JLabel();
			lbTime=new JLabel();
			
			
			btOpen=new JButton("��");//(imgNormal,imgEnter,imgPress);
			btOpen.setIcon(Resource.imgNormal);
			btOpen.setRolloverIcon(Resource.imgEnter);
			btOpen.setPressedIcon(Resource.imgPress);
			//btOpen.set
			btRestart=new JButton("����");
			btRestart.setIcon(Resource.imgNormal);
			btRestart.setRolloverIcon(Resource.imgEnter);
			btRestart.setPressedIcon(Resource.imgPress);
			
			btAi=new JButton("�Զ�");
			btAi.setIcon(Resource.imgNormal);
			btAi.setRolloverIcon(Resource.imgEnter);
			btAi.setPressedIcon(Resource.imgPress);
			
			btExit=new JButton("�˳�");
			btExit.setIcon(Resource.imgNormal);
			btExit.setRolloverIcon(Resource.imgEnter);
			btExit.setPressedIcon(Resource.imgPress);
			
			btNext=new JButton("��һ��");
			btNext.setIcon(Resource.imgNormal);
			btNext.setRolloverIcon(Resource.imgEnter);
			btNext.setPressedIcon(Resource.imgPress);
			btNext.setVisible(false);
			
		//	UIManager.put("Button.disabledText", Color.BLACK);
			
			//���þ��Բ���
			getContentPane().setLayout(new BorderLayout(0, 0));
			getContentPane().add(pnMain);
			pnMain.setLayout(null);
			
			pnMain.add(pnPerform);
			pnMain.add(lbInfo);
			pnMain.add(lbAllNum);
			pnMain.add(lbTime);
			pnMain.add(btOpen);
			pnMain.add(btRestart);
			pnMain.add(btAi);
			pnMain.add(btExit);
			pnMain.add(btNext);
			
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			btOpen.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(Game.loadFromFile())
						clearAI();
				}
				
			});
			btRestart.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.reset();
					clearAI();
				}
				
			});
			btAi.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.startAi();
					
				}
				
			});
			btExit.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
				
			});
			btNext.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.switchToNextSolution();
				}
				
			});
			
			adjust();
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
			this.setLocation(100,100);
			
			Font fnt=new Font("����", Font.PLAIN, 34);
			pnPerform.setLocation((int)(0.1*wndWidth), (int)(0.1*wndHeight));
			pnPerform.setSize	((int)(0.6*wndWidth), (int)(0.8*wndHeight));
			
			lbInfo.setLocation((int)(consoleX*wndWidth), (int)(0.1*wndHeight));
			lbInfo.setSize	((int)(labelW*wndWidth), (int)(labelH*wndHeight));
			lbInfo.setFont(new Font("����",Font.PLAIN,24));
			//lbInfo.setText("Test\ntest");
			
			lbAllNum.setLocation((int)(consoleX*wndWidth), (int)(0.2*wndHeight));
			lbAllNum.setSize	((int)(labelW*wndWidth), (int)(labelH*wndHeight));
			lbAllNum.setFont(new Font("����",Font.PLAIN,24));
			//lbAllNum.setText("Test\ntest");
			
			lbTime.setLocation((int)(consoleX*wndWidth), (int)(0.15*wndHeight));
			lbTime.setSize	((int)(labelW*wndWidth), (int)(labelH*wndHeight));
			lbTime.setFont(new Font("����",Font.PLAIN,24));
			//lbTime.setText("Test\ntest");
			
			btNext.setLocation((int)(consoleX*wndWidth), (int)(0.32*wndHeight));
			btNext.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndWidth/2));
			btNext.setFont(fnt);
			btNext.setHorizontalTextPosition(JButton.CENTER);
			
			btOpen.setLocation((int)(consoleX*wndWidth), (int)(0.44*wndHeight));
			btOpen.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndWidth/2));
			btOpen.setFont(fnt);
			btOpen.setHorizontalTextPosition(JButton.CENTER);
			
			btRestart.setLocation((int)(consoleX*wndWidth), (int)(0.56*wndHeight));
			btRestart.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndWidth/2));
			btRestart.setFont(fnt);
			btRestart.setHorizontalTextPosition(JButton.CENTER);

			btAi.setLocation((int)(consoleX*wndWidth), (int)(0.68*wndHeight));
			btAi.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndWidth/2));
			btAi.setFont(fnt);
			btAi.setHorizontalTextPosition(JButton.CENTER);
			
			btExit.setLocation((int)(consoleX*wndWidth), (int)(0.8*wndHeight));
			btExit.setSize	((int)(buttonW*wndWidth), (int)(buttonH*wndWidth/2));
			btExit.setFont(fnt);
			btExit.setHorizontalTextPosition(JButton.CENTER);
		
			this.setVisible(true);
			
		}
		/**
		 * ���¼���Label����ʾ����
		 * @param solutionNum�����������
		 * @param currentSolution��ǰ���
		 * @param totleTime����ʱ��
		 */
		public void updateInfo(int solutionNum,int currentSolution, long totleTime){
			lbInfo.setText(new String("����:")+Integer.toString(solutionNum));
			lbAllNum.setText(new String("��ǰ:")+Integer.toString(currentSolution+1));
			if(solutionNum>0 && solutionNum>currentSolution+1)
				this.btNext.setVisible(true);
			else
				this.btNext.setVisible(false);
			if(currentSolution==0)
				this.lbTime.setText("��ʱ:"+totleTime+"����");
			else
				this.lbTime.setText("");
		}
		private void clearAI(){
			lbInfo.setText("");
			lbAllNum.setText("");
			lbTime.setText("");
			btNext.setVisible(false);
		}
		/**
		 * �������ݸ��´���
		 * @param matrix С�����־���
		 * @param score ����
		 */
		public void refresh(int[][] matrix,int score){
		/*	((PerformPanel) pnPerform).setMatrix(matrix);
			lbScore.setText("����:"+Integer.toString(score));
			pnPerform.repaint();*/
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
				/*	if(dx<0)
						Game.theGame.moveLeft();
					else 
						Game.theGame.moveRight();
				}else{
					if(dy<0)
						Game.theGame.moveUp();
					else 
						Game.theGame.moveDown();*/
				}
				action=false;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}
}
