package local.johnson.finalutils.handle.datamerge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import local.johnson.finalutils.panel.DataMerge;

public class CapacityData {
	
	protected ArrayList<File> filelist = new ArrayList<File>();
	
	protected String primaryField = "";
	
	protected File masterFile = null;
	
	public DataMerge dataMerge;

	public CapacityData(DataMerge dataMerge) {
		this.dataMerge = dataMerge;
	}
	
	public int handle() {
		File directory = dataMerge.chooser.getSelectedFile();
		if (!directory.exists() || !directory.canRead() || !directory.isDirectory()) {
			return 1;
		}
		
		File files[] = directory.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".xlsx")) {
				filelist.add(file);
			}
		}
		
		mergeSimilarFiles();
		
		if (filelist.size() != 2) {
			return 1;
		}
		
		computePrimaryFieldAndMasterFile();
		
		return 0;
	}
	
	protected void mergeSimilarFiles() {
		//TODO
	}
	
	protected void computePrimaryFieldAndMasterFile() {
		ArrayList<String> mapList = new ArrayList<String>();
		for (File file : filelist) {
			try {
				Workbook wb = Workbook.getWorkbook(file);
				Cell headers[] = wb.getSheet(0).getRow(0);
				for (Cell header : headers) {
					if (!mapList.contains(header.getContents())) {
						mapList.add(header.getContents());
					} else {
						primaryField = header.getContents();
						masterFile = file;
						wb.close();
						return;
					}
				}
				wb.close();
			} catch (BiffException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
