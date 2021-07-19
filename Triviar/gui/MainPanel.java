package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main Panel. This is the landing panel when the application gets started.
 * @author Bunguiu Norales
 *
 */
public class MainPanel extends JPanel implements ActionListener 
{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. The layout and the starting elements are added and set.
	 */
	public MainPanel() 
	{
		
		setBackground(new Color(210, 187, 230));
		setLayout(new BorderLayout());
		add(namePanel(), BorderLayout.NORTH);
		add(butttonPanel(), BorderLayout.SOUTH);
		
	}

	
	/**
	 * set the name of the game.
	 * @return a panel with the game name.
	 */
	private	JPanel namePanel() 
	{
		JLabel label = new JLabel("QUIZZER");
		label.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 187, 230));
		panel.add(label);
		return panel;
	}
	
	/**
	 * sets he options for the game.
	 * @return a panel with game options.
	 */
	private JPanel butttonPanel() 
	{
		
		JPanel panel = new JPanel();
		
		JButton random = new JButton("Random Trivia");
		random.addActionListener(this);
		random.setBackground(new Color(203, 182, 119));
		JButton select = new JButton("Select Quiz");
		select.addActionListener(this);
		select.setBackground(new Color(203, 182, 119));
		GridBagConstraints c = new GridBagConstraints();
		// insets for all components 
		c.insets = new Insets(100, 2, 2, 2); 
 
       // column 0 
		c.gridx = 0; 
 
       // row 0 
		c.gridy = 0; 
 
       // increases components width
		c.ipadx = 350; 
 
       // increases components height 
		c.ipady = 15; 
		
		panel.setLayout(new GridBagLayout());
		panel.add(random, c);
		panel.setBackground(new Color(210, 187, 230));
		c.insets = new Insets(100, 2, 2, 2); 
		c.ipadx = 367; 
		c.gridy++;
		panel.add(select, c);
		
		return panel;
	}
	 
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		// If random trivia is selected then display a
		// random trivia
		if (command.equals("Random Trivia")) 
		{
			Random random = new Random();
			int randInt= random.nextInt(3)+1;
			try 
			{	
				setVisible(false);
				removeAll();
				add(new QuestionPanel("/resources/test" + randInt + ".txt", true));
				setVisible(true);
			} catch (NumberFormatException | IOException e1) 
			{
				JOptionPane.showMessageDialog(null, "Error");
			}
		}else if (command.equals("Select Quiz")) 
		{ // if users want to reads its own trivia
			
			String fullPath = "";
			JFileChooser jFileChooser = new JFileChooser("d:");
			int input = jFileChooser.showSaveDialog(this);
			if (input == JFileChooser.APPROVE_OPTION) 
			{
				
				setVisible(false);
				removeAll();
			
				File file = jFileChooser.getSelectedFile();
				fullPath = file.getAbsolutePath();
				
				try 
				{
					add(new QuestionPanel(fullPath, false));
					setVisible(true);
					
				} catch (NumberFormatException | IOException e1) 
				{
					JOptionPane.showMessageDialog(null, "Error loading file");
				}
			}
		}
		
	}
	
	/**
	 * renders the gif shown in the panel.
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		URL treeURL = null;
		
		treeURL = getClass().getResource("/resources/vs.gif");
		ImageIcon icon = new ImageIcon(treeURL);
		Image image = icon.getImage();
		g.drawImage(image, 150, 60, this);
		
	}
	
}
