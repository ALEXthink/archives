package test;

import ai.AiMatrix;
import ai.AiMoveMatrix;
import ai.Solution;
import core.Direction;
import core.Game;

/**
 * �����ݽ���ͳ��
 * @author duan
 *
 */
public class AiStatistic {
	public static final int Times=100;
	public static void main(String[] args){
		/**
		 * �ܵķ������ܵĲ������ո���������������ĵľ��롢���ڵ������ԽСԽ��
		 */
		//AiMatrix.setWeights(2.0, 0.0, 2.0, 1.0, -2.0);
    	int maxScore=0;
    	int minScore=Integer.MAX_VALUE;
    	int avgScore=0;
    	int num2048=0;
    	int num4096=0;
    	int num8192=0;
    	int num16384=0;
    	int stepNum=0;
    	System.out.println("��ʼ���С���");
    	long startMili=System.currentTimeMillis();
    	for(int i=0;i<Times;i++){
    		/**
    		 * һ����һ��������AI��Ϸ���̣�֮��¼���շ���
    		 */
        	Game game=new Game(false);
        	Solution sl=new Solution();
	    	while(!game.beenOver()){		
	    		Direction d=sl.produceNext(3, new AiMoveMatrix(game.getMatrix(),game.getScore(),game.getStep()));
	    		stepNum++;
	    		switch(d){
	    			case LEFT:
	    				game.moveLeft();
	    				break;
	    			case UP:
	    				game.moveUp();
	    				break;
	    			case RIGHT:
	    				game.moveRight();
	    				break;
	    			case DOWN:
	    				game.moveDown();
	    				break;
	    		}
	    	}
	    	System.out.println("������"+(i+1)+"��");
	    	//һ����Ϸ����
	    	int s=game.getScore();
	    	avgScore+=s;
	    	if(s>maxScore)
	    		maxScore=s;
	    	if(s<minScore)
	    		minScore=s;
	    	if(game.getMatrix().getMax()==2048)
	    		num2048++;
	    	if(game.getMatrix().getMax()==4096)
	    		num4096++;
	    	if(game.getMatrix().getMax()==8192)
	    		num8192++;
	    	if(game.getMatrix().getMax()==16384)
	    		num16384++;	    	
    	}
    	//ȫ������
    	long endMili=System.currentTimeMillis();
    	long avgMili=(endMili-startMili)/stepNum;
    	avgScore/=Times;
    	System.out.println("��߷�:"+maxScore);
    	System.out.println("��ͷ�:"+minScore);
    	System.out.println("ƽ����:"+avgScore);
    	System.out.println("����2048����:"+num2048);
    	System.out.println("����4096����:"+num4096);
    	System.out.println("����8192����:"+num8192);
    	System.out.println("����16384����:"+num16384);
    	System.out.println("ƽ��ÿ������ʱ��:"+avgMili+"(����)");
	}
}
