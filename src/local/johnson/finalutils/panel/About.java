package local.johnson.finalutils.panel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class About extends JPanel {
	
	protected final String VERSION = "1.0";
	
	protected final String AUTHOR = "johnson";
	
	protected final String EMAIL = "mfdboy@163.com";

	public About() {
		
		JLabel versionLabel = new JLabel();
		versionLabel.setText("version:");
		
		JTextPane versionTextPane = new JTextPane();
		versionTextPane.setText(VERSION);
		versionTextPane.setEditable(false);
		
		JLabel authorLabel = new JLabel();
		authorLabel.setText("author:");
		
		JTextPane authorTextPane = new JTextPane();
		authorTextPane.setText(AUTHOR);
		authorTextPane.setEditable(false);
		
		JLabel emailLabel = new JLabel();
		emailLabel.setText("email:");
		
		JTextPane emailTextPane = new JTextPane();
		emailTextPane.setText(EMAIL);
		emailTextPane.setEditable(false);
		
		add(versionLabel);
		add(versionTextPane);
		add(authorLabel);
		add(authorTextPane);
		add(emailLabel);
		add(emailTextPane);
		setLayout(new GridLayout(3, 2));
	}

	public About(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public About(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public About(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
