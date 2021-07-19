package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import app.Question;
import app.io.QuestionReader;

class QuestionReaderTest 
{

	@Test
	void testQuestionReader() throws NumberFormatException, IOException
	{
		
		Question[] question;
//		Question testQuestion = new Question("how are you?", , correctAnswer, imagePath)
		
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Public\\eclipse-workspace\\Quizzer\\src\\testing\\test.txt"));
		
		question = QuestionReader.reader(reader);
		assertEquals("how are you?", question[0].getQuestion());
		assertEquals("C:\\Users\\Public\\eclipse-workspace\\Quizzer\\src\\prueba\\vortex00.png", question[0].getImagePath());
		assertEquals("4) shhh!", question[0].getCorrectAnswer());
		assertEquals("2) Bunguiu", question[1].getCorrectAnswer());
		
	}

}
