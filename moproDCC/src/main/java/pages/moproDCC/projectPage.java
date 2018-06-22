package pages.moproDCC;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import moproDCC.moproDCC.baseFile;

public class projectPage extends baseFile {

	@FindBy(xpath="//input[@id='txtSearch' and @value='Search' ]")
	WebElement search;
	
	@FindBy(xpath="//input[@id='divGobtn' and @class='blackBtn']")
	WebElement go;
	
	@FindBy(xpath="//input[@id='divCreateProject']")
	WebElement createNewProjectBtn;	
	
	
	@FindBy(xpath="//a[@id='ucProjectstatus_anDashboard' ]")
	WebElement dashLink;
	
	public projectPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String geturl() {
		String link = dashLink.getAttribute("href");
		return link;
	}
	
	public void gotoCreateProject() {
		createNewProjectBtn.click();
	}
	
	public void searchProject(String searchData) {
		search.sendKeys(searchData);
		go.click();
		String url = dashLink.getAttribute("href");
		System.out.println(url);
	} 
	
}
