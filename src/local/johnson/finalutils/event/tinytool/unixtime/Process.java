package local.johnson.finalutils.event.tinytool.unixtime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.math.NumberUtils;
import local.johnson.finalutils.panel.tinytool.Unixtime;

public class Process implements ActionListener {
	
	public Unixtime unixtime;

	public Process(Unixtime unixtime) {
		this.unixtime = unixtime;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sourceText = unixtime.sourceText.getText();
		if (sourceText.isEmpty()) {
			return;
		}
		if (NumberUtils.isCreatable(sourceText)) {
			unixtime.destText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new Long(sourceText) * 1000)));
		} else {
			try {
				unixtime.destText.setText(Timestamp.valueOf(sourceText).getTime()/1000 + "");
			} catch (IllegalArgumentException exception) {
				unixtime.destText.setText("Timestamp format must be yyyy-mm-dd hh:mm:ss");
				exception.printStackTrace();
			}
		}
	}

}
