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
public class IIBMessageFlow 
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
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=1)
	public static void ReadIIBMessageFlowData(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for read IIB message flow data
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-readIIBMessageFlow .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==200)
		{
			System.out.println("Read IIB message flow data method is success");
		}
		else
		{
			System.out.println("Read IIB message flow data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-readIIBMessageFlow .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=2)
	public static void ReadIIBMessageFlowActivityLogs(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for read IIB message flow activity logs
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-readIIBMessageFlowActivityLogs .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==200)
		{
			System.out.println("Read IIB message flow activity logs method is success");
		}
		else
		{
			System.out.println("Read IIB message flow activity logs method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-readIIBMessageFlowActivityLogs .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=3)
	public static void SearchIIBMessageFlows(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for search IIB message flows 
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-searchIIBMessageFlows .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(applicationname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[8]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[8]/td[2]/input")).sendKeys(messageflowname);
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
		if(curldata.contains(messageflowname) && responsecode==200)
		{
			System.out.println("Read search IIB message flows method is success");
		}
		else
		{
			System.out.println("Read search IIB message flows method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-searchIIBMessageFlows .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=4)
	public static void SearchIIBMessageFlowEvents(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for search IIB message flow events
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-searchIIBMessageFlowEvents .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==200)
		{
			System.out.println("search IIB message flow events method is success");
		}
		else
		{
			System.out.println("search IIB message flow events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-searchIIBMessageFlowEvents .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=5)
	public static void ForceUpdateIIBMessageFlow(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for force update IIB message flow 
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-forceUpdateIIBMessageFlow .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("force update IIB message flow method is success");
		}
		else
		{
			System.out.println("force update IIB message flow method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-forceUpdateIIBMessageFlow .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=6)
	public static void ChangeIIBMessageFlowCustomProperties(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for change IIB message flow custom properties
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-changeIIBMessageFlowCustomProperties .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("change IIB message flow custom properties method is success");
		}
		else
		{
			System.out.println("change IIB message flow custom properties method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-changeIIBMessageFlowCustomProperties .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=7)
	public static void StartIIBMessageFlow(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for start IIB message flow 
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-startIIBMessageFlow .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("start IIB message flow method is success");
		}
		else
		{
			System.out.println("start IIB message flow method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-startIIBMessageFlow .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=8)
	public static void StartIIBMessageFlowFlowMonitoring(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for start IIB message flow flow monitoring
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-startIIBMessageFlowFlowMonitoring .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("start IIB message flow flow monitoring method is success");
		}
		else
		{
			System.out.println("start IIB message flow flow monitoring method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-startIIBMessageFlowFlowMonitoring .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=9)
	public static void StartIIBMessageFlowStatistic(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for start IIB message flow statistic
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-startIIBMessageFlowStatistic .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("start IIB message flow statistic method is success");
		}
		else
		{
			System.out.println("start IIB message flow statistic method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-startIIBMessageFlowStatistic .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=10)
	public static void StopIIBMessageFlow(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for stop IIB message flow 
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlow .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("stop IIB message flow method is success");
		}
		else
		{
			System.out.println("stop IIB message flow method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlow .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=11)
	public static void StopIIBMessageFlowFlowMonitoring(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for stop IIB message flow flow monitoring
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlowFlowMonitoring .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("stop IIB message flow flow monitoring method is success");
		}
		else
		{
			System.out.println("stop IIB message flow flow monitoring method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlowFlowMonitoring .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=12)
	public static void StopIIBMessageFlowRecording(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for stop IIB message flow recording
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlowRecording .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("stop IIB message flow recording method is success");
		}
		else
		{
			System.out.println("stop IIB message flow recording method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlowRecording .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=13)
	public static void StopIIBMessageFlowStatistic(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for stop IIB message flow statistic
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlowStatistic .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("stop IIB message flow statistic method is success");
		}
		else
		{
			System.out.println("stop IIB message flow statistic method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-stopIIBMessageFlowStatistic .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","mgrname","servername","messageflowname","applicationname"})
	@Test(priority=14)
	public static void DeleteIIBMessageFlow(String nodename, String mgrname, String servername, String messageflowname, String applicationname) throws InterruptedException
	{
		//for delete IIB message flow 
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-deleteIIBMessageFlow .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(messageflowname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).sendKeys(applicationname);
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
		if(curldata.contains(messageflowname) && responsecode==204)
		{
			System.out.println("delete IIB message flow method is success");
		}
		else
		{
			System.out.println("delete IIB message flow method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-iib\\\\\\.messageFlow-deleteIIBMessageFlow .arrow")).click();
		Thread.sleep(5000);
	}
	
	 @Test(priority=15)
	 public static void Logout() throws InterruptedException
	 {
			driver.findElement(By.xpath("//div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[2]")).click();
			Thread.sleep(3000);
			
			driver.close();
			
	 }
		

}
