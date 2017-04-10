/**********************************************
 Copyright 2014 lianera (www.lianera.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**********************************************/

package core;

import gui.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ai.Producer;

public class Game {
	public static Matrix matrix=null;
	static Window wnd=null;
	static int solutionNum=0;
	static int currentSolution=0;
	private static long totleTime=0;
	static Producer currentProducer=null;
	
	public static void init() throws FileNotFoundException, IOException{
		matrix=new Matrix();
	}
	/**
	 * ���ļ��м�����Ŀ
	 * @return�Ƿ�ɹ�
	 */
	public static boolean loadFromFile(){
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("./res/dataset"));
			if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
				matrix=new Matrix(new FileInputStream(chooser.getSelectedFile()));
				if(wnd!=null){
					wnd.pnPerform.updateMatrix(matrix.getMatrix());
					wnd.pnPerform.setOrigin(matrix.getMatrix());
				}
				return true;				
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"�ļ�û���ҵ���");
		} catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"�ļ�����");
		}
		return false;
	}
	/**
	 * ������Ϸ
	 */
	public static void reset(){
		matrix=new Matrix();
		wnd.pnPerform.updateMatrix(matrix.getMatrix());
		wnd.pnPerform.setOrigin(null);
	}
	/**
	 * ��ʼ�����˹�����
	 */
	public static void startAi(){
		Producer p=new Producer();
		try {
			if(matrix.hasConflicted())
				JOptionPane.showMessageDialog(null,"�Ѿ��д����ˣ�");
			else{
					
				long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
				int num=p.Produce(matrix);
				long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
				long t=endTime-startTime;				
				
				if(num>0){
					totleTime=t;
					currentProducer=p;
					solutionNum=num;
					currentSolution=0;
					applyAiSolution(currentSolution);
				}else
					JOptionPane.showMessageDialog(null,"�Ҳ������еĽ⣡");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * �л�����һ����
	 */
	public static void switchToNextSolution(){
		currentSolution++;
		applyAiSolution(currentSolution);
	}
	
	private static void applyAiSolution(int index){
		matrix=new Matrix();
		//wnd.pnPerform.updateMatrix(p.getMatrix());
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				matrix.setElem(j, i,currentProducer.getMatrix(currentSolution)[i][j]);
		wnd.pnPerform.updateMatrix(matrix.getMatrix());
		wnd.updateInfo(solutionNum, currentSolution,totleTime);	
	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		Game.init();
		try {
			wnd = new Window();
			Game.reset();
		//	g.loadFromFile();
			wnd.pnPerform.updateMatrix(Game.matrix.getMatrix());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
