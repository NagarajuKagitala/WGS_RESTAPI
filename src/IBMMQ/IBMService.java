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
public class IBMService 
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
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=1)
	public static void DeleteServiceifexist(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for deleting service if exist
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-deleteService .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-deleteService .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"wgsname","nodename","qmgrname","servicename"})
	@Test(priority=2)
	public static void CreateService(String wgsname, String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for creating Service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-createService .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+servicename+"\"\r\n"
				+ "  \r\n"
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
		if(curldata.contains(servicename) && responsecode==201)
		{
			System.out.println("Service is created");
		}
		else
		{
			System.out.println("Service is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-createService .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=3)
	public static void ReadServiceData(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for read service data
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-readService .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
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
		if(curldata.contains(servicename) && responsecode==200)
		{
			System.out.println("Read service data method is success");
		}
		else
		{
			System.out.println("Read service data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-readService .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=4)
	public static void SearchServiceEvents(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for search service events
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-searchServiceEvents .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
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
		if(curldata.contains(servicename) && responsecode==200)
		{
			System.out.println("search service events method is success");
		}
		else
		{
			System.out.println("search service events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-searchServiceEvents .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=5)
	public static void ReadMQObjectAuthorityRecords(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for read mq records service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-readAuthRecs_8 .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
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
		if(curldata.contains(servicename) && responsecode==200)
		{
			System.out.println("Read MQ Object Authority Records method is success");
		}
		else
		{
			System.out.println("Read MQ Object Authority Records method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-readAuthRecs_8 .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=6)
	public static void ForecUpdateService(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for force update service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-forceUpdateService_1 .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
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
		if(curldata.contains(servicename) && responsecode==204)
		{
			System.out.println("Force update service method is success");
		}
		else
		{
			System.out.println("Force update service method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-forceUpdateService_1 .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=7)
	public static void ChangeServiceCustomProperties(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for change service properties
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-changeServiceCustomProperties .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
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
		if(curldata.contains(servicename) && responsecode==204)
		{
			System.out.println("Change service custom properties method is success");
		}
		else
		{
			System.out.println("Change service custom properties method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-changeServiceCustomProperties .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"wgsname","nodename","qmgrname","servicename","updatedservicename"})
	@Test(priority=8)
	public static void ChangeService(String wgsname, String nodename, String qmgrname, String servicename, String updatedservicename) throws InterruptedException
	{
		//for changing service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-changeService .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ " \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+updatedservicename+"\"\r\n"
				+ " \r\n"
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
		if(curldata.contains(updatedservicename) && responsecode==204)
		{
			System.out.println("Change service method is success");
		}
		else
		{
			System.out.println("Change service method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-changeService .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"wgsname","nodename","qmgrname","servicename","copiedservicename"})
	@Test(priority=9)
	public static void CopyService(String wgsname, String nodename, String qmgrname, String servicename, String copiedservicename) throws InterruptedException
	{
		//for copying service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-copyService .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+copiedservicename+"\"\r\n"
				+ "  \r\n"
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
		if(curldata.contains(copiedservicename) && responsecode==201)
		{
			System.out.println("Copy service method is success");
		}
		else
		{
			System.out.println("Copy service method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-copyService .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"nodename","qmgrname","servicename","copiedservicename"})
	@Test(priority=10)
	public static void DeleteCopiedService(String nodename, String qmgrname, String servicename, String copiedservicename) throws InterruptedException
	{
		//for deleting copied service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-deleteService .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(copiedservicename);
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
		if(curldata.contains(copiedservicename) && responsecode==204)
		{
			System.out.println("Delete copied service method is success");
		}
		else
		{
			System.out.println("Delete copied service method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-deleteService .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"nodename","qmgrname","servicename"})
	@Test(priority=11)
	public static void DeleteService(String nodename, String qmgrname, String servicename) throws InterruptedException
	{
		//for deleting service
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-deleteService .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(servicename);
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
		if(curldata.contains(servicename) && responsecode==204)
		{
			System.out.println("Delete service method is success");
		}
		else
		{
			System.out.println("Delete service method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.service-deleteService .arrow")).click();
		Thread.sleep(5000);                
	}
	
	 @Test(priority=12)
	 public static void Logout() throws InterruptedException
	 {
			driver.findElement(By.xpath("//div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[2]")).click();
			Thread.sleep(3000);
			
			driver.close();
			
	 }
}
