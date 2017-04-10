package ai;

import core.Element;
import core.Matrix;

/**
 * ����AI�о��ľ���
 * @author duan
 * @version 1.0
 */
abstract public class AiMatrix extends Matrix{
	/**
	 * ������ֵ��Ϊ�˹�һ��
	 */
	public static int maxScore=1966080;
	public static int maxStep=(int)((Matrix.Width*Matrix.Height-1)*Matrix.Width*Matrix.Height/2);
	int score;
	int totalStep;
	/**
	 * ����ָ���Ȩֵ
	 */
	/**
	 * ����
	 */
	private static double wtScore=0.1;
	/**
	 * ����
	 */
	private static double wtStep=-0.1;
	/**
	 * �ո���
	 */
	private static double wtBlank=0.1;
	/**
	 * ����������ĵľ���
	 */
	private static double wtDistance=0.1;
	/**
	 * ������������֮��(�ո����)
	 */
	private static double wtDiff=-0.1;
	public AiMatrix(Matrix m,int mScore,int mStep){
		super(m);
		score=mScore;
		totalStep=mStep;
	}
	public AiMatrix(AiMatrix am){
		super(am);
		score=am.score;
		totalStep=am.totalStep;
	}
	public boolean isEmptyElement(int x,int y){
		return elems[y][x].empty();
	}
	
	public void putElement(Element e,int x,int y){
		elems[y][x]=e;
	}	
	/**
	 * ����Ȩֵ
	 * @return 
	 */
	public static void setWeights(double weightScore,double weightStep,double weightBlank,double weightDistance,double weightDiff){
		wtScore=weightScore;
		wtStep=weightStep;
		wtBlank=weightBlank;
		wtDistance=weightDistance;
		wtDiff=weightDiff;
	}
	/**
	 * ��������
	 * ���ԣ�
	 * ����ָ�꣺�ܵķ������ܵĲ������ո��������������ĵľ��롢���ڵ������ԽСԽ��
	 * @return ����ֵ
	 */
	public double fitness(){
		if(dead())
			return -Solution.INFINITY;
		//����
		int[][] m=this.toIntMatrix();
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
				if(v!=0){
					if(i+1<Matrix.Height && m[i+1][j]!=0){
						if(v>m[i+1][j])
							times=v/m[i+1][j];
						else
							times=m[i+1][j]/v;
					}
					if(j+1<Matrix.Width && m[i][j+1]!=0){
						if(v>m[i][j+1])
							times+=v/m[i][j+1];
						else 
							times+=m[i][j+1]/v;
					}
				}
				diff+=times;//������������
			}
		}
		//���������ľ����ƽ��
		double maxNumDistance=(maxX-(Matrix.Width-1)/2.0)*(maxX-(Matrix.Width-1)/2.0)+
				(maxY-(Matrix.Height-1)/2.0)*(maxY-(Matrix.Height-1)/2.0);
		
		/*
		//��һ������
		double evScore=(double)score/maxScore;
		double evStep=(double)totalStep/maxStep;
		double evBlank=(double)blankNum/(Matrix.Width*Matrix.Height-1);
		double evDistance=(double)maxNumDistance/((Matrix.Width-1)*(Matrix.Width-1)/4.0+(Matrix.Height-1)*(Matrix.Height-1)/4.0);
		double evDiff=(double)diff/(Matrix.Width*Matrix.Height*Matrix.Width*Matrix.Height*2);
		*/
		double evScore=score==0?0:(double)Math.log(score)/Math.log(2);
		double evStep=(double)totalStep;
		double evBlank=(double)blankNum;
		double evDistance=(double)maxNumDistance;
		double evDiff=(double)diff;

		/**
		 * ����ֵΪ���� ָ��XȨֵ��ƽ����
		 */
		double f=(wtScore*evScore+wtStep*evStep+wtBlank*evBlank+wtDistance*evDistance+wtDiff*evDiff)/5;
		return -f;
	}	
	
	abstract void generateChildren();
	abstract AiMatrix getNextChild(); 
	abstract boolean childrenLeft();
}
