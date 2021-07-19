package app.io;

import java.io.BufferedReader;
import java.io.IOException;

import app.Question;
/**
 * This class reads a quiestion from a file.
 *  and contructs an array of Question objects.
 * @author Bunguiu Norales
 *
 */
public class QuestionReader
{
	/**
	 * read the file.
	 * @param in stream
	 * @return question read
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	
	public static Question[] reader(BufferedReader in) throws NumberFormatException,IOException 
	{
		
		int n = Integer.parseInt((in.readLine()));
		Question[] questions = new Question[n];
		
		for (int i = 0; i < n; i++) 
		{
			
			String[] split = in.readLine().split("\\t");
			String fileQuestions = split[0];
			int numOfOps = Integer.parseInt(split[1]);
			String[] options = new String[numOfOps]; 
			int correctIndex = Integer.parseInt(split[2]) -1;
			String correctString ="";
			String imagePath = split[3];
			
			
			for (int j = 0; j < numOfOps; j++)
			{
				options[j] = in.readLine();
				if (j == correctIndex )
				{
					correctString = options[j];
				}
			}
			
			questions[i] = new Question(fileQuestions, options,
					correctString, imagePath);
			
		}
		
		return questions;
	}
	

}
