package local.johnson.finalutils.event.tinytool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import local.johnson.finalutils.panel.TinyTool;


public class ComboBox implements ActionListener {
	
	protected TinyTool tinyToolPanel;
	
	protected final String OPTION_UNIQUE = "Unique";

	protected final String OPTION_REPLACE = "Replace";
	
	protected final String OPTION_UNIXTIME = "Unixtime";

	protected final String OPTION_DEFAULT = "Default";

	public ComboBox(TinyTool tinyToolPanel) {
		this.tinyToolPanel = tinyToolPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tinyToolPanel.otherPane.removeAll();
		if (tinyToolPanel.comboBox.getItemAt(tinyToolPanel.comboBox.getSelectedIndex()).toString()
				.equals(OPTION_UNIQUE)) {
			tinyToolPanel.loadUniquePanel();
		} else if (tinyToolPanel.comboBox.getItemAt(tinyToolPanel.comboBox.getSelectedIndex()).toString()
				.equals(OPTION_REPLACE)) {
			tinyToolPanel.loadReplacePanel();
		} else if (tinyToolPanel.comboBox.getItemAt(tinyToolPanel.comboBox.getSelectedIndex()).toString()
				.equals(OPTION_UNIXTIME)) {
			tinyToolPanel.loadUnixtimePanel();
		} else {
			tinyToolPanel.loadDefaultPanel();
		}
		tinyToolPanel.otherPane.repaint();
	}

}
