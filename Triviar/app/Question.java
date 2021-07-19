package app;
/**
 * 
 * @author Bunguiu Norales
 *
 */
public class Question 
{
	private String question;
	private String[] answers;
	private String correctAnswer;
	private String imagePath;
	
	/**
	 * Construct an Question object.
	 * @param question to be asked.
	 * @param answers options to the question.
	 * @param correctAnswer for the question.
	 * @param imagePath path to the image for the question.
	 */
	public Question(String question, String[] answers, String correctAnswer, String imagePath) 
	{
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.imagePath = imagePath;
		
	}
	
	/**
	 * get the question.
	 * @return the question.
	 */
	public String getQuestion() 
	{
		return question;
	}
	
	/**
	 * get the possible answers.
	 * @return the answers
	 */
	public String[] getAnswers() 
	{
		return answers;
	}
	
	/**
	 * get the correct answer to the question.
	 * @return the correct answer.
	 */
	public String getCorrectAnswer() 
	{
		return correctAnswer;
	}
	
	/**
	 * gets the path to the image.
	 * @return the image path
	 */
	public String getImagePath() 
	{
		return imagePath;
	}
}
