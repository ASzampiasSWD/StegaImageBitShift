import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The FileHandle class handles all
 * file operations.
 * @author Amanda
 */
public class FileHandle 
{
	private String strInputFile;
	private Scanner scKeyboard;
	private boolean bQuit;
	
	/**
	 * FileHandle() constructor.
	 * Used at the start for the first
	 * flHandle fl = new FileHandle().getFile();
	 * in the main class.
	 */
    public FileHandle()
	{
		this.bQuit = false;
		this.scKeyboard = new Scanner(System.in);
	}
    
    /**
     * FileHandle (strInputFile, scKeyboard, bQuit)
     * @param strInputFile the new name used for output files.
     * @param scKeyboard the scanner that contains the file contents.
     * @param bQuit whether the use wants to quit or not.
     */
	public FileHandle(String strInputFile, Scanner scKeyboard, boolean bQuit)
	{
	   this.strInputFile = strInputFile;
	   this.scKeyboard = scKeyboard;
	   this.bQuit = bQuit;
	}
	
	/**
	 * getQuit() access modifier
	 * to bQuit. We need this to quit
	 * the application.  
	 * @return bQuit
	 */
	public boolean getQuit()
	{
	  return bQuit;	
	}
	
	/**
	 * getScanner() access modifier
	 * to scKeyboard. We need this 
	 * for the readHexFile() function.
	 * @return scKeyboard
	 */
	public Scanner getScanner()
	{
		return scKeyboard;
	}
	
	/**
	 * getFileName() access modifier
	 * to strInputFile. We need this
	 * for future output files.
	 * @return strInputFile
	 */
	public String getFileName()
	{
		return strInputFile;
	}
	
	/**
	 * Remove the .png .jpeg .bmp extensions.
	 * This way we can make new output files.
	 * @return strFixName
	 */
	public String fixFileName()
	{
		int i = strInputFile.indexOf('.');
		String strFixName = strInputFile.substring(0, i);
		return strFixName;
	}
	
	/**
	 * getFile() gets a file to read the
	 * hex numbers.
	 * @return strInputFile;
	 */
	public FileHandle getFile()
	{
	  boolean bFoundFile = false;
	  
	  while (!bFoundFile)
	  {
		scKeyboard = new Scanner(System.in);
		System.out.print("Input a file: ");
		strInputFile = scKeyboard.next();
		
		if (strInputFile.equalsIgnoreCase("quit"))
		{
			scKeyboard.close();
			return new FileHandle(strInputFile, scKeyboard, bQuit = true);
		}
		try
		{
			scKeyboard = new Scanner(new File(strInputFile));
			bFoundFile = true;
			return new FileHandle(fixFileName(), scKeyboard, bQuit);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("The file " + strInputFile + " does "
					           + "not exist. Please try again.");
		}
	 }
	  return new FileHandle("never reached error", scKeyboard, bQuit);
	}
}