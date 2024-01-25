package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class probe extends JPanel{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					probe window = new probe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public probe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JButton btn = new JButton();
		RoundBorder bordeRedondo = new RoundBorder(0);
		btn.setBounds(100, 70, 100, 40);
		btn.setBorder(bordeRedondo);
		frame.getContentPane().add(btn);
	}
	
	
}

class RoundBorder implements Border{
	private int radio;
	
	public RoundBorder(int radio) {
		this.radio = radio;
	}
	
	public int getRadio() {
		return this.radio;
	}
	
	public void setRadio(int radio) {
		this.radio = radio;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		Insets insets = getBorderInsets(c);
		int arcWidth = radio * 2;
		int arcHeigth = radio * 2;
		
		Shape borderShape = new RoundRectangle2D.Double(x, y, width - 1, height - 1, arcWidth, arcHeigth);
		g.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeigth);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		// TODO Auto-generated method stub
		return new Insets(radio, radio, radio, radio);
	}

	@Override
	public boolean isBorderOpaque() {
		// TODO Auto-generated method stub
		return true;
	}
	
}


