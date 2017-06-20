package local.johnson.finalutils.handle.datamerge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
		
		filesList = directory.listFiles(new ExcelFilenameFilter());
		
		if (filesList.length != 2) {
			return 1;
		}
		
		computePrimaryFieldAndMasterFile();
		
		return 0;
	}
	
	protected void mergeSimilarFiles() {
		HashMap<String, File> mapList = new HashMap<String, File>();
		HashMap<String, ArrayList<Row>> mergedList = new HashMap<String, ArrayList<Row>>();
		for (File file : filesList) {
			try {
				Workbook wb = WorkbookFactory.create(file);
				Row firstRow = wb.getSheetAt(0).getRow(0);
				RowConsumer rowConsumer = new RowConsumer();
				firstRow.forEach(rowConsumer);
				String header = rowConsumer.getHeaderString();
				if (mapList.containsKey(header)) {
					if (!mergedList.containsKey(header)) {
						ArrayList<Row> rowList = new ArrayList<Row>();
						Workbook mainwb = WorkbookFactory.create(mapList.get(header));
						Sheet mainSheet = mainwb.getSheetAt(0);
						for (int i = 0; i <= mainSheet.getLastRowNum(); i++) {
							rowList.add(mainSheet.getRow(i));
						}
						mergedList.put(header, rowList);
					}
					ArrayList<Row> rowList = mergedList.get(header);
					for (int i = 1; i <= wb.getSheetAt(0).getLastRowNum(); i++) {
						rowList.add(wb.getSheetAt(0).getRow(i));
					}
					mergedList.put(header, rowList);
					file.delete();
				} else {
					mapList.put(header, file);
				}
			} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!mergedList.isEmpty()) {
			mergedList.forEach(new MergedBiConsumer(mapList));
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

class RowConsumer implements Consumer<Object> {
	
	protected StringBuffer header;
	
	RowConsumer() {
		header = new StringBuffer();
	}

	@Override
	public void accept(Object t) {
		header.append(t.toString());
	}
	
	public String getHeaderString() {
		return header.toString();
	}
	
}

class MergedBiConsumer implements BiConsumer<String, ArrayList<Row>> {
	
	protected HashMap<String, File> mapList;
	
	MergedBiConsumer(HashMap<String, File> mapList) {
		this.mapList = mapList;
	}

	@Override
	public void accept(String t, ArrayList<Row> u) {
		try {
			Workbook wb = WorkbookFactory.create(mapList.get(t));
			rebuild(wb, u);
			wb.getClass().getMethod("write", null).invoke(wb, null);
			wb.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void rebuild(Workbook wb, ArrayList<Row> u) {
		wb.removeSheetAt(0);
		Sheet sheet = wb.createSheet();
		for (int i = 0; i < u.size(); i++) {
			Row row = u.get(i);
			RowConsumer rowConsumer = new RowConsumer(sheet, i);
			row.forEach(rowConsumer);
			sheet = rowConsumer.getSheet();
		}
	}
	
	class RowConsumer implements Consumer<Cell> {
		
		protected Sheet sheet;
		
		protected int i;
		
		RowConsumer (Sheet sheet, int i) {
			this.sheet = sheet;
			this.i = i;
			sheet.createRow(i);
		}

		@Override
		public void accept(Cell t) {
			Cell cell = sheet.getRow(i).createCell(t.getColumnIndex());
			if (t.getCellTypeEnum() == CellType.NUMERIC) {
				cell.setCellValue(t.getNumericCellValue());
			} else {
				cell.setCellValue(t.getStringCellValue());
			}
		}
		
		public Sheet getSheet() {
			return sheet;
		}
		
	}
	
}
