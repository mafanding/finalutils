package local.johnson.finalutils.event.tinytool.unixtime;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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
		if (NumberUtils.isCreatable(sourceText)) {
			unixtime.destText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new Long(sourceText) * 1000)));
		} else {
			System.out.println("i am formatter date");
		}
	}

}
