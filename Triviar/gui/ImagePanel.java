package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * The purpose for this panel is to resize and display 
 * given the path to the image.
 * @author Bunguiu Norales
 *
 */
public class ImagePanel extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	
	private String imagePath;
	private boolean random;
	
	/**
	 * default constructor.
	 * 
	 * @param imagePath path to the image.
	 * @param random trivia?
	 */
	public ImagePanel(String imagePath, boolean random) 
	{
		this.random = random;
		this.imagePath = imagePath;
		setPreferredSize(new Dimension(400,400));
		setBackground(new Color(210, 187, 230));
	}
	
	
	/**
	 * resize, renders and display the image.
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{

		super.paintComponent(g);

		ImageIcon icon = null;
		
		if (random) 
		{
			URL treeURL = null;
			treeURL = getClass().getResource(imagePath);
			icon= new ImageIcon(treeURL);
			
		}else 
		{

			icon = new ImageIcon(imagePath);
		}
		
		Image image = icon.getImage();
		Image modified =image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		
		icon = new ImageIcon(modified);
		g.drawImage(modified, 145, 0, this);
		
	}
	

}
