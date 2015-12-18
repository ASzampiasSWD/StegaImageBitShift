import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * XORShift applies a shift to a
 * hex file and prints out the new
 * shift to an _XOR.txt.
 * @author Amanda
 */
public class XORShift 
{
	private Scanner scKeyboard;
	private String strFileName;
	
	/**
	 * XORShift(Scanner, String) constructor.
	 * @param scKeyboard scanner taken from FileHandle class.
	 * @param strFileName file name taken from FileHandle class.
	 */
	public XORShift(Scanner scKeyboard, String strFileName)
	{
	  this.scKeyboard = scKeyboard;
	  this.strFileName = strFileName;
	}
	
	/**
	 * Reads in the file provided by the scanner, takes a 
	 * hex digit (06) example and applies an XOR gate to it
	 * to create a new solution and then added into a text
	 * file.
	 * @throws IOException error handling.
	 */
	public void XORWriteShift(int intXORBy) throws IOException
	{
      String strTemp;
	  String strBefore = "";
	  String strAfter = "";
	  String[] arHex = {"0", "1", "2", "3", "4", "5", "6", "7",
			            "8", "9", "A", "B", "C", "D", "E", "F" };
	  boolean bKeepGoing = true;
	  PrintWriter plOut = new PrintWriter(new File(strFileName + "_XOR.txt"));
	  
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
			int hex = intTemp ^ intXORBy;
		    int firstdigit = hex / 16;
		    int seconddigit = hex % 16;
		    strAfter += arHex[firstdigit] + "" + arHex[seconddigit] + " ";
			plOut.print(arHex[firstdigit] + "" + arHex[seconddigit] + " ");
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
	    // Used for debugging small files. Checks to see if XOR is working correctly.
		System.out.println("BEFORE: " + strBefore.substring(0, strBefore.length() -1));
		System.out.println("AFTER:  " + strAfter.substring(0, strAfter.length() -1));
	  }
}
