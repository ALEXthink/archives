package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import ai.Producer;
import core.Game;

public class AiTest {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		Game.init();;
		if(Game.loadFromFile()){
			Producer p=new Producer();
			System.out.println("������...");
			long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
			p.Produce(Game.matrix);
			long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
			System.out.println("���ҽ���!");
			System.out.println(p);
			System.out.println("����ʱ�䣺 "+(endTime-startTime)+"ms");
		}
	}	
}
