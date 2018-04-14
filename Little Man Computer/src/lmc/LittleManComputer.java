package lmc;

import javax.swing.JOptionPane;

public class LittleManComputer
{
	public int[] memory;
	public int programCounter = 0;
	public int accumulator;
	public int instructionRegister;
	public int addressRegister;
	
	public int instructionsLength;
	
	public int input;
	public int ouput;
	
	public LittleManComputer(int memorySize)
	{
		memory = new int[memorySize];
	}
	
	// This function takes the input(a set of opcodes) and adds them to the memory registers as numbers
	// This is done using the parser class. We use an input of opcodes as they are easy to remember.

	public void assemble(String[] instructions) 
	{
		Parser p = new Parser();
		for(int i=0;i < instructions.length;i++)
		{
			instructionsLength = instructions.length;
			int val = p.parse(instructions[i]);
			memory[i] = val;
		}
	}
	
	
	
	public void outputMemory()
	{
		for(int i=0;i < memory.length;i++)
		{
			if(memory[i] !=0)
			System.out.println("Instruction " + i + " :" + memory[i]);
		}
	}
	
	public void run() 
	{
		outputMemory();
		System.out.println("Running LMC");
		while(true)
		{
		int instruction = memory[programCounter];	//fetching
		
		programCounter++;
		instructionRegister = Integer.parseInt(Integer.toString(instruction).substring(0, 1));// gets first character (as instruction)
		addressRegister = instruction - (instructionRegister*100);
		if(instruction==0)
		{
			break;
		}
		execute();
		}
		
	}
	void execute()
	{
		switch (instructionRegister) {
        case 1:
            add();
            break;
        case 2:
            sub();
            break;
        case 3:
            sta();
            break;
        case 4:
            System.out.println("Unknown command");
            break;
        case 5:
            lda();
            break;
        case 6:
            bra();
            break;
        case 7:
            brz();
            break;
        case 8:
            brp();
            break;
        case 9:
            if(addressRegister == 1)
            {
            	inp();
            	break;
            }
            else if(addressRegister == 2)
            {
            	out();
            }            
		}
	}
	
	void add()
	{
		accumulator  = accumulator + memory[addressRegister];	
		
	}
	void sub()
	{
		accumulator  = accumulator - memory[addressRegister];
	}
	void brp()
	{
		if(memory[addressRegister] > 0)
		{
			bra();
		}
	}
	
	void brz()
	{
		if(memory[addressRegister] == 0)
		{
			bra();
		}
	}
	
	void bra()
	{
		programCounter = addressRegister;
	}
	
	void lda()
	{
		 accumulator = memory[addressRegister];
	}
	
	void sta()
	{
		System.out.println("Storing data in accumulator");
		memory[addressRegister] = accumulator;
	}
	
	void inp()
	{
		accumulator = Integer.parseInt(JOptionPane.showInputDialog("Enter your input"));
	}
	void out()
	{
		JOptionPane.showMessageDialog(null, "Accumulator : " + accumulator);
	}
	
	
	
	
	
	
	
	
	
	
}
