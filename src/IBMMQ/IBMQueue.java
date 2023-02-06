package IBMMQ;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testrail.TestClass;

@Listeners(TestClass.class)
public class IBMQueue 
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
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=1)
	public static void DeleteifQueueexist(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-deleteQueue .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-deleteQueue .arrow")).click();
		Thread.sleep(3000);
		
	}
	
	@Parameters({"wgsname","nodename","qmgrname","queuename","queuetype"})
	@Test(priority=2)
	public static void CreateQueue(String wgsname,String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-createQueue .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{ \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+queuename+"\",\r\n"
				+ "  \"type\": \""+queuetype+"\"\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==201)
		{
			System.out.println("Queue is created");
		}
		else
		{
			System.out.println("Queue is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-createQueue .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=3)
	public static void ReadQueueData(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-readQueue .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==200)
		{
			System.out.println("Read queue data method is success");
		}
		else
		{
			System.out.println("Read queue data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-readQueue .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=3)
	public static void ReadMQObjectAuthorityRecords(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-readAuthRecs_7 .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==200)
		{
			System.out.println("Read MQ Object Authority records method is success");
		}
		else
		{
			System.out.println("Read MQ Object Authority records method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-readAuthRecs_7 .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=4)
	public static void SearchQueues(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueues .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==200)
		{
			System.out.println("Search queues method is success");
		}
		else
		{
			System.out.println("Search queues method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueues .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=5)
	public static void SearchQueueAccountings(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueueAccountings .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==200)
		{
			System.out.println("Search queue Accountings method is success");
		}
		else
		{
			System.out.println("Search queue Accountings method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueueAccountings .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=6)
	public static void SearchQueueEvents(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueueEvents .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==200)
		{
			System.out.println("Search queue events method is success");
		}
		else
		{
			System.out.println("Search queue events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueueEvents .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=7)
	public static void SearchQueueStatistics(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueueStatistics .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==200)
		{
			System.out.println("Search queue statistics method is success");
		}
		else
		{
			System.out.println("Search queue statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-searchQueueStatistics .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=8)
	public static void ForceUpdateQueue(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-forceUpdateQueue .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==204)
		{
			System.out.println("Force update queue method is success");
		}
		else
		{
			System.out.println("Force update queue method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-forceUpdateQueue .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"wgsname","nodename","qmgrname","queuename","updatedqueuename","queuetype"})
	@Test(priority=9)
	public static void ChangeQueue(String wgsname, String nodename, String qmgrname,String queuename, String updatedqueuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-changeQueue .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{ \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+updatedqueuename+"\",\r\n"
				+ "  \"type\": \""+queuetype+"\"\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(updatedqueuename) && responsecode==204)
		{
			System.out.println("Queue is changed");
		}
		else
		{
			System.out.println("Queue is not changed");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-changeQueue .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=10)
	public static void ChangeQueueCustomProperties(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-changeQueueCustomProperties .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==204)
		{
			System.out.println("change queue custom properties method is success");
		}
		else
		{
			System.out.println("change queue custom properties method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-changeQueueCustomProperties .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"wgsname","nodename","qmgrname","queuename","copiedqueuename","queuetype"})
	@Test(priority=11)
	public static void CopyQueue(String wgsname, String nodename, String qmgrname,String queuename, String copiedqueuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-copyQueue .arrow")).click();
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
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{ \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+copiedqueuename+"\",\r\n"
				+ "  \"type\": \""+queuetype+"\"\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(copiedqueuename) && responsecode==201)
		{
			System.out.println("Queue is copied");
		}
		else
		{
			System.out.println("Queue is not copied");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-copyQueue .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","qmgrname","copiedqueuename","queuetype"})
	@Test(priority=12)
	public static void DeleteCopiedQueue(String nodename, String qmgrname, String copiedqueuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-deleteQueue .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(copiedqueuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(copiedqueuename) && responsecode==204)
		{
			System.out.println("Copied queue is deleted");
		}
		else
		{
			System.out.println("Copied queue is not deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-deleteQueue .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","queuename","queuetype"})
	@Test(priority=13)
	public static void DeleteQueue(String nodename, String qmgrname, String queuename, String queuetype) throws InterruptedException
	{
		//for creating queue
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-deleteQueue .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		Select element=new Select(driver.findElement(By.xpath("//td[2]/select")));
		Thread.sleep(3000);
		element.selectByVisibleText(queuetype);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(queuename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(6000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(queuename) && responsecode==204)
		{
			System.out.println("Queue is deleted");
		}
		else
		{
			System.out.println("Queue is not deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.queue-deleteQueue .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Test(priority=14)
	 public static void Logout() throws InterruptedException
	 {
			driver.findElement(By.xpath("//div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[2]")).click();
			Thread.sleep(3000);
			
			driver.close();
			
	 }	
}
