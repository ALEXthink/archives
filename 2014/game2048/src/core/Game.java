package core;
import gui.Window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import ai.AiMoveMatrix;
import ai.Solution;
/**
 * ��Ϸ�࣬����ģ���Э���ͳ�����������
 * @author duan
 * @version 1.0
 */
public class Game extends Thread{
	/**
	 * ȫ��ֻ��һ��Game����
	 */
	public static Game theGame;
	Matrix mat;
	private int score;
	private int step;
	private GameState state;
	private Window wnd;
	/**
	 * �Ƿ���ͼ�ν�������
	 */
	private boolean guiMode;
	enum GameState{
		RUNNING,BEENOVER;
	}
	public Game(boolean gmode){
		//theGame=this;
		guiMode=gmode;
		try {
			if(guiMode){
				wnd=new Window();
				//��ȡ��Ļ��С
			    Dimension scrSize =Toolkit.getDefaultToolkit().getScreenSize();
			    //�ƶ�����Ļ����
			    wnd.setLocation((int)(scrSize.getWidth()-wnd.getWidth())/2, 
			    		(int)(scrSize.getHeight()-wnd.getHeight())/2);
			}
			initGame();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ���¿�ʼ��Ϸ
	 */
	public void restart(){
		initGame();
	}
	/**
	 * ����Ϸ�������г�ʼ���������ʼ�������¿�ʼ��Ϸʱ
	 */
	private void initGame(){
		mat=new Matrix();
		score=0;	
		step=0;
		state=GameState.RUNNING;
		if(guiMode)
			wnd.refresh(mat.toIntMatrix(), score);
	}
	private void move(Direction d){
		if(state!=GameState.RUNNING)
			return;
		int r=mat.move(d);
		if(r>=0){	//�ƶ���Чʱ�Ž��мӷ�
			score+=r;
			step++;
			if(mat.dead()){
				state=GameState.BEENOVER;
			}else{ 
				mat.putRandElement();
			}
		}
		if(mat.dead()){
			state=GameState.BEENOVER;
		}
		//���ݸı��ˣ���Ҫ���´���
		if(guiMode){
			//System.out.println(guiMode);
			wnd.refresh(mat.toIntMatrix(), score);
			if(state==GameState.BEENOVER)
				JOptionPane.showMessageDialog(null, "��Ϸ������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void moveLeft(){
		move(Direction.LEFT);
	}
	public void moveUp(){
		move(Direction.UP);
	}
	public void moveRight(){
		move(Direction.RIGHT);
	}
	public void moveDown(){
		move(Direction.DOWN);
	}
	
	public boolean beenOver(){
		return state==GameState.BEENOVER;
	}
	public Matrix getMatrix(){
		return mat;
	}
	public int getScore(){
		return score;
	}
	public int getStep(){
		return step;
	}
	public String toString(){
		return mat+"��ǰ�÷�:"+Integer.toString(score)+"   ��ǰ������"+Integer.toString(step);
	}
	
	public void startAi(){
		Thread trd=new Game(true);
		((Thread) trd).start();
	}
	public void Ai(){
		//Game game=new Game(true);
    	Solution sl=new Solution();
		/**
		 *   �ܵķ������ܵĲ������ո���������������ĵľ��롢���ڵ������ԽСԽ��
		 */
    //	AiMatrix.setWeights(0.1, 0.0, 10.0, 1.0, -1.0);
    	System.out.println(this);   
    	while(!this.beenOver()){		
    		Direction d=sl.produceNext(1, new AiMoveMatrix(this.getMatrix(),this.getScore(),this.getStep()));
    		switch(d){
    			case LEFT:
    				this.moveLeft();
    				break;
    			case UP:
    				this.moveUp();
    				break;
    			case RIGHT:
    				this.moveRight();
    				break;
    			case DOWN:
    				this.moveDown();
    				break;
    		}
        	System.out.println("�������:"+d.toString());   
        	System.out.println(this);    
        	try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
    	}
    	System.out.println("��Ϸ������");      	
	}
	/**
	 * ��ʽ�ĳ������
	 * @param args
	 */
	public static void main(String[] args){
		theGame=new Game(true);
	}
	@Override
	public void run() {
		Ai();
	}
}
