package util;
/**
 * ˳�����
 * @author duan
 * @version 1.0
 */
public class SeqList {
	private static final int defaultCapacity=8;
	private Object[] objs;
	private int size;
	public SeqList(){
		objs=new Object[defaultCapacity];
		size=0;
	}
	/**
	 * �����в���Ԫ��
	 * @param pos λ�ã���0��ʼ
	 * @param o ����
	 * @warnings δ���pos��ֵ
	 */
	public void insert(int pos,Object o){
		//��������������
		if(size==objs.length){
			expandCapacity();
		}
		//�ƶ�����λ��֮���Ԫ��
		for(int i=size;i>pos;i--)
			objs[i]=objs[i-1];
		objs[pos]=o;
		size++;
	}
	/**
	 * ���뵽���
	 * @param o
	 */
	public void insert(Object o){
		this.insert(size, o);
	}
	/**
	 * ����ָ��λ�õ�Ԫ��
	 * @param pos
	 * @return
	 */
	public Object get(int pos){
		return objs[pos];
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return
	 */
	public boolean empty(){
		return size==0;
	}
	/**
	 * ȡ�ô�С
	 * @return
	 */
	public int getSize(){
		return size;
	}
	/**
	 * ����
	 * @warnings δ�����ڴ�����飬�б�Ҫ��
	 */
	private void expandCapacity(){
		Object[] newObjs=new Object[objs.length*2];//ÿ�μӱ���ÿ�β��븴�Ӷ�Ϊlog2(n)?j
		for(int i=0;i<objs.length;i++)	//����
			newObjs[i]=objs[i];
		objs=newObjs;
	}
	
}
