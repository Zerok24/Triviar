package gui;
import javax.swing.JFrame;

public class MainWindow extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * main window for the program.
	 */
	public MainWindow() 
	{
		
		setSize(700,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new MainPanel());
		setVisible(true);
	}
	
}
