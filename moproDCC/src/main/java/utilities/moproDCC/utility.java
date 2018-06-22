package utilities.moproDCC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import moproDCC.moproDCC.baseFile;

public class utility extends baseFile{
	
	public static FileInputStream ipstrm;
	
	// Get the Page Title
	public static String getPgTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	//Take Screenshot 	
	 public static void takeScreenshot() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");			
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));			
			}
	 // Select Environment 
	 public static void selectEnvironment(String env) {
		  if (env.equalsIgnoreCase("Staging")) {
			 driver.get(prop.getProperty("stgurl"));
		 } else if (env.equalsIgnoreCase("Production")) {
			 driver.get(prop.getProperty("produrl"));
		 } else {
			 driver.get(prop.getProperty("qaurl"));
		 }
	 }
	 
	 // Read Excel File 
	 public static Workbook readExcel() throws Exception {	
		    String flpath = prop.getProperty("excelpath");		   
			File fl = new File (flpath);
			ipstrm = new FileInputStream(fl);
			 Runtime.getRuntime().exec(
			            "cmd /c taskkill /f /im excel.exe");
			String flext = flpath.substring(flpath.indexOf("."));
			Workbook wb = null;
			if (flext.equalsIgnoreCase(".xls")) {		
				wb = new HSSFWorkbook(ipstrm);
			} else if (flext.equalsIgnoreCase(".xlsx")) {
				wb = new XSSFWorkbook(ipstrm);
			}			
			return wb;
			}
	 
	 // Alert Message
	 public static void alert() {
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("alert('Project Already Created')");
	 }	 
	 

	// Send Mail Via G-mail
	 public static void sendMail(String recipient, String subject , String msg) {
			final String username = prop.getProperty("testmailid");
			final String password = prop.getProperty("testmailpswd");
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(prop.getProperty("testmailid")));
					message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
					message.setSubject(subject);
					message.setText(msg + System.lineSeparator() + "Thanks" + System.lineSeparator() + "QA Team");
					Transport.send(message);
					System.out.println("Done");

				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
	 
	 // Get Date and Time 
	 public static String getDateTime() {
		 Date date = new Date();
		 SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_HHmmss");
		 String out = format.format(date);
		 return out;
	 }
	 
	 // open a excel file 
	 
	 public static void openExcel() {
		 //String path = val;
		 try{  
	          Runtime.getRuntime().exec("cmd /c start C:\\Users\\einsteinjackson\\Desktop\\Data.xlsx\"");  
	          }catch(IOException  e){
	              e.printStackTrace();
	          }  
	 }	 
	 
	 
	 
	 
	 
}
