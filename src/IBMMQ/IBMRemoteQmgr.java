package IBMMQ;

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
public class IBMRemoteQmgr
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
	
	@Parameters({"nodename","qmgrname",})
	@Test(priority=1)
	public static void DeleteRemoteQueueManagerifexist(String nodename, String qmgrname) throws InterruptedException
	{
		//for deleting remote qmgr
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-deleteRemoteQMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-deleteRemoteQMgr .arrow")).click();
		Thread.sleep(5000); 
	}
	
	@Parameters({"wgsname","nodename","qmgrname","connname"})
	@Test(priority=2)
	public static void CreateRemoteQmgr(String wgsname, String nodename, String qmgrname, String connname) throws InterruptedException
	{
		//for creating remoteqmgr
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-createRemoteQMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ " \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \r\n"
				+ "    \"communication\": \r\n"
				+ " {\r\n"
				+ "    \"connectionName\": \""+connname+"\"\r\n"
				+ "   \r\n"
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
		if(curldata.contains(qmgrname) && responsecode==201)
		{
			System.out.println("remote qmgr is created");
		}
		else
		{
			System.out.println("remote qmgr is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-createRemoteQMgr .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname"})
	@Test(priority=3)
	public static void ReadRemoteQueueManagerData(String nodename, String qmgrname) throws InterruptedException
	{
		//for read rqmgr data
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-readRemoteQMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
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
		if(curldata.contains(qmgrname) && responsecode==200)
		{
			System.out.println("Read remote queue manager data method is success");
		}
		else
		{
			System.out.println("Read remote queue manager data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-readRemoteQMgr .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname"})
	@Test(priority=4)
	public static void SearchRemoteQueueManagers(String nodename, String qmgrname) throws InterruptedException
	{
		//for search rqmgrs 
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-searchRemoteQMgrs .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
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
		if(curldata.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search rqmgrs method is success");
		}
		else
		{
			System.out.println("search rqmgrs method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-searchRemoteQMgrs .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"wgsname","nodename","qmgrname","updatedrqmgrname","connname"})
	@Test(priority=5)
	public static void ChangeRemoteQmngr(String wgsname,String nodename, String qmgrname, String updatedrqmgrname, String connname) throws InterruptedException
	{
		//for changing rqmgr
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-changeRemoteQMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ " \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+updatedrqmgrname+"\",\r\n"
				+ " \r\n"
				+ "  \"communication\":\r\n"
				+ " {\r\n"
				+ "    \"connectionName\": \""+connname+"\"\r\n"
				+ "   } \r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(5000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(updatedrqmgrname) && responsecode==204)
		{
			System.out.println("Change rqmgr method is success");
		}
		else
		{
			System.out.println("Change rqmgr method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-changeRemoteQMgr .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"nodename","qmgrname"})
	@Test(priority=6)
	public static void DeleteRemoteQueueManager(String nodename, String qmgrname) throws InterruptedException
	{
		//for deleting remote qmgr
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-deleteRemoteQMgr .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
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
		if(curldata.contains(qmgrname) && responsecode==204)
		{
			System.out.println("Delete remote qmgr method is success");
		}
		else
		{
			System.out.println("Delete remote qmgr method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.remoteQmgr-deleteRemoteQMgr .arrow")).click();
		Thread.sleep(5000);                
	}
	
	 @Test(priority=7)
	 public static void Logout() throws InterruptedException
	 {
			driver.findElement(By.xpath("//div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[2]")).click();
			Thread.sleep(3000);
			
			driver.close();
			
	 }
}
