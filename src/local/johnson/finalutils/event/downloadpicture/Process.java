package local.johnson.finalutils.event.downloadpicture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import local.johnson.finalutils.panel.DownloadPicture;

public class Process implements ActionListener {
	
	protected DownloadPicture downloadPicture;

	public Process(DownloadPicture downloadPicture) {
		this.downloadPicture = downloadPicture;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String response = local.johnson.finalutils.handle.DownloadPicture.handle(downloadPicture.chooser.getSelectedFile());
		downloadPicture.msg.setText(response);
		downloadPicture.add(downloadPicture.msg);
		downloadPicture.validate();
	}

}
