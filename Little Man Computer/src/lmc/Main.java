package lmc;

public class Main {

	
	public static void main(String[] args)
	{
		LittleManComputer lmc = new LittleManComputer(100);
		String[] inst = {"INP" ,"STA 99" ,"INP","ADD 99", "OUT", "HLT"};
		lmc.assemble(inst);
		
		lmc.run();
	}
}
