import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class will shift a hex number 
 * from the given direction and number
 * of shifts. (Ex: 06 + 3 shifts is 09).
 * It applies the desired shift number 
 * to all the hex numbers and then outputs
 * the new results to a file.
 * @author Amanda
 */
public class HexBitShifting 
{
	private Scanner scKeyboard;
	private String strFileName;
	
	/**
	 * HexBitShifting(scKeyboard, strFileName) constructor.
	 * @param scKeyboard Scanner that will read hex numbers from file.
	 * @param strFileName File name to be used on output file.
	 */
	public HexBitShifting(Scanner scKeyboard, String strFileName)
	{
		this.scKeyboard = scKeyboard;
		this.strFileName = strFileName;
	}
	
	/**
	 * HEXWriteShift(strDirection, intShiftNumber)
	 * @param strDirection left or right shift (+ or -).
	 * @param intShiftNumber the number thats needed to shift places.
	 * @throws IOException error handling.
	 */
	public void HEXWriteShift(String strDirection, int intShiftNumber) throws IOException
	{
	  String strTemp;
	  String strBefore = "";
	  String strAfter = "";
	  boolean bKeepGoing = true;
	  int hex = 0;
	  PrintWriter plOut = new PrintWriter(new File(strFileName + "_HEXBIT.txt"));
	  String[] arHex = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
			            "9", "A", "B", "C", "D", "E", "F" };
	  
	  while (scKeyboard.hasNextLine())
	  {
	    strTemp = scKeyboard.nextLine();
	    
	    for (int i = 0; i < strTemp.length(); i++)
		{
		  if (i + 2 > strTemp.length())
		  {
			bKeepGoing = false;
		  }
		
		  if (bKeepGoing == true && strTemp.substring(i, i + 2).contains(" "))
		  {
			 i++;
		  }
		 
		  if (bKeepGoing == true && i + 2 <= strTemp.length() && !strTemp.substring(i, i + 2).equals(" "))
		  {
			strBefore += strTemp.substring(i, i + 2) + " ";
			int intTemp = Integer.parseInt(strTemp.substring(i, i + 2).toLowerCase(), 16);
		
			if (strDirection.equalsIgnoreCase("left"))
			{
			  hex = intTemp - intShiftNumber;
			}
			
			if (strDirection.equalsIgnoreCase("right"))
			{
				hex = intTemp + intShiftNumber;
			}
			
			if (hex < 0)
			{
			  hex = hex + 256; 
			}
			if (hex > 255)
			{
			   hex = hex - 256;
			}
				   
			int firstdigit = hex / 16;
			int seconddigit = hex % 16;
			plOut.print(arHex[firstdigit] + "" + arHex[seconddigit] + " ");
			strAfter += arHex[firstdigit] + "" + arHex[seconddigit] + " ";
			i++;
		   }
		   if (bKeepGoing == false)
		   {
			 continue;
		   }
		}
	   }
	   plOut.flush();
	   plOut.close();
	   // For testing purposes. Usually used on small files.
	   System.out.println("BEFORE: " + strBefore.substring(0, strBefore.length() -1));
	   System.out.println("AFTER:  " + strAfter.substring(0, strAfter.length() -1));
	 }
}	