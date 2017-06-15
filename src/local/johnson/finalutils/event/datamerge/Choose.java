package local.johnson.finalutils.event.datamerge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import local.johnson.finalutils.panel.DataMerge;

public class Choose implements ActionListener {
	
	public DataMerge dataMerge;

	public Choose(DataMerge dataMerge) {
		this.dataMerge = dataMerge;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dataMerge.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dataMerge.chooser.setDialogTitle("select a directory");
		int returnVal = dataMerge.chooser.showOpenDialog(dataMerge.chooser);
		if (returnVal == JFileChooser.OPEN_DIALOG) {
			dataMerge.input.setText(dataMerge.chooser.getSelectedFile().getAbsolutePath());
		}
	}

}
