package local.johnson.finalutils.panel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import local.johnson.finalutils.event.datamerge.Choose;
import local.johnson.finalutils.event.datamerge.Process;

public class DataMerge extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final String CONFIG_PATH = "finalutils.xml";
	
	public JButton chooseButton = new JButton();

	public JButton processButton = new JButton();

	public JTextField input = new JTextField(15);

	public JLabel label = new JLabel("File:");

	public JFileChooser chooser = new JFileChooser();

	public JTextPane msg = new JTextPane();
	
	public JComboBox<?> comboBox;

	public DataMerge() {
		List<String> optList;
		Configurations configs = new Configurations();
		try {
			XMLConfiguration config = configs.xml(CONFIG_PATH);
			optList = config.getList(String.class, "datamerge.options.option");
			comboBox = new JComboBox<>(optList.toArray());
			chooseButton.setText("choose");
			chooseButton.addActionListener(new Choose(this));
			processButton.setText("process");
			processButton.addActionListener(new Process(this));
			add(comboBox);
			add(label);
			add(input);
			add(chooseButton);
			add(processButton);
			setLayout(new FlowLayout());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DataMerge(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public DataMerge(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public DataMerge(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
