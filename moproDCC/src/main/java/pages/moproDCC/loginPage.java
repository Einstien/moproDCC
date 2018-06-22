package pages.moproDCC;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import moproDCC.moproDCC.baseFile;

public class loginPage extends baseFile {
	
	@FindBy (xpath= "//input[@name='txtUsername']")
	WebElement userName;
	

	@FindBy (xpath= "//input[@name='txtPassword']")
	WebElement password;
	

	@FindBy (xpath= "//input[@type='submit' and @name='Button1']")
	WebElement submitBtn;
	
	public loginPage() {
		PageFactory.initElements(driver, this);
	}	
	
	public projectPage login()  {		
		userName.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));		
		submitBtn.click();		
		return new projectPage();
	}	

}
