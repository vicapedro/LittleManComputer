package lmc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
	public String[] instructionList = {"ADD","SUB","STA","SKIP","LDA","BRA","BRZ","BRP"};
	public Parser()
	{
		
	}
	
	public int parse(String str)
	{
		
		int instruction = 0;
		System.out.println("Parsing Instructions");
		for(int i = 0; i < instructionList.length;i++)
		{
			if(i == 3)
			{
				i++;
			}
			String regex = ("(" + instructionList[i] + ")" + " (\\d\\d)"); 
			
			Pattern patternMatch = Pattern.compile(regex);
			Matcher m = patternMatch.matcher(str);
			
			if(m.find())
			{	
				instruction = Integer.parseInt(String.valueOf(i + 1) + m.group(2));	
			}
		}
		
		
		Matcher IOMatcher;
		Pattern inp = Pattern.compile("(INP)");
		IOMatcher = inp.matcher(str);
		
		if(IOMatcher.find())
		{
			String instructionAddString = "9";
			instructionAddString += "01";
			instruction = Integer.parseInt(instructionAddString);
		}
		Pattern out = Pattern.compile("(OUT)");
		IOMatcher = out.matcher(str);
		
		if(IOMatcher.find())
		{
			String instructionAddString = "9";
			instructionAddString += "02";
			instruction = Integer.parseInt(instructionAddString);
		}
				
		return instruction;
	}

}
