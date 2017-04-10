package core;

import java.util.Random;

/**
 * �����ϵ�Ԫ��
 * @author duan
 * @version 2.0
 */
public class Element {
	private int figure;
	private static Random r=new Random();
	/**
	 * Ĭ�ϳ�ʼ��Ϊ��
	 */
	public Element(){
		figure=0;
	}
	public Element(int n){
		figure=n;
	}
	public Element(Element e){
		this.figure=e.figure;
	}
	public int getValue(){
		return figure;
	}
	/**
	 * �������������2��4
	 * �����׺���2��4����Ϊ9:1
	 */
	public void randomize(){
		//figure=(r.nextInt(2)+1)*2;
		figure=r.nextInt(10)==0?4:2;
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return true��Ϊ�գ�����ǿ�
	 */
	public boolean empty(){
		return figure==0;
	}
	/**
	 * ���
	 */
	public void clear(){
		figure=0;
	}
	/**
	 * ��Ŀ��Ԫ�ؽ���ֵ
	 * @param e Ŀ��Ԫ��
	 */
	public void swap(Element e){
		int tmp=e.figure;
		e.figure=this.figure;
		this.figure=tmp;
	}
	/**
	 * Ԫ����ֵ�ӱ�
	 */
	public void upgrade(){
		figure*=2;
	}
	public boolean equals(Object obj){
		if(obj instanceof Integer)	
			return figure==(Integer)obj;
		else if(obj instanceof Element)
			return this.figure==((Element)obj).figure;
		return false;
	}
	public String toString(){
		if(empty())
			return "     ";
		return String.format("%-5d", figure);
	}

}
