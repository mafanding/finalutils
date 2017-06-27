package local.johnson.finalutils.handle.datamerge;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import local.johnson.finalutils.panel.DataMerge;

public class CapacityData {
	
	protected HashMap<String, FileInfo> fileInfoMap;
	
	protected HashMap<String, ArrayList<String>> relationTree;
	
	private final int EXPECT_LENGTH = 2;
	
	public DataMerge dataMerge;

	public CapacityData(DataMerge dataMerge) {
		this.dataMerge = dataMerge;
		fileInfoMap = new HashMap<String, FileInfo>();
		relationTree = new HashMap<String, ArrayList<String>>();
	}
	
	public int handle() {
		File directory = dataMerge.chooser.getSelectedFile();
		if (!directory.exists() || !directory.canRead() || !directory.isDirectory()) {
			return 1;
		}
		
		try {
			initFileInfoMap(directory);
			initRelationTree();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	protected void initFileInfoMap(File directory) throws EncryptedDocumentException, InvalidFormatException, IOException {
		File fileArr[] = directory.listFiles(new ExcelFilenameFilter());
		for (File file : fileArr) {
			Workbook wb = WorkbookFactory.create(file);
			Sheet sheet = wb.getSheetAt(0);
			Row header = sheet.getRow(sheet.getFirstRowNum());
			StringBuffer buffer = new StringBuffer();
			ArrayList<String> headersList = new ArrayList<String>();
			for (int i = header.getFirstCellNum(); i <= header.getLastCellNum(); i ++) {
				Cell cell = header.getCell(i);
				if (cell != null) {
					if (cell.getCellTypeEnum() == CellType.NUMERIC) {
						buffer.append(Double.toString(cell.getNumericCellValue()));
						headersList.add(Double.toString(cell.getNumericCellValue()));
					} else {
						buffer.append(cell.getStringCellValue());
						headersList.add(cell.getStringCellValue());
					}
				}
			}
			if (fileInfoMap.containsKey(buffer.toString())) {
				FileInfo fileInfo = fileInfoMap.get(buffer.toString());
				for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i ++) {
					fileInfo.appendFileContent(sheet.getRow(i));
				}
				fileInfoMap.put(buffer.toString(), fileInfo);
			} else {
				FileInfo fileInfo = new FileInfo();
				for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i ++) {
					fileInfo.appendFileContent(sheet.getRow(i));
				}
				fileInfo.setFilePath(file.getAbsolutePath()).setHeaderList(headersList).setHeaderStr(buffer.toString());
				fileInfoMap.put(buffer.toString(), fileInfo);
			}
			
			wb.close();
			file.delete();
		}
	}
	
	protected void initRelationTree() {
		if (fileInfoMap.size() != EXPECT_LENGTH) {
			return;
		}
		FileInfo fileInfoArr[] = fileInfoMap.values().toArray(new FileInfo[EXPECT_LENGTH]);
		for (int i = 0; i < EXPECT_LENGTH - 1; i ++) {
			for (int j = i; j < EXPECT_LENGTH - 1; j ++) {
				String pfn = computePrimaryFieldName(fileInfoArr[j].getHeadersList(), fileInfoArr[j + 1].getHeadersList());
				ArrayList<String> value;
				if (relationTree.containsKey(pfn)) {
					value = relationTree.get(pfn);
					if (!value.contains(fileInfoArr[j].getHeaderStr())) {
						value.add(fileInfoArr[j].getHeaderStr());
						fileInfoMap.put(fileInfoArr[j].getHeaderStr(), fileInfoMap.get(fileInfoArr[j].getHeaderStr()).increamRefCount());
					}
					if (!value.contains(fileInfoArr[j+1].getHeaderStr())) {
						value.add(fileInfoArr[j + 1].getHeaderStr());
						fileInfoMap.put(fileInfoArr[j + 1].getHeaderStr(), fileInfoMap.get(fileInfoArr[j + 1].getHeaderStr()).increamRefCount());
					}
				} else {
					value = new ArrayList<String>();
					value.add(fileInfoArr[j].getHeaderStr());
					fileInfoMap.put(fileInfoArr[j].getHeaderStr(), fileInfoMap.get(fileInfoArr[j].getHeaderStr()).increamRefCount());
					value.add(fileInfoArr[j + 1].getHeaderStr());
					fileInfoMap.put(fileInfoArr[j + 1].getHeaderStr(), fileInfoMap.get(fileInfoArr[j + 1].getHeaderStr()).increamRefCount());
				}
				relationTree.put(pfn, value);
			}
		}
	}
	
	protected void mergeFileInfoMap() {
		if (relationTree.isEmpty()) {
			return ;
		}
	}
	
	protected String computePrimaryFieldName(ArrayList<String> h1, ArrayList<String> h2) {
		for(String item : h1) {
			if (h2.contains(item)) {
				return item;
			}
		}
		return null;
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

class FileInfo {
	
	private String filePath;
	
	private ArrayList<String> headersList;
	
	private ArrayList<Row> fileContent;
	
	private String headerStr;
	
	private boolean isMaster;
	
	private String primaryFieldName;
	
	private int refCount;
	
	FileInfo() {
		isMaster = false;
		primaryFieldName = filePath = new String();
		headersList = new ArrayList<String>();
		fileContent = new ArrayList<Row>();
		headerStr = new String();
		refCount = 0;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public FileInfo setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
	public int getRefCount() {
		return refCount;
	}
	
	public FileInfo increamRefCount() {
		return increamRefCount(1);
	}
	
	public FileInfo decreamRefCount() {
		return decreamRefCount(1);
	}
	
	public FileInfo increamRefCount(int num) {
		refCount += num;
		return this;
	}
	
	public FileInfo decreamRefCount(int num) {
		refCount -= num;
		return this;
	}
	
	public String getHeaderStr() {
		return headerStr;
	}
	
	public FileInfo setHeaderStr(String headerStr) {
		this.headerStr = headerStr;
		return this;
	}
	
	public String getPrimaryFieldName() {
		return primaryFieldName;
	}
	
	public FileInfo setPrimaryFieldName(String primaryFieldName) {
		this.primaryFieldName = primaryFieldName;
		return this;
	}
	
	public boolean getIsMaster() {
		return isMaster;
	}
	
	public FileInfo setIsMaster(boolean isMaster) {
		this.isMaster = isMaster;
		return this;
	}
	
	public ArrayList<Row> getFileContent() {
		return fileContent;
	}
	
	public FileInfo setFileContent(ArrayList<Row> fileContent) {
		this.fileContent = fileContent;
		Row headerRow = this.fileContent.get(0);
		for (int i = headerRow.getFirstCellNum(); i <= headerRow.getLastCellNum(); i ++) {
			Cell cell = headerRow.getCell(i);
			if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				headersList.add(Double.toString(cell.getNumericCellValue()));
			} else {
				headersList.add(cell.getStringCellValue());
			}
		}
		return this;
	}
	
	public FileInfo appendFileContent(Row row) {
		fileContent.add(row);
		return this;
	}
	
	public ArrayList<String> getHeadersList() {
		return headersList;
	}
	
	public FileInfo setHeaderList(ArrayList<String> headersList) {
		this.headersList = headersList;
		return this;
	}
}
