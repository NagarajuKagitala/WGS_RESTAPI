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
public class IBMChannel 
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
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=1)
	public static void DeleteChannelifexist(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for deleting channel if exist
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-deleteChannel .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-deleteChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"wgsname","nodename","qmgrname","channelname","channeltype","connectionname","transqueuename"})
	@Test(priority=2)
	public static void CreateChannel(String wgsname, String nodename, String qmgrname, String channelname, String channeltype, String connectionname, String transqueuename) throws InterruptedException
	{
		//for creating channel
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-createChannel .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+channelname+"\",\r\n"
				+ "  \"type\": \""+channeltype+"\",\r\n"
				+ "  \r\n"
				+ "  \"general\": \r\n"
				+ " {\r\n"
				+ "   \r\n"
				+ "    \"connectionName\": \""+connectionname+"\",\r\n"
				+ "    \"xmitQName\": \""+transqueuename+"\"\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}");
		Thread.sleep(10000);
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
		if(curldata.contains(channelname) && responsecode==201)
		{
			System.out.println("channel is created");
		}
		else
		{
			System.out.println("channel is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-createChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=3)
	public static void ReadChannelData(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for read channel data
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-readChannel .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==200)
		{
			System.out.println("Read channel data method is success");
		}
		else
		{
			System.out.println("Read channel data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-readChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=4)
	public static void SearchChannels(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for search channels
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-searchChannels .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==200)
		{
			System.out.println("Search channels method is success");
		}
		else
		{
			System.out.println("Search channels method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-searchChannels .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=5)
	public static void ReadMQObjectAuthorityRecords(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for ReadMQObjectAuthorityRecords of channels
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-readAuthRecs_1 .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==200)
		{
			System.out.println("ReadMQObjectAuthorityRecords method is success");
		}
		else
		{
			System.out.println("ReadMQObjectAuthorityRecords method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-readAuthRecs_1 .arrow")).click();
		Thread.sleep(10000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=6)
	public static void SearchChannelEvents(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for search channel events
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-searchChannelEvents .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==200)
		{
			System.out.println("Search channel events method is success");
		}
		else
		{
			System.out.println("Search channel events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-searchChannelEvents .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=7)
	public static void SearchChannelStatistics(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for search channel statistics
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-searchChannelStatistics .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==200)
		{
			System.out.println("Search channel statistics method is success");
		}
		else
		{
			System.out.println("Search channel statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-searchChannelStatistics .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=8)
	public static void ForceUpdateChannel(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for force update channel
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-forceUpdateChannel .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==204)
		{
			System.out.println("force update channel method is success");
		}
		else
		{
			System.out.println("force update channel method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-forceUpdateChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"wgsname","nodename","qmgrname","channelname","channeltype","connectionname","transqueuename","updatedchannelname"})
	@Test(priority=9)
	public static void ChangeChannel(String wgsname, String nodename, String qmgrname, String channelname, String channeltype, String connectionname, String transqueuename, String updatedchannelname) throws InterruptedException
	{
		//for changing channel
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-changeChannel .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+updatedchannelname+"\",\r\n"
				+ "  \"type\": \""+channeltype+"\",\r\n"
				+ "  \r\n"
				+ "  \"general\": \r\n"
				+ " {\r\n"
				+ "   \r\n"
				+ "    \"connectionName\": \""+connectionname+"\",\r\n"
				+ "    \"xmitQName\": \""+transqueuename+"\"\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}");
		Thread.sleep(20000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(20000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(updatedchannelname) && responsecode==204)
		{
			System.out.println("channel is changed");
		}
		else
		{
			System.out.println("channel is not changed");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-changeChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=10)
	public static void ChangeChannelCustomProperties(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for change channel custom properties
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-changeChannelCustomProperties .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"description\": \"hello\"\r\n"
				+ "}");
		Thread.sleep(10000);
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
		if(curldata.contains(channelname) && responsecode==204)
		{
			System.out.println("change channel custom properties method is success");
		}
		else
		{
			System.out.println("change channel custom properties method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-changeChannelCustomProperties .arrow")).click();
		Thread.sleep(10000);
	}
	
	
	@Parameters({"wgsname","nodename","qmgrname","channelname","channeltype","connectionname","transqueuename","copiedchannelname"})
	@Test(priority=11)
	public static void CopyChannel(String wgsname, String nodename, String qmgrname, String channelname, String channeltype, String connectionname, String transqueuename, String copiedchannelname) throws InterruptedException
	{
		//for copying channel
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-copyChannel .arrow")).click();
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
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+copiedchannelname+"\",\r\n"
				+ "  \"type\": \""+channeltype+"\",\r\n"
				+ "  \r\n"
				+ "  \"general\": \r\n"
				+ " {\r\n"
				+ "   \r\n"
				+ "    \"connectionName\": \""+connectionname+"\",\r\n"
				+ "    \"xmitQName\": \""+transqueuename+"\"\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}");
		Thread.sleep(20000);
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
		if(curldata.contains(copiedchannelname) && responsecode==201)
		{
			System.out.println("channel is copied");
		}
		else
		{
			System.out.println("channel is not copied");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-copyChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype","copiedchannelname"})
	@Test(priority=12)
	public static void DeleteCopiedChannel(String nodename, String qmgrname, String channelname, String channeltype, String copiedchannelname) throws InterruptedException
	{
		//for deleting copied channel 
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-deleteChannel .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(copiedchannelname);
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
		if(curldata.contains(copiedchannelname) && responsecode==204)
		{
			System.out.println("copied channel is deleted");
		}
		else
		{
			System.out.println("copied channel is not deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-deleteChannel .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"nodename","qmgrname","channelname","channeltype"})
	@Test(priority=13)
	public static void DeleteChannel(String nodename, String qmgrname, String channelname, String channeltype) throws InterruptedException
	{
		//for deleting channel 
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-deleteChannel .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys(qmgrname);
		Thread.sleep(3000);
		//for selecting channel type
		Select element = new Select(driver.findElement(By.xpath("//td[2]/select")));
		element.selectByVisibleText(channeltype);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[4]/td[2]/input")).sendKeys(channelname);
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
		if(curldata.contains(channelname) && responsecode==204)
		{
			System.out.println("channel is deleted");
		}
		else
		{
			System.out.println("channel is not deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.channel-deleteChannel .arrow")).click();
		Thread.sleep(5000);
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
