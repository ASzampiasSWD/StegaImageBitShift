import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * ANDShift applies an AND bit shift
 * to each hex number from the scanner
 * file.
 * @author Amanda
 */
public class ANDShift 
{
	private Scanner scKeyboard;
	private String strFileName;

  /**
   * ANDShift (scKeyboard, strFileName) constructor.
   * Passes in the scanner to read hex numbers and the 
   * file name to supply to the output file.
   * @param scKeyboard Scanner that will read hex file.
   * @param strFileName file name to create output file name.
   */
  public ANDShift(Scanner scKeyboard, String strFileName)
  {
	  this.scKeyboard = scKeyboard;
	  this.strFileName = strFileName;
  }
  /**
   * This will read every hex number provided by the 
   * scanner, make an updated AND bit shift hex number,
   * and put the updated hex number into a new output file.
   * @param intAndNumber What hex number a user would like to bitwise AND by.
   * @throws IOException error handling.
   */
  public void ANDWriteShift(int intAndNumber) throws IOException
	{
      String strTemp;
	  String strBefore = "";
	  String strAfter = "";
	  String[] arHex = {"0", "1", "2", "3", "4", "5", "6", "7",
			            "8", "9", "A", "B", "C", "D", "E", "F" };
	  boolean bKeepGoing = true;
	  PrintWriter plOut = new PrintWriter(new File(strFileName + "_AND.txt"));
	 
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
			int hex = intTemp & intAndNumber;
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
	    // Provided only for small hex file testing.
		System.out.println("BEFORE: " + strBefore.substring(0, strBefore.length() -1));
		System.out.println("AFTER:  " + strAfter.substring(0, strAfter.length() -1));
	  }
 }
