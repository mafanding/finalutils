package local.johnson.finalutils.panel;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import local.johnson.finalutils.panel.tinytool.Default;
import local.johnson.finalutils.panel.tinytool.Replace;
import local.johnson.finalutils.panel.tinytool.Unique;

public class TinyTool extends JPanel {
	
	protected final String CONFIG_PATH = "finalutils.xml";
	
	protected JComboBox comboBox;
	
	protected JPanel topPane;
	
	protected JPanel otherPane;

	public TinyTool() {
		topPane = new JPanel();
		otherPane = new JPanel();
		List<String> optList;
		Configurations configs = new Configurations();
		try {
			XMLConfiguration config = configs.xml(CONFIG_PATH);
			optList = config.getList(String.class, "tinytool.options.option");
			comboBox = new JComboBox(optList.toArray());
			topPane.add(comboBox);
			switch (optList.get(comboBox.getSelectedIndex())) {
				case "Replace":
					loadReplacePanel();
					break;
				case "Unique":
					loadUniquePanel();
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
	}

	public void loadReplacePanel() {
		otherPane.add(new Replace());
	}

	public void loadDefaultPanel() {
		otherPane.add(new Default());
	}

}
