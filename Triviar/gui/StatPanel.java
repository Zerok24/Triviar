package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * displays the stats for the trivia.
 * number of answers missed and the correct answers.
 * @author Bunguiu Norales
 *
 */
public class StatPanel extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;

	/**
	 * construct an StatPanel object.
	 * @param grade grade gotten in the trivia/quiz
	 * @param wrongAnswers
	 */
	public StatPanel(double grade, ArrayList<String> wrongAnswers)
	{
		setLayout(new BorderLayout());
		JLabel label = new JLabel("Your Grade is: " + grade + "%" );
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 187, 230));
		panel.add(label);
		add(panel, BorderLayout.NORTH);
		add(centerPanel( wrongAnswers), BorderLayout.CENTER);
		add(southPanel(), BorderLayout.SOUTH);
	}
	

	/**
	 * Display a text area with the questions missed and the correct answer of them.
	 * @param wrongAnswers
	 * @return Jpanel
	 */
	private  JPanel centerPanel( ArrayList<String> wrongAnswers) 
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("You missed " + wrongAnswers.size()/3 + " questions");
		panel.setLayout(new BorderLayout());
		
		JTextArea textArea = new JTextArea(10,10);
		textArea.setText(textArea.getText() + label.getText() +"\n");
		textArea.setBackground(new Color(210, 187, 230));
		
		for (int i = 0; i < wrongAnswers.size(); i+=3) 
		{
			textArea.setText(textArea.getText() + "\nQuestion: " 
					+ wrongAnswers.get(i) + "\n");
			
			textArea.setText(textArea.getText() + "You selected: " 
					+ wrongAnswers.get(i+1) +  "\n");
			textArea.setText(textArea.getText() + "Correct answer: "
						+ wrongAnswers.get(i+2) + "\n");

		}
		panel.add(textArea, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * set the return to main menu button.
	 * @return a JPanel with the button on it.
	 */
	private JPanel southPanel() 
	{
		
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Return to main");
		button.addActionListener(this);
		button.setBackground(new Color(203, 182, 119));
		panel .add(button);
		panel.setBackground(new Color(210, 187, 230));
		return panel;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		if (command.equals("Return to main")) 
		{ // if user want to return to main menu
			this.setVisible(false);
			removeAll();
			add(new MainPanel());
			setVisible(true);
			
			
		}
		
	}
	
}
