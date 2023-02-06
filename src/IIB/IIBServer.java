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
public class IIBServer 
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
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=1)
	public static void DeleteIIBServerIfExist(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for Delete IIB Server statistics
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-deleteIIBServer .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-deleteIIBServer .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"wgsname","nodename","mgrname","servername"})
	@Test(priority=2)
	public static void CreateIIBServer(String wgsname, String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for creating server
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-createIIBServer .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"managerName\": \""+mgrname+"\",\r\n"
				+ "  \"serverName\": \""+servername+"\"\r\n"
				+ "}");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(7000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(servername) && responsecode==201)
		{
			System.out.println("Server is created");
		}
		else
		{
			System.out.println("Server is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-createIIBServer .arrow")).click();
		Thread.sleep(5000);
	}
		
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=3)
	public static void ReadIIBServerData(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for read IIB server data
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-readIIBServer .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==200)
		{
			System.out.println("Read IIB server data method is success");
		}
		else
		{
			System.out.println("Read IIB server data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-readIIBServer .arrow")).click();
		Thread.sleep(5000);
	}	
		
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=4)
	public static void SearchIIBServers(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for search IIB servers
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-searchIIBServers .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==200)
		{
			System.out.println("search IIB servers method is success");
		}
		else
		{
			System.out.println("search IIB servers method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-searchIIBServers .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=5)
	public static void SearchIIBServerEvents(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for search IIB server events
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-searchIIBServerEvents .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==200)
		{
			System.out.println("search IIB server events method is success");
		}
		else
		{
			System.out.println("search IIB server events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-searchIIBServerEvents .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=6)
	public static void ForceUpdateIIBServer(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for force update IIB Server
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-forceUpdateIIBServer .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("force update IIB Server method is success");
		}
		else
		{
			System.out.println("force update IIB Server method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-forceUpdateIIBServer .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=7)
	public static void ChangeIIBServerCustomProperties(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for change IIB server custom properties 
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-changeIIBServerCustomProperties .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"description\": \"hello\"\r\n"
				+ "}");
		Thread.sleep(5000);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("change IIB server custom properties method is success");
		}
		else
		{
			System.out.println("change IIB server custom properties method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-changeIIBServerCustomProperties .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=8)
	public static void StartIIBServer(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for start IIB Server
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServer .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("start IIB Server method is success");
		}
		else
		{
			System.out.println("start IIB Server method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServer .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=9)
	public static void StartIIBServerAllApplicationTypes(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for start IIB Server app types
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerAllApps .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("start IIB Server all app types method is success");
		}
		else
		{
			System.out.println("start IIB Server all app types method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerAllApps .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=10)
	public static void StartIIBServerAllMessageFlows(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for start IIB Server all msg flows
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerAllMessageFlows .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("start IIB Server  all msg flows method is success");
		}
		else
		{
			System.out.println("start IIB Server all msg flows method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerAllMessageFlows .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=11)
	public static void StartIIBServerResourceStatistic(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for start IIB Server resource statistics
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerResourceStatistic .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("start IIB Server resource statistics method is success");
		}
		else
		{
			System.out.println("start IIB Server resource statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerResourceStatistic .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=12)
	public static void StartIIBServerStatistic(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for start IIB Server statistics
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerStatistic .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("start IIB Server statistics method is success");
		}
		else
		{
			System.out.println("start IIB Server statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-startIIBServerStatistic .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=13)
	public static void StopIIBServices(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for stop IIB Server
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServer .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("stop IIB Server method is success");
		}
		else
		{
			System.out.println("stop IIB Server method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServer .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=14)
	public static void StopIIBServerAllApplicationTypes(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for stop IIB Server app types
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerAllApps .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("stop IIB Server all app types method is success");
		}
		else
		{
			System.out.println("stop IIB Server all app types method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerAllApps .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=15)
	public static void StopIIBServerAllMessageFlows(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for stop IIB Server all msg flows
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerAllMessageFlows .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("stop IIB Server  all msg flows method is success");
		}
		else
		{
			System.out.println("stop IIB Server all msg flows method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerAllMessageFlows .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=16)
	public static void StopIIBServerResourceStatistic(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for Stop IIB Server resource statistics
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerResourceStatistic .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("Stop IIB Server resource statistics method is success");
		}
		else
		{
			System.out.println("Stop IIB Server resource statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerResourceStatistic .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=17)
	public static void StopIIBServerStatistic(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for stop IIB Server statistics
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerStatistic .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("stop IIB Server statistics method is success");
		}
		else
		{
			System.out.println("stop IIB Server statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-stopIIBServerStatistic .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername"})
	@Test(priority=18)
	public static void DeleteIIBServer(String nodename, String mgrname, String servername) throws InterruptedException
	{
		//for Delete IIB Server statistics
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-deleteIIBServer .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(mgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servername);
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
		if(curldata.contains(servername) && responsecode==204)
		{
			System.out.println("IIB Server is deleted");
		}
		else
		{
			System.out.println("IIB Server is not deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.server-deleteIIBServer .arrow")).click();
		Thread.sleep(5000);
	}
	
	 @Test(priority=19)
	 public static void Logout() throws InterruptedException
	 {
			driver.findElement(By.xpath("//div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[2]")).click();
			Thread.sleep(3000);
			
			driver.close();
			
	 }	

}
