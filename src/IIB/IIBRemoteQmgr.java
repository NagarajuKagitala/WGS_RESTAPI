package IIB;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testrail.TestClass;
@Listeners(TestClass.class)
public class IIBRemoteQmgr
{
static WebDriver driver;
	
	@Parameters({"sDriver","sDriverpath","URL","uname","password"})
	@Test(priority=0)
	public static void Login(String sDriver,String sDriverpath, String URL, String uname, String password) throws InterruptedException
	{
		if(sDriver.equalsIgnoreCase("webdriver.chrome.driver"))
		{
		    System.setProperty(sDriver,sDriverpath);
		    driver=new ChromeDriver();
		}
		else if(sDriver.equalsIgnoreCase("webdriver.ie.driver"))
		{
			System.setProperty(sDriver,sDriverpath);
			driver=new InternetExplorerDriver();
		}
		else if(sDriver.equalsIgnoreCase("webdriver.edge.driver"))
		{
			System.setProperty(sDriver,sDriverpath);
			driver=new EdgeDriver();
		}
		else if(sDriver.equalsIgnoreCase("webdriver.firefox.driver"))
		{
			System.setProperty(sDriver,sDriverpath);
			driver=new FirefoxDriver();
		}
		
		//for getting URL
		driver.get(URL);
		Thread.sleep(9000);
		
		//for maximize window
		driver.manage().window().maximize();
		
		//for authorizations
		driver.findElement(By.xpath("//div[2]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.name("username")).sendKeys(uname);
		Thread.sleep(3000);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//form/div[2]/button")).click();
		Thread.sleep(6000);
		
		
		String btnvalue1 = driver.findElement(By.xpath("//div[2]/button")).getText();
		String btnvalue2 = driver.findElement(By.xpath("//button[contains(.,'Logout')]")).getText();
		
		if(btnvalue1.equals(btnvalue2))
		{
			System.out.println("login is unsuccessful");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		else
		{
			System.out.println("login is successfull");
		}
		
		driver.findElement(By.xpath("//button[2]")).click();
		Thread.sleep(4000);
	}	
	
	@Parameters({"nodename","mgrname"})
	@Test(priority=1)
	public static void DeleteIIBRemoteQmgrsifexist(String nodename, String mgrname) throws InterruptedException
	{
		//for delete IIB remote qmgr 
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-deleteACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(mgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000); 
		
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-deleteACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"wgsname","nodename","mgrname","type","url"})
	@Test(priority=2)
	public static void CreateIIBRemoteQmgr(String wgsname, String nodename, String mgrname, String type, String url) throws InterruptedException
	{
		//for creating remote qmgr of IIB
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-createACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"managerName\": \""+mgrname+"\",\r\n"
				+ "  \r\n"
				+ "  \"general\": \r\n"
				+ "  {\r\n"
				+ "    \"type\": \""+type+"\",\r\n"
				+ "    \"url\": \""+url+"\"\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==201)
		{
			System.out.println("IIBRemote Qmgr is created");
		}
		else
		{
			System.out.println("IIBRemote Qmgr is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-createACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname"})
	@Test(priority=3)
	public static void ReadIIBRemoteQmgrData(String nodename, String mgrname) throws InterruptedException
	{
		//for read IIB remote qmgr data
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-readACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(mgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==200)
		{
			System.out.println("Read IIB remote qmgr data method is success");
		}
		else
		{
			System.out.println("Read IIB remote qmgr data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-readACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname"})
	@Test(priority=4)
	public static void SearchIIBRemoteQmgrs(String nodename, String mgrname) throws InterruptedException
	{
		//for search IIB remote qmgr         
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-searchACEIIBRemoteMgrs .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(mgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==200)
		{
			System.out.println("Search IIB remote qmgrs method is success");
		}
		else
		{
			System.out.println("search IIB remote qmgrs method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-searchACEIIBRemoteMgrs .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"wgsname","nodename","mgrname","type","url","updatednodename"})
	@Test(priority=5)
	public static void ChangeIIBRemoteQmgr(String wgsname, String nodename, String mgrname, String type, String url, String updatednodename) throws InterruptedException
	{
		//for changing remote qmgr of IIB
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-changeACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(mgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+updatednodename+"\",\r\n"
				+ "  \"managerName\": \""+mgrname+"\",\r\n"
				+ "  \r\n"
				+ "  \"general\": \r\n"
				+ "  {\r\n"
				+ "    \"type\": \""+type+"\",\r\n"
				+ "    \"url\": \""+url+"\"\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(updatednodename) && responsecode==204)
		{
			System.out.println("IIBRemote Qmgr is changed");
		}
		else
		{
			System.out.println("IIBRemote Qmgr is not changed");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-changeACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname"})
	@Test(priority=6)
	public static void DeleteIIBRemoteQmgrs(String nodename, String mgrname) throws InterruptedException
	{
		//for delete IIB remote qmgr 
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-deleteACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(mgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("IIB remote qmgr is deleted");
		}
		else
		{
			System.out.println("IIB remote qmgr is not deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-aceiib\\\\\\.remoteMgr-deleteACEIIBRemoteMgr .arrow")).click();
		Thread.sleep(5000);
	}
	
	
	 @Test(priority=13)
	 public static void Logout() throws InterruptedException
	 {
			driver.findElement(By.xpath("//div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[2]")).click();
			Thread.sleep(3000);
			
			driver.close();
			
	 }

}
