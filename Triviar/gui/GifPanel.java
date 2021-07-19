package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.GlyphVector;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * displays the a random gif depending if the .
 * answer was correct or not
 * 
 * @author Bunguiu Norales
 *
 */
public class GifPanel extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean correct;
	private String correctAnswer;
	private Random random;
	private int randInt;
	
	
	/**
	 * default constructor. 
	 * @param correct was the question answered correctly?
	 * @param correctAnswer of the question.
	 */
	public GifPanel(boolean correct, String correctAnswer) 
	{
		this.correct = correct;
		this.correctAnswer = correctAnswer;
		setPreferredSize(new Dimension(400,400));
		random = new Random();
		randInt = random.nextInt(5)+1;
		setBackground(new Color(210, 187, 230));
	}
	
	/**
	 * renders a random gif depending on the correctness of the answer.
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ImageIcon icon = null;
		GlyphVector glyphVector;
		Font font = new Font("Serif", Font.PLAIN, 50);
		URL treeURL = null;
		int x;
		if (correct)
		{
			
			glyphVector = font.createGlyphVector(g2.getFontRenderContext(), "CORRECT!");
			
			String randomGIf = "/resources/correct" + randInt + ".gif";
			treeURL = getClass().getResource(randomGIf);
			
			icon = new ImageIcon(treeURL);
			Image image = icon.getImage();
			x = (this.getWidth() - image.getWidth(null)) / 2;
			
			g2.drawImage(image, x, 150, this);
			g2.setColor(Color.green);
			g2.drawGlyphVector(glyphVector, 150,50);
			
			font = new Font("Serif", Font.PLAIN, 25);
			g2.setColor(Color.BLACK);
			glyphVector = font.createGlyphVector(g2.getFontRenderContext(),
					correctAnswer + " is the correct answer!");
			g2.drawGlyphVector(glyphVector, 160,100);
			
		}else 
		{
			
			String randomGIf = "/resources/wrong" + randInt + ".gif";
			treeURL = getClass().getResource(randomGIf);
			icon = new ImageIcon(treeURL);
			Image image = icon.getImage();
			x = (this.getWidth() - image.getWidth(null)) / 2;
		    
			glyphVector = font.createGlyphVector(g2.getFontRenderContext(),
					"INCORRECT!");
			
			g2.setColor(Color.RED);
			g2.drawImage(image, x, 150, this);
			g2.drawGlyphVector(glyphVector, 160,50);
			
			font = new Font("Serif", Font.PLAIN, 25);
			glyphVector = font.createGlyphVector(g2.getFontRenderContext(),
					"Correct Answer: "+ correctAnswer);
			g2.setColor(Color.BLACK);
			g2.drawGlyphVector(glyphVector, 160,100);
			
		}
		
		
	}
	
	

}
