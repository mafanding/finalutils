package local.johnson.finalutils.event.datamerge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import local.johnson.finalutils.handle.datamerge.CapacityData;
import local.johnson.finalutils.panel.DataMerge;

public class Process implements ActionListener {
	
	protected final String OPTION_ALIDATA = "AliData";
	
	protected final String OPTION_CAPACITYDATA = "CapacityData";
	
	protected final String OPTION_DEEPDATA = "DeepData";
	
	protected int stateCode = 1;
	
	public DataMerge dataMerge;

	public Process(DataMerge dataMerge) {
		this.dataMerge = dataMerge;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (dataMerge.comboBox.getSelectedItem().toString()) {
			case OPTION_ALIDATA:
				//TODO
				break;
			case OPTION_CAPACITYDATA:
				stateCode = new CapacityData(dataMerge).handle();
				break;
			case OPTION_DEEPDATA:
				//TODO
				break;
			default:
				break;
		}
		
		if (stateCode == 1) {
			dataMerge.msg.setText("process failed");
		} else {
			dataMerge.msg.setText("process succeed");
		}
		dataMerge.repaint();
		stateCode = 1;
	}

}
