package core;

import java.util.Random;

import util.SeqList;

/**
 * �����࣬�ܹ�������λ�ϲ������Լ������顣
 * @author duan
 * @version 2.0
 */
public class Matrix {
	public static final int Width=4;
	public static final int Height=4;
	protected Element[][] elems;
	private static Random rand=new Random();
	/**
	 * Ĭ�Ϲ��캯����ʼ��Ϊ��Ϸ��ʼ״̬
	 */
	public Matrix(){
		elems=new Element[Height][Width];
		//��ʼ��
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				elems[i][j]=new Element(0);
			}
		}
		//�����������Ԫ��
		putRandElement();
		putRandElement();

	}
	/**
	 * ���ƹ��캯����������Ԫ�أ�
	 * @param m
	 */
	public Matrix(Matrix m){
		elems=new Element[Height][Width];
		//��ʼ��
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				elems[i][j]=new Element(m.elems[i][j]);
			}
		}		
	}
	/**
	 * �ڿ�ȱ������һ��2��4�������
	 * @return ����1���ɹ�������0��û�п�Ԫ�ء�
	 */
	public boolean putRandElement(){	
		SeqList blanks=new SeqList();
		//�ѿ�Ԫ�ؼ���˳�����
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				if(elems[i][j].empty()){
					blanks.insert(elems[i][j]);
				}
			}
		}
		if(blanks.empty())	//û�п�Ԫ��
			return false;
		//�ӱ���ѡ��һ��λ��
		int pos=rand.nextInt(blanks.getSize());
		//����Ԫ�������
		((Element) blanks.get(pos)).randomize();
		return true;
	}

	/**
	 * �ƶ�Ԫ�صĺ�������������Ϸ�������Ԫ�غϲ���
	 * @param d
	 * @return ����-1�����ʾΪ��Ч�ƶ������򷵻ر��ε÷֡�
	 */
	public int move(Direction d){	
		Element[] arr=new Element[Height];//�������ƴ��ƶ�Ԫ��
		int r,score=0;
		boolean valid=false;
		switch(d){
		case LEFT:
			//��ÿ�н����ƶ�
			for(int i=0;i<Height;i++){
				r=moveLine(elems[i]);
				if(r!=-1){
					valid=true;
					score+=r;
				}
			}
			break;
		case UP:
			for(int j=0;j<Width;j++){
				for(int i=0;i<Height;i++){
					arr[i]=elems[i][j];
				}
				r=moveLine(arr);
				if(r!=-1){
					valid=true;
					score+=r;
				}
			}
			break;
		case RIGHT:
			for(int i=0;i<Height;i++){
				for(int j=0;j<Width;j++){
					arr[Width-1-j]=elems[i][j];
				}
				r=moveLine(arr);
				if(r!=-1){
					valid=true;
					score+=r;
				}
			}
			break;
		case DOWN:
			for(int j=0;j<Width;j++){
				for(int i=0;i<Height;i++){
					arr[Height-1-i]=elems[i][j];
				}
				r=moveLine(arr);
				if(r!=-1){
					valid=true;
					score+=r;
				}
			}
			break;
		}
		if(valid)
			return score;
		return -1;
	}
	/**
	 * ���ݹ����ƶ�һ�У��У�
	 * @param arr
	 * @return ����-1�����ʾΪ��Ч�ƶ������򷵻ر��ε÷֡�
	 */
	private int moveLine(Element[] arr){
		boolean[] flag=new boolean[arr.length];	//����Ƿ�ϲ���
		for(int n=0;n<arr.length;n++)	//��ձ��
			flag[n]=false;
		int s=0;
		boolean valid=false;
		for(int i=1;i<arr.length;i++){
			if(arr[i].empty())
				continue;
			int k=i-1;
			while(k>=0 && arr[k].empty())
				k--;
			/**
			 * �˴����Ż�
			 */
			if(k<0 || !arr[k].equals(arr[i]) || flag[k]==true){	//��ĩ�� �� ������ͬ����  �� ������ͬ�ĵ��ϲ���
				if(k+1!=i){
					arr[k+1].swap(arr[i]);	//�ƶ�����ֹ������գ�������swap
					valid=true;
				}
			}else{	//�������������δ�ϲ���
				arr[k].upgrade();//�ϲ�
				arr[i].clear();
				s+=arr[k].getValue();//�ӷ�
				flag[k]=true;	//���ñ��
				valid=true;
			}
		}
		if(valid)
			return s;	//���ط��� 
		return -1;
	}
	public int getMax(){
		int max=0;
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				if(elems[i][j].getValue()>max)
					max=elems[i][j].getValue();
			}
		}
		return max;
	}
	/**
	 * �ж��Ƿ����������
	 * @return 1������0���
	 */
	public boolean dead(){
		//�ж��Ƿ������ڵ���ͬԪ��
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				if(elems[i][j].empty())
					return false;
				if(i+1<Height)
					if(elems[i][j].equals(elems[i+1][j]))
						return false;
				if(j+1<Width)
					if(elems[i][j].equals(elems[i][j+1]))
						return false;
			}
		}
		return true;
	}
	

	/**
	 * ���ؿո���
	 * @return �ո���
	 */
	/*
	public int blankNum(){
		int n=0;
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				if(elems[i][j].empty()){
					n++;
				}
			}
		}
		return n;
	}
	*/
	/**
	 * ��Ԫ�ؾ���ת����4x4��������
	 * @return 4x4��ά����
	 */
	public int[][] toIntMatrix(){
		int[][] m=new int[Height][Width];
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				m[i][j]=elems[i][j].getValue();
			}
		}
		return m;
	}
	
	/**
	 * ������ת���ɵ��ַ���
	 */
	public String toString(){
		String line="-------------------------\n";
		String s = new String();
		s+=line;
		for(int i=0;i<Height;i++){
			s+="|";
			for(int j=0;j<Width;j++){
				s+=elems[i][j].toString()+"|";
			}
			s+="\n"+line;
		}
		return s;
	}


}
