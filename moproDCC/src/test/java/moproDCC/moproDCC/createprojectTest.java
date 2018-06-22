package moproDCC.moproDCC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.moproDCC.createnewprojectPage;
import pages.moproDCC.loginPage;
import pages.moproDCC.projectPage;
import utilities.moproDCC.utility;

public class createprojectTest extends baseFile {

	loginPage loginpg;
	projectPage projpg;
	createnewprojectPage createpg;	
	String shname = "CreateProject";		
	String excelPath = prop.getProperty("excelpath");
	
	public createprojectTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {		
		initialize();
		loginpg = new loginPage();			
		createpg = new createnewprojectPage();
	}	
	
	@Test 	
	public void createProject() throws Exception{	
		try {
			
			File excel = new File(excelPath);
			FileInputStream inpStream = new FileInputStream(excel);
			XSSFWorkbook book = new XSSFWorkbook(inpStream);
			XSSFSheet sh = book.getSheet("CreateProject");			
			Runtime.getRuntime().exec(
		            "cmd /c taskkill /f /im excel.exe");
			int rowCount = sh.getLastRowNum();			
			
		System.out.println("Number of Rows :" + rowCount);
		
		for (int i=1; i<=rowCount; i++ ) {			
			Row r = sh.getRow(i);	
			
			if (r.getCell(9) == null) {
				
				String environment;				
				if (r.getCell(0) != null) {
					 environment = r.getCell(0).toString();
				} else {					
					environment = "QA";
				}				
				String titl;
				if (r.getCell(1) != null) {
					titl = r.getCell(1).toString()+"_"+utility.getDateTime(); 
				} else {
					titl = "Test";
				}
				
				String clust;
				if 	(r.getCell(2) !=null) {
					clust = r.getCell(2).toString(); 
					} else {
						clust = "one";
					}
				String version;
				if (r.getCell(3) != null) {
					version = r.getCell(3).toString(); 
					} else {
						version = "v2";
					}
				String bundl ;
				if (r.getCell(4) !=null) {
					bundl = r.getCell(4).toString(); 
					} else {
						bundl = "Default";
					}				
				String ecomm;
				if (r.getCell(5) != null) {					
					ecomm = r.getCell(5).toString();
				} else {					
					ecomm = "no";
				}
				String brand; 
				if (r.getCell(6)!= null) {
					brand = r.getCell(6).toString();	
				} else {
					brand = "no";
				}				
				int quantity;				 
				if (r.getCell(7) != null) {
					double quan = r.getCell(7).getNumericCellValue();
					quantity = (int) quan;
					}  else {
						quantity = 1;
					}				
				String recipient;
				if (r.getCell(8)!= null) { 
					recipient = r.getCell(8).toString();
				} else {
					recipient = prop.getProperty("testmailid");
				}				
					
					String projUrl = "" ;
					driver.manage().deleteAllCookies(); 
					utility.selectEnvironment(environment);	
					projpg = loginpg.login();		
					for (int k=0; k<quantity; k++) {
					projpg.gotoCreateProject();
					createpg.createNewProject(titl, clust, bundl, version, ecomm, brand);
					projUrl = projUrl + "Project " + i + ":" +  projpg.geturl() + System.lineSeparator();
					String pgname = utility.getPgTitle();
					Assert.assertEquals(pgname, "Projects | MOpro Administration"); 		
					}
					
					
					Cell cell = r.createCell(9);
	        		 cell.setCellValue("Create");
	        		 inpStream.close();
	        		 FileOutputStream os = new FileOutputStream(excelPath);
	        		 book.write(os);
	        		 os.close();	        		 
	        		 inpStream = new FileInputStream(excel);
			       
			        String subject = "Project Created";
					String msg = projUrl;		
					utility.sendMail(recipient, subject, msg);	
					
														
			} 					
		}	 
	} 				catch (FileNotFoundException e) {
						e.printStackTrace();
						}		 
	}	
	
	@AfterMethod
	public void close() {
		driver.quit();
		utility.openExcel();
	}	
}
