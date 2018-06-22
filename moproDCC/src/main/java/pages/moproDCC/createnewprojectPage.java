package pages.moproDCC;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import moproDCC.moproDCC.baseFile;

public class createnewprojectPage extends baseFile {	

	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_txtProjectname']")
	WebElement projectTilte;
	
	@FindBy(xpath="//select[@id='ctl00_ContentPlaceHolder1_ddlServerType']")
	WebElement clusterDropdown;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_chkECWIDEcommerce']")
	WebElement ecommCheckbox;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_chkBigBrands']")
	WebElement brandsCheckbox;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_divSubmit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_btnEditPassCancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_btnBundleChange']")
	WebElement changebundleBtn;
	
	@FindBy(xpath="//select[@id='ctl00_ContentPlaceHolder1_ddlBundleType']")
	WebElement bundleDropdown;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder1_chkBuilderV2']")
	WebElement upgradeCheckbox;
	
	
	public createnewprojectPage() {
		PageFactory.initElements(driver, this);
	}	
	public projectPage createNewProject(String title, String cluster, String bundle, String version, String ecomm, String brand ) {		
		
		projectTilte.sendKeys(title);		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('ctl00_ContentPlaceHolder1_ddlServerType').style.display='block';");		
		Select clusDropdown = new Select(clusterDropdown);		
		if (cluster.equalsIgnoreCase("two")) {
			clusDropdown.selectByIndex(1);
		} else {
			clusDropdown.selectByIndex(0);
		} 
		if (bundle.length()!=0) {
	    changebundleBtn.click();
	    js.executeScript("document.getElementById('ctl00_ContentPlaceHolder1_ddlBundleType').style.display='block';");
		Select bunDropdown = new Select(bundleDropdown); 
		bunDropdown.selectByVisibleText(bundle);
		}			
		if (version.equalsIgnoreCase("v1")) {			
			upgradeCheckbox.click();			
		} else {
			if (ecomm.equalsIgnoreCase("yes")) {
				ecommCheckbox.click();
			}
			if (brand.equalsIgnoreCase("yes")) {
				brandsCheckbox.click();
			}	
		}
		submitBtn.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		js.executeScript("return document.readyState").toString().equals("complete");		
		return new projectPage();
	}
}
