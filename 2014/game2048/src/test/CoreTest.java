package test;
import java.util.Scanner;

import core.*;
/**
 * �������ַ�����������coreģ�飬����L��U��R��D����ʾ����
 * @author duan
 *
 */
public class CoreTest {
	public static void main(String[] args){
    	Game game=new Game(false);
    	System.out.println(game);   
    	while(!game.beenOver()){		
    		@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
    		String str=s.next();
			switch(str.charAt(0)){
				case 'l':case 'L':
					game.moveLeft();
				break;
				case 'u':case 'U':
					game.moveUp();
				break;
				case 'r':case 'R':
					game.moveRight();
				break;
				case 'd':case 'D':
					game.moveDown();
				break;
			}
    		System.out.println(game);   
    	}

	}
}
