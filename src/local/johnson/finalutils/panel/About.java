package local.johnson.finalutils.panel;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class About extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String CONFIG_PATH = "finalutils.xml";

	public About() {
		
		Configurations configs = new Configurations();
		try {
			XMLConfiguration config = configs.xml(CONFIG_PATH);
			String version = config.getString("about.version");
			String author = config.getString("about.author");
			String email = config.getString("about.email");
			
			JLabel versionLabel = new JLabel();
			versionLabel.setText("version:");
			
			JTextPane versionTextPane = new JTextPane();
			versionTextPane.setText(version);
			versionTextPane.setEditable(false);
			
			JLabel authorLabel = new JLabel();
			authorLabel.setText("author:");
			
			JTextPane authorTextPane = new JTextPane();
			authorTextPane.setText(author);
			authorTextPane.setEditable(false);
			
			JLabel emailLabel = new JLabel();
			emailLabel.setText("email:");
			
			JTextPane emailTextPane = new JTextPane();
			emailTextPane.setText(email);
			emailTextPane.setEditable(false);
			
			add(versionLabel);
			add(versionTextPane);
			add(authorLabel);
			add(authorTextPane);
			add(emailLabel);
			add(emailTextPane);
			setLayout(new GridLayout(3, 2));
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
