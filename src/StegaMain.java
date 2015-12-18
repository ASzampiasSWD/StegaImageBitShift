/* Author: Amanda Szampias
 * School: Baldwin Wallace Software Engineering
 * Date: December 14 2015 - December 18 2015
 * Version: Version 1 Console.
 * Java Version: Used 1.8 to compile.
 * Future Upgrades: Having an option to import
 * a .jpg and having the program translate text
 * to hex. Saves time from having to open up .jpg
 * into a hex editor, copying hex numbers, and saving
 * it to the text file. Also translating hex to text
 * to create a .jpg file. GUI version coming soon.
 * Description: This program is used on 
 * stegnographic images that are encrypted.
 * I made this program to be used for NCL 
 * (National Cyber league) security competitions. A hex file
 * with all numbers is put in and the program takes each one,
 * does an operation, and then puts the new hex number into
 * a new output file.
 * Instructions: Import a new .txt file into the project folder.
 * This file should contain hex numbers. EX: (05 a5 b3). Run
 * the program and pick the bit shift you want to do. An
 * output file with the same name as your file with a _bit shit
 * extension will appear.
 */

import java.io.IOException;
import java.util.Scanner;

/**
 * StegaMain is where all the classes
 * come together in one area. A user can
 * choose between xor, and, or, (and) a
 * hex shifting operation.
 * @author Amanda
 */
public class StegaMain 
{
	/**
	 * Where all the classes are called upon and used.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Scanner scOptions = new Scanner(System.in);
		FileHandle flHandle;
		String strChoice = "";
		boolean bOptionQuit = false;
	
	  do
      {
		 System.out.println("Enter in a number for an option:\n" +
                            "1. XOR bit shifting\n" +
                            "2. AND bit shifting\n" + 
                            "3. OR  bit shifting\n" +
                            "4. Hex bit shifting");
		System.out.print("Option Number: ");
		strChoice = scOptions.next();
		if (strChoice.equalsIgnoreCase("quit"))
		{
		   bOptionQuit = true;
		   break;
		}
		
       	flHandle = new FileHandle().getFile();
		
        if (!flHandle.getQuit())
    	{
    		switch (strChoice)
    		{
    		case "1":
    			XORShift xShift = new XORShift(flHandle.getScanner(), flHandle.getFileName());
    			System.out.print("What hex number would you like to bitwise XOR by: ");
    			int intXorNumber = scOptions.nextInt(16);
    			xShift.XORWriteShift(intXorNumber);
    			break;
    		case "2":
    			ANDShift aShift = new ANDShift(flHandle.getScanner(), flHandle.getFileName());
    			System.out.print("What hex number would you like to bitwise AND by: ");
    			int intAndNumber = scOptions.nextInt(16);
    			aShift.ANDWriteShift(intAndNumber);
    			break;
    		case "3":
    			ORShift oShift = new ORShift(flHandle.getScanner(), flHandle.getFileName());
    			System.out.print("What hex number would you like to bitwist OR by: ");
    			int intOrNumber = scOptions.nextInt(16);
    			oShift.ORWriteShift(intOrNumber);
    			break;
    		case "4":
    			HexBitShifting hexShift = new HexBitShifting(flHandle.getScanner(), flHandle.getFileName());
    			System.out.print("What direction would you like to go: (type left or right) ");
    			String strDirection = scOptions.next();
    			System.out.print("By how many bits would you like to shift? ");
    			int intShiftNumber = scOptions.nextInt();
    			hexShift.HEXWriteShift(strDirection, intShiftNumber);
    			break;
    		case "quit":
    			bOptionQuit = true;
    			break;
    	    default: 
    	    	System.out.println("The value of " + strChoice + " is not a valid numbered option. "
    	    			           + "Please try again.");
    		    break;
    		}
    	}
    	 System.out.println("\n--------------------------");
      }
      while (flHandle.getQuit() == false && bOptionQuit == false);
	}
}
