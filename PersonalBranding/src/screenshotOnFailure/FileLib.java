package screenshotOnFailure;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileLib {

public String getPropertyKeyValue(String path,String key)
{
	FileInputStream ip=null;
	Properties prop=null;
	try{
		ip=new FileInputStream(path);
		prop= new Properties();
		prop.load(ip);
	}
	catch(Throwable e){
		
		e.printStackTrace();
	}
	return prop.getProperty(key);
}

public static String getCurrentDateTime() {
	Calendar currentDate = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
	return formatter.format(currentDate.getTime());
}

public String getExcelData(String excelPath,String sheetName,int rowNum,int cellNum)
{
	FileInputStream ip=null;
	Workbook wb=null;
	try
	{
		ip=new FileInputStream(excelPath);
		wb=WorkbookFactory.create(ip);
		
	}
	catch(Throwable e){
		
		e.printStackTrace();
	}
	
	Sheet sh = wb.getSheet(sheetName);
	String data=sh.getRow(rowNum).getCell(cellNum).toString();
	return data;
}
}




