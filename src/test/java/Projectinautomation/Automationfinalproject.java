package Projectinautomation;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Automationfinalproject {
	WebDriver driver;
	String baseurl="https://www.synthite.com/synthite/our-business/spices";
	
	
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}

	@BeforeMethod
	public void urlload()

	{
		driver.get("https://www.synthite.com/synthite/our-business/spices");
		driver.manage().window().maximize();
	}
	@Test
	public void test1() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div[1]/div[2]/ul/li[1]/a")).click();
File f=new File("C:\\excel\\excelautomateproject.xlsx");//C:\\excel\\newswag.xlsx
		

		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("Sheet1");
		System.out.println(sh.getLastRowNum());
		
		
		
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			String name=sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println("name="+name);
			String email=sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println(email);
			
			

			driver.findElement(By.xpath("//*[@id=\"contact_fname\"]")).clear();
			
		
			driver.findElement(By.xpath("//*[@id=\"contact_fname\"]")).sendKeys(name);
			
			
			driver.findElement(By.xpath("//*[@id=\"contact_email\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"contact_email\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"contactFormm\"]/p[3]/input[2]")).click();
		
		
		}
		
		//value passing in drop down
		driver.findElement(By.xpath("//*[@id=\"query\"]")).sendKeys("spices");
		driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/form/input[2]")).click();
		driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/div[1]/div[2]/ul/li[5]/a")).click();
		
		
		
		
		WebElement productchoose=driver.findElement(By.xpath("//*[@id=\"selectarea\"]"));
		Select product=new Select(productchoose);
		product.selectByValue("Oleoresins");
		
		
		
		List< WebElement> li=product.getOptions();
		System.out.println(li.size());
		
		
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		
		//scroll down
		JavascriptExecutor js=(JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)","");
		
		
		
		String parentwindow=driver.getWindowHandle();
		System.out.println("parentwindowtitle"+driver.getTitle());
		driver.findElement(By.xpath("/html/body/div[11]/div/div[6]/div[2]/a[1]")).click();
		
		Set<String>allwindowhandles=driver.getWindowHandles();
		for(String handle:allwindowhandles)
		{
			System.out.println(handle);
			if(!handle.equalsIgnoreCase(parentwindow))
			{
				
				driver.switchTo().window(handle);
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
				//driver.findElement(By.xpath("//*[@id=\"login_popup_cta_form\"]/div/div[3]/div/div/label/div")).click();
				//driver.findElement(By.xpath("//*[@id=\"login_popup_cta_form\"]/div/div[3]/div/div/label/div")).sendKeys("asdf");
				//driver.findElement(By.xpath("//*[@id=\"login_popup_cta_form\"]/div/div[4]/div/div/label/div")).click();
				//driver.findElement(By.xpath("//*[@id=\"login_popup_cta_form\"]/div/div[4]/div/div/label/div")).sendKeys("abcd123");
				driver.findElement(By.xpath("//*[@id=\"login_popup_cta_form\"]/div/div[5]/div/div")).click();
				driver.close();
				
			}
			
			driver.switchTo().window(parentwindow);
			
		}
		//mouse over
		
		WebElement home=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a"));
		Actions act =new Actions(driver);
		act.moveToElement(home);
		act.perform();
		
		WebDriverWait wait=new WebDriverWait (driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/div/div/div[2]/div/a")));
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/div/div/div[2]/div/a")).click();
		
		
		//link validation
		
		URL ob=new URL(baseurl);
		//URLConnection ob=new URLConnection(baseurl);
		HttpURLConnection con=(HttpURLConnection)ob.openConnection();
		
	
		con.connect();
		
		if(con.getResponseCode()==200)
		{
			System.out.println("valid url");
			
		}
		else
		{
			System.out.println("invalid url");
		}
		//single element screenshot
		
		WebElement spiceelement=driver.findElement(By.xpath("/html/body/div[5]/div/div/p"));
		File src= spiceelement.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("./screenshot//Elementscreenshot1.png"));
		
		
		//locating multiple web elements
		
		List<WebElement>li1=driver.findElements(By.tagName("a"));
		System.out.println(li1.size());
		for(WebElement s:li1)
		{
			String link=s.getAttribute("href");
			String text=s.getText();
			System.out.println(link+"----"+text);
		}
		//whole page screenshot
File srcnsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		FileHandler.copy(srcnsht, new File("C:\\excel\\screenshot.png") );
		
		
		
		//inspect with name and id
		driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[3]/div[1]/div[2]/ul/li[1]/a")).click();
		
		driver.findElement(By.name("contact_fname")).click();
		driver.findElement(By.name("contact_fname")).sendKeys("asdfhji");
		driver.findElement(By.id("contact_cell")).click();
		driver.findElement(By.id("contact_cell")).sendKeys("2345678901");
	//inspect with absolute xpath
		
		
		driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[1]/form/p[1]/input[4]")).click();
		driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[1]/form/p[1]/input[4]")).sendKeys("spices board");
		
		//logo check and check box selection check
		WebElement logocheck=driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/a/img"));
		boolean b=logocheck.isDisplayed();
		if(b)
		{
			System.out.println("displayed");
		}
		else
			
			{
			System.out.println("not displayed");
			}
		
		driver.navigate().back();
		driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[3]/div[1]/div[2]/ul/li[2]/a")).click();
		

		WebElement checkbox=driver.findElement(By.xpath("//*[@id=\"ebookform\"]/input[4]"));
		boolean c=checkbox.isSelected();
		if(c)
		{
			System.out.println("check box is selected");
		}
		else
		{
			System.out.println("check box is not selectd");
		}
		
		
		//multiple link verification
		
		List<WebElement>li3=driver.findElements(By.tagName("a"));
		for(WebElement s:li3)
		{
			String  link =s.getAttribute("href");
			verify(link);
			
		}
		
	}
	public void verify(String link)
	{
		try
		{
			URL ob=new URL(link);
			HttpURLConnection con=(HttpURLConnection)ob.openConnection();
			con.connect();
			if(con.getResponseCode()==200)
			{
				System.out.println("valid");
			}
			else if(con.getResponseCode()==400)
			{
				System.out.println("broken link-----"+link);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		
	}
}

	
		
		
		
	
	
	
	
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	



