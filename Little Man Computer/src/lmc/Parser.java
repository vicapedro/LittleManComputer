package lmc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
	
	public Parser()
	{
		
	}
	
	public int parse(String str)
	{
		
		
		int instruction = 0;
		if(str == "HLT")
		{
			instruction = 000;
		}
		
		Pattern add = Pattern.compile("(ADD) (\\d\\d)");
		Matcher m = add.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "1";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		
		Pattern sub = Pattern.compile("(SUB) (\\d\\d)");
		m = sub.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "2";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		Pattern store = Pattern.compile("(STA) (\\d\\d)");
		m = store.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "3";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		
		Pattern lda = Pattern.compile("(LDA) (\\d\\d)");
		m = lda.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "5";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		
		Pattern bra = Pattern.compile("(BRA) (\\d\\d)");
		m = bra.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "6";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		Pattern brz = Pattern.compile("(BRZ) (\\d\\d)");
		m = brz.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "7";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		
		Pattern brp = Pattern.compile("(BRP) (\\d\\d)");
		m = brp.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "8";
			instructionAddString += m.group(2);
			instruction = Integer.parseInt(instructionAddString);
		}
		
		Pattern inp = Pattern.compile("(INP)");
		m = inp.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "9";
			instructionAddString += "01";
			instruction = Integer.parseInt(instructionAddString);
		}
		Pattern out = Pattern.compile("(OUT)");
		m = out.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "9";
			instructionAddString += "02";
			instruction = Integer.parseInt(instructionAddString);
		}
		
		Pattern dat = Pattern.compile("(BRP) (\\d\\d)");
		m = dat.matcher(str);
		
		if(m.find())
		{
			String instructionAddString = "";
			instructionAddString += m.group(2);	
			instruction = Integer.parseInt(instructionAddString);
		}
		
		
		return instruction;
	}

}
