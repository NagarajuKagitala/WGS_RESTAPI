package IBMMQ;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testrail.Settings;
import testrail.TestClass;
@Listeners(TestClass.class)
public class IBMAuthinfo 
{
  static WebDriver driver;
  static String URL;
  static String uname;
  static String  password;
  static String wgsname;
  static String nodename;
  static String qmgrname;

@BeforeTest
public void Beforetest() throws Exception
{
	Settings.read();
	URL=Settings.getURL();
	uname=Settings.getRESTAPI_Username();
	password=Settings.getRESTAPI_Password();
	wgsname=Settings.getWGSNAME();
	nodename=Settings.getNODENAME();
	qmgrname=Settings.getQUEUEMANAGERNAME();
}

	
	@Parameters({"sDriver","sDriverpath"})
	@Test(priority=0)
	public static void Login(String sDriver,String sDriverpath) throws InterruptedException
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
	
	@Parameters({"authinfoname"})
	@Test(priority=1)
	public static void DeleteAuthinfoifexist(String authinfoname) throws InterruptedException
	{
		//for deleting authinfo if exist
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-deleteAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-deleteAuthInfo .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"authinfoname","authtype","connname"})
	@Test(priority=2)
	public static void CreateAuthinfo(String authinfoname, String authtype, String connname) throws InterruptedException
	{
		//for creating authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-createAuthInfo .arrow")).click();
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
				+ "  \"name\": \""+authinfoname+"\",\r\n"
				+ "  \r\n"
				+ "  \"general\": \r\n"
				+ "  {\r\n"
				+ "   \r\n"
				+ "    \"type\": \""+authtype+"\"\r\n"
				+ "   \r\n"
				+ "  },\r\n"
				+ "  \"extended\": \r\n"
				+ " {\r\n"
				+ "    \r\n"
				+ "    \"connName\": \""+connname+"\"\r\n"
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
		if(curldata.contains(authinfoname) && responsecode==201)
		{
			System.out.println("authinfo is created");
		}
		else
		{
			System.out.println("authinfo is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-createAuthInfo .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=3)
	public static void ReadAuthinfoData(String authinfoname) throws InterruptedException
	{
		//for read authinfo data
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-readAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
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
		if(curldata.contains(authinfoname) && responsecode==200)
		{
			System.out.println("Read authinfo data method is success");
		}
		else
		{
			System.out.println("Read authinfo data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-readAuthInfo .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=4)
	public static void ReadMQObjectAuthorityRecords(String authinfoname) throws InterruptedException
	{
		//for read mq records authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-readAuthRecs .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(10000);
		
		//for scrolling down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(authinfoname) && responsecode==200)
		{
			System.out.println("Read MQ Object Authority Records method is success");
		}
		else
		{
			System.out.println("Read MQ Object Authority Records method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-readAuthRecs .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=5)
	public static void SearchAuthenticationInfo(String authinfoname) throws InterruptedException
	{
		//for search authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-searchAuthInfos .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
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
		if(curldata.contains(authinfoname) && responsecode==200)
		{
			System.out.println("search authinfo method is success");
		}
		else
		{
			System.out.println("search authinfo method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-searchAuthInfos .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=6)
	public static void SearchAuthInfoEvents(String authinfoname) throws InterruptedException
	{
		//for search auth info events
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-searchAuthInfoEvents .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
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
		if(curldata.contains(authinfoname) && responsecode==200)
		{
			System.out.println("search authinfo events method is success");
		}
		else
		{
			System.out.println("search authinfo events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-searchAuthInfoEvents .arrow")).click();
		Thread.sleep(5000);
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=7)
	public static void ForecUpdateAuthinfo(String authinfoname) throws InterruptedException
	{
		//for force update Authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-forceUpdateAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
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
		if(curldata.contains(authinfoname) && responsecode==204)
		{
			System.out.println("Force update authinfo method is success");
		}
		else
		{
			System.out.println("Force update authinfo method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-forceUpdateAuthInfo .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=8)
	public static void ChangeAuthinfoCustomProperties(String authinfoname) throws InterruptedException
	{
		//for change authinfo custom properties
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-changeAuthInfoCustomProperties .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
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
		if(curldata.contains(authinfoname) && responsecode==204)
		{
			System.out.println("Change authinfo custom properties method is success");
		}
		else
		{
			System.out.println("Change authinfo custom properties method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-changeAuthInfoCustomProperties .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"authinfoname","updatedauthinfoname"})
	@Test(priority=9)
	public static void ChangeAuthinfo(String authinfoname, String updatedauthinfoname) throws InterruptedException
	{
		//for changing authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-changeAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+updatedauthinfoname+"\"\r\n"
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
		if(curldata.contains(updatedauthinfoname) && responsecode==204)
		{
			System.out.println("Change authinfo method is success");
		}
		else
		{
			System.out.println("Change authinfo method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-changeAuthInfo .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"authinfoname","copiedauthinfoname","connname"})
	@Test(priority=10)
	public static void CopyAuthinfo(String authinfoname, String copiedauthinfoname, String connname) throws InterruptedException
	{
		//for copying authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-copyAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \r\n"
				+ "  \"wgsName\": \""+wgsname+"\",\r\n"
				+ "  \"nodeName\": \""+nodename+"\",\r\n"
				+ "  \"qmgrName\": \""+qmgrname+"\",\r\n"
				+ "  \"name\": \""+copiedauthinfoname+"\",\r\n"
				+ "\r\n"
				+ "  \"extended\": \r\n"
				+ " {\r\n"
				+ "    \r\n"
				+ "    \"connName\": \""+connname+"\"\r\n"
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
		if(curldata.contains(copiedauthinfoname) && responsecode==201)
		{
			System.out.println("Copy authinfo method is success");
		}
		else
		{
			System.out.println("Copy authinfo method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-copyAuthInfo .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"authinfoname","copiedauthinfoname"})
	@Test(priority=11)
	public static void DeleteCopiedAuthinfo(String authinfoname, String copiedauthinfoname) throws InterruptedException
	{
		//for deleting copied authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-deleteAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(copiedauthinfoname);
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
		if(curldata.contains(copiedauthinfoname) && responsecode==204)
		{
			System.out.println("Delete copied authinfo method is success");
		}
		else
		{
			System.out.println("Delete copied authinfo method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-deleteAuthInfo .arrow")).click();
		Thread.sleep(5000);                
	}
	
	@Parameters({"authinfoname"})
	@Test(priority=12)
	public static void DeleteAuthinfo(String authinfoname) throws InterruptedException
	{
		//for deleting authinfo
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-deleteAuthInfo .arrow")).click();
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
		driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(authinfoname);
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
		if(curldata.contains(authinfoname) && responsecode==204)
		{
			System.out.println("Delete authinfo method is success");
		}
		else
		{
			System.out.println("Delete authinfo method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-ibmMQ\\\\\\.authInfo-deleteAuthInfo .arrow")).click();
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
