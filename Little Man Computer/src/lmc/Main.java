package lmc;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args)
	{
		LittleManComputer lmc = new LittleManComputer(100);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many instructions are in your program");
		int listLength = Integer.parseInt(sc.nextLine());
		String[] inst = new String[listLength]; 
		for(int j = 0; j < listLength;j++)
		{
			System.out.println("Instruction " + (j + 1) + ": ");	
			inst[j] = sc.nextLine();
			
		}					
		lmc.assemble(inst);
		lmc.run();
	}
}
