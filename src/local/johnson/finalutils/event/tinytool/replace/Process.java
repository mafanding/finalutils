package local.johnson.finalutils.event.tinytool.replace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import local.johnson.finalutils.panel.tinytool.Replace;


public class Process implements ActionListener {
	
	public Replace replacePanel;

	public Process(Replace replacePanel) {
		this.replacePanel = replacePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = replacePanel.sourceText.getText();
		String search = replacePanel.search.getText();
		String replace = replacePanel.replace.getText();
		replacePanel.destText.setText(input.replaceAll(search, replace));
	}

}
