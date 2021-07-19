package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import app.Question;
import app.io.QuestionReader;

/**
 * 
 * This object displays the different questions in the file.
 * @author Bunguiu Norales
 *
 */

public class QuestionPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Question[] questions;
	private int current = 0;
	private ButtonGroup group;
	private int result = 0;
	private JRadioButton[] radioButtons;
	private ArrayList<String> wrongAnswerStrings = new ArrayList<String>();
	private boolean random;
	private Clip clip;


	/**
	 * 
	 * Sets the initial state of the panel. 
	 * 
	 * @param path to the file
	 * @param random is the selected file random or it was read?
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public QuestionPanel(String path, boolean random) throws NumberFormatException, IOException 
	{
		BufferedReader reader = null;
		this.random = random;
		if (random)
		{
			InputStream in = getClass().getResourceAsStream(path); 
			reader = new BufferedReader(new InputStreamReader(in));
		}else 
		{
			reader = new BufferedReader(new FileReader(new File(path)));
		}

		this.questions = QuestionReader.reader(reader);
		
		setLayout(new BorderLayout());
		add(new ImagePanel(this.questions[current].getImagePath(),
				random), BorderLayout.NORTH);
		
		add(centerPanel(questions[current].getAnswers(), questions[current].getQuestion() ),
				BorderLayout.CENTER );
		
		add(southPanel(), BorderLayout.SOUTH);

	}
	
	/**
	 * returns a JPanel with the buttons for the different options to the questions.
	 * it also adds the question to be display. 
	 * 
	 * @param options to be display for the given question
	 * @param question question to be display
	 * @return center panel.
	 */
	private JPanel centerPanel(String[] options, String question) 
	{
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2,2));
		
		radioButtons = new JRadioButton[options.length];
		group = new ButtonGroup();
		
		for (int i = 0; i < radioButtons.length; i++) 
		{
			radioButtons[i] = new JRadioButton(options[i]);
			radioButtons[i].setActionCommand(options[i]);
			radioButtons[i].setBackground(new Color(210, 187, 230));
			group.add(radioButtons[i]);
			panel.add(radioButtons[i]);
		}
		
		mainPanel.add(new JLabel(question), BorderLayout.NORTH);
		
		mainPanel.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(210, 187, 230));
		mainPanel.setBackground(new Color(210, 187, 230));
		return mainPanel;
		
	}
	
	/**
	 *  Sets the submit button.
	 * @return A JPanel with the submit button.
	 */
	private JPanel southPanel()
	{
		JPanel panel = new JPanel();
		JButton button = new JButton("Submit");
		button.addActionListener(this);
		button.setBackground(new Color(203, 182, 119));
		panel.add(button);
		panel.setBackground(new Color(210, 187, 230));
		return panel;
	}
	
	/**
	 * Display the next question, when the next button is press.
	 */
	private void next() 
	{
		current++;
		setVisible(false);
		removeAll();
		if ( current <questions.length ) 
		{

			add(new ImagePanel(this.questions[current].getImagePath(), random),
					BorderLayout.NORTH);
			
			add(centerPanel(this.questions[current].getAnswers(),
					questions[current].getQuestion()), BorderLayout.CENTER );
			
			add(southPanel(), BorderLayout.SOUTH);
				
		}else
		{
			double nota = result * 100/ questions.length;
			add(new StatPanel(nota, wrongAnswerStrings));
	
		}
		setVisible(true);
		
	}
	
	/**
	 * Checks whether the submitted answer was correct.
	 * if it was not, then it stores it to be presented at the end
	 * of the quiz/trivia
	 */
	private void checker()
	{
		
		JButton button = new JButton("Next");
		button.addActionListener(this);
		button.setBackground(new Color(203, 182, 119));
		
		if (group.getSelection()!=null) 
		{
			
			setVisible(false);
			removeAll();
			
			if (group.getSelection().getActionCommand()
					.equals(questions[current].getCorrectAnswer() ))
			{
				result++;

				add(new GifPanel(true, questions[current].getCorrectAnswer()),
						BorderLayout.CENTER);
				add(button, BorderLayout.SOUTH);
				answerSound("/resources/correct.wav");
				
			}else 
			{
				add(new GifPanel(false,questions[current].getCorrectAnswer()),
						BorderLayout.CENTER);
				
				answerSound("/resources/wrong.wav");
				add(button, BorderLayout.SOUTH);
				
				wrongAnswerStrings.add(questions[current].getQuestion());
				wrongAnswerStrings.add(group.getSelection().getActionCommand());
				wrongAnswerStrings.add(questions[current].getCorrectAnswer());
			}
			
			setVisible(true);
		}else 
		{
			
			JOptionPane.showMessageDialog(null, "Please select an answer! ");
		}

	}
	
	/**
	 * plays the given sound.
	 * @param soundPath to be play.
	 */
	public void answerSound(String soundPath) 
	{
		
		AudioInputStream is= null;
		try
		{
			if (!soundPath.contains(":")) 
			{
				is = AudioSystem.getAudioInputStream(getClass()
						.getResource(soundPath));
			}else
			{
				is = AudioSystem.getAudioInputStream(new File(soundPath));
			}
			
			clip = AudioSystem.getClip();
			clip.open(is);
			clip.setFramePosition(0);
			clip.start();
				
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			JOptionPane.showMessageDialog(null, "Could not load sound!");
		
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		String commandString = e.getActionCommand();
		
		if (commandString.equals("Next")) 
		{ //when "next" gets pressed
			next();
			
		}else if (commandString.equals("Submit")) 
		{ // when "Submit" gets pressed
			checker();
		}
	}
	
}
