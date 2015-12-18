import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The ORShift class applies an
 * OR shift to each hex number provided
 * by the scanner file. It then takes the
 * new hex number and places it into a 
 * new _OR file.
 * @author Amanda
 */
public class ORShift 
{
	private Scanner scKeyboard;
	private String strFileName;
	
	/**
	 * ORShift(scKeyboard, strFileName) constructor.
	 * @param scKeyboard scanner used to read hex file.
	 * @param strFileName file name used for output file.
	 */
	public ORShift(Scanner scKeyboard, String strFileName)
	{
	  this.scKeyboard = scKeyboard;
	  this.strFileName = strFileName;
	}
	
	/**
	 * This class reads in a scanner file, takes the hex
	 * number and applies an OR shift, takes the OR shift
	 * number and places it in a new output text file.
	 * @param intOrNumber number to OR bit shift by.
	 * @throws IOException error handling
	 */
    public void ORWriteShift(int intOrNumber) throws IOException
	{
	   String strTemp;
	   String strBefore = "";
	   String strAfter = "";
	   String[] arHex = {"0", "1", "2", "3", "4", "5", "6", "7",
				         "8", "9", "A", "B", "C", "D", "E", "F" };
	   boolean bKeepGoing = true;
	   PrintWriter plOut = new PrintWriter(new File(strFileName + "_OR.txt"));
	   
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
				int hex = intTemp | intOrNumber;
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
		    // Used for testing purposes. Usually used on small files to check OR shift.
			System.out.println("BEFORE: " + strBefore.substring(0, strBefore.length() -1));
			System.out.println("AFTER:  " + strAfter.substring(0, strAfter.length() -1));
   }
}
