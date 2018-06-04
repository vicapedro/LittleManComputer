package lmc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
	public String[] instructionList = {"ADD","SUB","STA","SKIP","LDA","BRA","BRZ","BRP","INP","OUT"};
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
				if(instructionList[i] == "INP")
				{
					instruction = 901;
				}
				else if(instructionList[i] == "OUT")
				{
					instruction = 902;
				}
				instruction = Integer.parseInt(String.valueOf(i + 1) + m.group(2));	
			}
		}
		return instruction;
	}

}
