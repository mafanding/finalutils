package local.johnson.finalutils.handle.datamerge;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import local.johnson.finalutils.panel.DataMerge;

public class CapacityData {
	
	protected File filesList[];
	
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
		
		filesList = directory.listFiles(new ExcelFilenameFilter());
		
		mergeSimilarFiles();
		
		if (filesList.length != 2) {
			return 1;
		}
		
		computePrimaryFieldAndMasterFile();
		
		return 0;
	}
	
	protected void mergeSimilarFiles() {
		HashMap<String, File> mapList = new HashMap<String, File>();
		for (File file : filesList) {
			try {
				Workbook wb = WorkbookFactory.create(file);
				Iterator<Cell> firstRow = wb.getSheetAt(0).getRow(0).cellIterator();
				String header = "";
				while (firstRow.hasNext()) {
					header.concat(firstRow.next().getStringCellValue());
				}
				if (mapList.containsKey(header)) {
					//TODO
				} else {
					System.out.println(header);
					mapList.put(header, file);
				}
			} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void computePrimaryFieldAndMasterFile() {
		//TODO
	}

}

/**
 * An instance of this class can be used to control the files returned
 * be a call to the listFiles() method when made on an instance of the
 * File class and that object refers to a folder/directory
 */
class ExcelFilenameFilter implements FilenameFilter {

    /**
     * Determine those files that will be returned by a call to the
     * listFiles() method. In this case, the name of the file must end with
     * either of the following two extension; '.xls' or '.xlsx'. For the
     * future, it is very possible to parameterise this and allow the
     * containing class to pass, for example, an array of Strings to this
     * class on instantiation. Each element in that array could encapsulate
     * a valid file extension - '.xls', '.xlsx', '.xlt', '.xlst', etc. These
     * could then be used to control which files were returned by the call
     * to the listFiles() method.
     *
     * @param file An instance of the File class that encapsulates a handle
     *             referring to the folder/directory that contains the file.
     * @param name An instance of the String class that encapsulates the
     *             name of the file.
     * @return A boolean value that indicates whether the file should be
     *         included in the array retirned by the call to the listFiles()
     *         method. In this case true will be returned if the name of the
     *         file ends with either '.xls' or '.xlsx' and false will be
     *         returned in all other instances.
     */
    @Override
    public boolean accept(File file, String name) {
        return(name.endsWith(".xls") || name.endsWith(".xlsx"));
    }
}
