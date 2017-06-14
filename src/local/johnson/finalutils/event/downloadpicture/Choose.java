package local.johnson.finalutils.event.downloadpicture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import local.johnson.finalutils.panel.DownloadPicture;

public class Choose implements ActionListener {
	
	protected DownloadPicture downloadPicture;

	public Choose(DownloadPicture downloadPicture) {
		this.downloadPicture = downloadPicture;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		downloadPicture.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		downloadPicture.chooser.setFileFilter(downloadPicture.filter);
		downloadPicture.chooser.setDialogTitle("select a excel file");
		int returnVal = downloadPicture.chooser.showOpenDialog(downloadPicture.chooser);
		if (returnVal == JFileChooser.OPEN_DIALOG) {
			downloadPicture.input.setText(downloadPicture.chooser.getSelectedFile().getName());
		}
	}

}
