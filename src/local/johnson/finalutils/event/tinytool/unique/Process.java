package local.johnson.finalutils.event.tinytool.unique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import local.johnson.finalutils.panel.tinytool.Unique;

public class Process implements ActionListener {
	
	public Unique uniquePanel;

	public Process(Unique uniquePanel) {
		this.uniquePanel = uniquePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = uniquePanel.sourceText.getText();
		String inputs[] = input.split(System.getProperty("line.separator"));
		StringBuilder outputBuffer = new StringBuilder();
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String i : inputs) {
			if (!arrayList.contains(i)) {
				arrayList.add(i);
				outputBuffer.append(i).append(System.getProperty("line.separator"));
			}
		}
		uniquePanel.destText.setText(outputBuffer.toString());
	}

}
