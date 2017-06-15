package local.johnson.finalutils.panel;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import local.johnson.finalutils.event.tinytool.ComboBox;
import local.johnson.finalutils.panel.tinytool.Default;
import local.johnson.finalutils.panel.tinytool.Replace;
import local.johnson.finalutils.panel.tinytool.Unique;
import local.johnson.finalutils.panel.tinytool.Unixtime;

public class TinyTool extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final String CONFIG_PATH = "finalutils.xml";
	
	public JPanel topPane;
	
	public JPanel otherPane;
	
	public JComboBox<?> comboBox;

	public TinyTool() {
		topPane = new JPanel();
		otherPane = new JPanel();
		List<String> optList;
		Configurations configs = new Configurations();
		try {
			XMLConfiguration config = configs.xml(CONFIG_PATH);
			optList = config.getList(String.class, "tinytool.options.option");
			comboBox = new JComboBox<>(optList.toArray());
			comboBox.addActionListener(new ComboBox(this));
			topPane.add(comboBox);
			switch (optList.get(comboBox.getSelectedIndex())) {
				case "Replace":
					loadReplacePanel();
					break;
				case "Unique":
					loadUniquePanel();
					break;
				case "Unixtime":
					loadUnixtimePanel();
					break;
				default:
					loadDefaultPanel();
			}
			setLayout(new BorderLayout());
			add(topPane, BorderLayout.NORTH);
			add(otherPane, BorderLayout.CENTER);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public TinyTool(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public TinyTool(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public TinyTool(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public void loadUniquePanel() {
		otherPane.add(new Unique());
		otherPane.validate();
	}

	public void loadReplacePanel() {
		otherPane.add(new Replace());
		otherPane.validate();
	}
	
	public void loadUnixtimePanel() {
		otherPane.add(new Unixtime());
		otherPane.validate();
	}

	public void loadDefaultPanel() {
		otherPane.add(new Default());
		otherPane.validate();
	}

}
