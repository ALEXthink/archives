package ai;

import core.Direction;
import core.Element;
import core.Matrix;

/**
 * AI�࣬������������
 * @author duan
 *
 */
public class Solution {
	/**
	 * ������������ָ��Ĺ�һ��
	 */
	public static int maxScore=0;
	public static int maxStep=(int)((Matrix.Width*Matrix.Height-1)*Matrix.Width*Matrix.Height/2);

	public Solution(){
		if(maxScore==0){
			int gridnum=Matrix.Width*Matrix.Height;
			//maxScore=(int) Math.pow(2, gridnum+1);
			for(int i=0;i<gridnum;i++){
				maxScore+=(i)*Math.pow(2, i+1);
			}
		}
	}
	public Matrix produceNext(){
		
		return null;
		
	}
	
	/**
	 * alpha beta �㷨���ƶ�ʱ�����ֵ��������ÿ�����Сֵ
	 * @param depth �������
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private double AlphaBetaSearch(int depth,double alpha,double beta,boolean player,Matrix m,int score,int totalStep){
		if(depth==0)
			return 
	}
	
	/**
	 * ѡ�����������µļ���ֵ
	 * ������͸�ˣ������еĻ�����õĻ������ǾͿ�������
	 * @param depth ���
	 * @param m ����
	 * @param score ����
	 * @param totalStep ����
	 * @return ������õ�
	 */
	double maxMove(int depth,double alpha,double beta,Matrix m,int score,int totalStep){
		if (depth <= 0) { 
			return fitness(m.toIntMatrix(),score,totalStep); 
		} 
		if(m.dead())	//�������ˣ����ü�����
			return Double.NEGATIVE_INFINITY;
		//������һ��
		Direction[] dir={Direction.LEFT,Direction.UP,Direction.RIGHT,Direction.DOWN};
		for(int i=0;i<4;i++){
			Matrix newMat=new Matrix(m);
			int s=newMat.move(dir[i]);
			if(s<0)
				continue;
			//����ķ�����ѡ����õ�
			double val=minPut(depth-1,alpha,beta,newMat,score+s,totalStep+1);
			if(val>alpha)
				alpha=val;
		}
		return alpha;
	}
	/**
	 * ����������ã�����������
	 * �����һ�������Ļ���ҷ��϶����ῼ�ǣ�����
	 * @param depth
	 * @param m
	 * @param score
	 * @param totalStep
	 * @return
	 */
	double minPut(int depth,double alpha,double beta,Matrix m,int score,int totalStep){
		if(depth<0)
			return fitness(m.toIntMatrix(),score,totalStep);
		//����
		for(int i=0;i<m.Height;i++){
			for(int j=0;j<m.Width;j++){
				if(m.isEmptyElement(j,i)){
					for(int r=2;r<=4;r+=2){
						Matrix newMat=new Matrix(m);
						m.putElement(new Element(r), j, i);
						maxMove(depth-1,alpha,beta,newMat,score,totalStep);
					}
				}
			}
		}
	}
	
	/**
	 * ��������
	 * ���ԣ�
	 * ����ָ�꣺�ܵķ������ܵĲ������ո��������������ĵľ��롢���ڵ������ԽСԽ��
	 * @return ����ֵ
	 */
	private double fitness(int[][] m,int score,int totalStep){
		//����
		
		int blankNum=0;
		int max=0;
		int maxX = 0,maxY = 0;
		int diff=0;
		for(int i=0;i<Matrix.Height;i++){
			for(int j=0;j<Matrix.Width;j++){
				int v=m[i][j];
				if(v==0){
					blankNum++;//�ո���
				}
				//�ҵ�������
				if(v>max){
					max=v;
					maxX=j;
					maxY=i;
				}
				int times = 0;//�õ�ÿһ�����ұߣ��±ߵı���ֵ
				if(i+1<Matrix.Height){
					if(v>m[i+1][j])
						times=v/m[i+1][j];
					else
						times=m[i+1][j]/v;
				}
				if(j+1<Matrix.Width){
					if(v>m[i][j+1])
						times+=v/m[i][j+1];
					else 
						times+=m[i][j+1]/v;
				}
				diff+=times;//������������
			}
		}
		//���������ľ����ƽ��
		int maxNumDistance=(maxX-Matrix.Width/2)*(maxX-Matrix.Width/2)+
				(maxY-Matrix.Height/2)*(maxY-Matrix.Height/2);
		//��һ������
		double evScore=(double)score/maxScore;
		double evStep=(double)totalStep/maxStep;
		double evBlank=(double)blankNum/(Matrix.Width*Matrix.Height-1);
		double evDistance=maxNumDistance/(Matrix.Width*Matrix.Width/4+Matrix.Height*Matrix.Height/4);
		double evDiff=diff/(Matrix.Width*Matrix.Height*Matrix.Width*Matrix.Height*2);
		double f=evScore-evStep+evBlank+evDistance-evDiff;
		return f;
	}
}
