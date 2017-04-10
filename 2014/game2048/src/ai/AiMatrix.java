package ai;

import core.Direction;
import core.Element;
import core.Matrix;

/**
 * ����AI�о��ľ���
 * @author duan
 * @version 1.1
 */
abstract public class AiMatrix extends Matrix{
	/**
	 * ������ֵ��Ϊ�˹�һ��
	 */
	public static int maxScore=1966080;
	public static int maxStep=(int)((Matrix.Width*Matrix.Height-1)*Matrix.Width*Matrix.Height/2);
	
	public static double farPointX=1;//(Matrix.Width-1)/2.0;
	public static double farPointY=(Matrix.Height-1)/2.0;
	
	int score;
	int totalStep;
	/**
	 * ����ָ���Ȩֵ
	 */
	/**
	 * ����
	 */
	//private static double wtScore=0.1;
	/**
	 * ����
	 */
	//private static double wtStep=-0.1;
	/**
	 * �ո���
	 */
	//private static double wtBlank=0.1;
	/**
	 * ����������ĵľ���
	 */
	//private static double wtDistance=0.1;
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
/*	public static void setWeights(double weightScore,double weightStep,double weightBlank,double weightDistance,double weightDiff){
		wtScore=weightScore;
		wtStep=weightStep;
		wtBlank=weightBlank;
		wtDistance=weightDistance;
		wtDiff=weightDiff;
	}*/
	/**
	 * ��������
	 * ���ԣ�
	 * ����ָ�꣺�ܵķ������ܵĲ������ո��������������ĵľ��롢���ڵ������ԽСԽ��
	 * @return ����ֵ
	 */
	public double fitness(){
		if(dead())
			return Solution.INFINITY;
		Direction allDir[]={Direction.LEFT,Direction.UP,Direction.RIGHT,Direction.DOWN};
		int[][] m=this.toIntMatrix();
		int max=0;
		int maxX = 0,maxY = 0;		
		int maxValue=0;		//���ֵ
		int blankNum=0;		//�ո���
		double smoothness=0;	//ƽ����
		double monotonicity=0;	//������
		double[] diffDir=new double[allDir.length];//���������ϵ����ڵ�Ԫ������ֵ
		for(int i=0;i<Matrix.Height;i++){
			for(int j=0;j<Matrix.Width;j++){
				//�ҵ�������
				if(m[i][j]>max){
					max=m[i][j];
					maxX=j;
					maxY=i;
				}				
				if(m[i][j]>maxValue){
					maxValue=m[i][j];
				}
				if(m[i][j]==0){
					blankNum++;//�ո���
				}else{
					double logv=Math.log(m[i][j])/Math.log(2);
					//ƽ����
					for(Direction d:allDir){
						Element e=neigbor(d,j,i);
						if(e!=null){
							smoothness-=Math.abs(logv-Math.log(e.getValue())/Math.log(2));
						}
					}
					//������
					for(int k=0;k<allDir.length;k++){
						Element e=neigbor(allDir[k],j,i);
						if(e!=null){
							double lognbv=Math.log(e.getValue())/Math.log(2);
							if(lognbv>logv)
								diffDir[k]+=logv-lognbv;
						}
					}

				}
			}
		}
		double maxNumDistance=(maxX-(Matrix.Width-1)/2.0)*(maxX-(Matrix.Width-1)/2.0)+
				(maxY-(Matrix.Height-1)/2.0)*(maxY-(Matrix.Height-1)/2.0);		
		
		double blanks=Math.log(blankNum)/Math.log(2);
		//ȫ�ֵ�����Ϊ�ĸ������ϵ����Ե����ֵ
		monotonicity=Math.max(diffDir[0],diffDir[2])+Math.max(diffDir[1],diffDir[3]);
		// ����ֵΪ���� ָ��XȨֵ��ƽ����
		double f=1.0*maxValue+0.1*smoothness+2.5*blanks+1.5*monotonicity+2.0*maxNumDistance;
		//System.out.println(max);
		return -f;
	}	
	
	/**
	 * �ҵ�ָ����������ķǿյ�Ԫ
	 */
	Element neigbor(Direction d,int x,int y){
		switch(d){
		case LEFT:
			do{
				x--;
				if(x<0)
					return null;
			}while(elems[y][x].empty());
			break;
		case UP:
			do{
				y--;
				if(y<0)
					return null;
			}while(elems[y][x].empty());
			break;
		case RIGHT:
			do{
				x++;
				if(x>=Matrix.Width)
					return null;
			}while(elems[y][x].empty());
			break;
		case DOWN:
			do{
				y++;
				if(y>=Matrix.Height)
					return null;
			}while(elems[y][x].empty());
			break;
		}
		return elems[y][x];
	}
	
	abstract void generateChildren();
	abstract AiMatrix getNextChild(); 
	abstract boolean childrenLeft();
}
