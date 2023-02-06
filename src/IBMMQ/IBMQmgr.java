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
public class IBMQmgr 
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
    
    
    @Parameters({"wgsname","qmgrname","nodename"})
    @Test(priority=1)
    public static void CreateQmgr(String wgsname,String qmgrname, String nodename) throws InterruptedException
    {
    	//for creating qmngr
    	driver.findElement(By.cssSelector("#operations-qmgr-createQMgr .arrow")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div/div[2]/button")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//textarea")).clear();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
    			+ "  \"wgsName\": \""+wgsname+"\",\r\n"
    			+ "  \"nodeName\": \""+nodename+"\",\r\n"
    			+ "  \"name\": \""+qmgrname+"\"\r\n"
    			+ "}");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[3]/button")).click();
    	Thread.sleep(19000);
    	
    	//scrolling down for result
    	JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(9000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(qmgrname) && responsecode==201)
		{
			System.out.println("qmngr is created");
		}
		else
		{
			System.out.println("qmngr is not created");
			driver.findElement(By.xpath("fail the testcase"));
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-createQMgr .arrow")).click();
    	Thread.sleep(3000);
		
     }
    
    @Parameters({"nodename","qmgrname"})
    @Test(priority=2)
    public static void ReadQmgrData(String nodename, String qmgrname ) throws InterruptedException
    {
    
    	//for reading qmgr data
    	driver.findElement(By.cssSelector("#operations-qmgr-readQMgr .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("read qmgr data method is success");
		}
		else
		{
			System.out.println("read qmgr data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
    	
		driver.findElement(By.cssSelector("#operations-qmgr-readQMgr .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename","qmgrname"})
    @Test(priority=3)
    public static void ReadMQObjectAuthorityRecords(String nodename, String qmgrname ) throws InterruptedException
    {
        //for reading qmgr data
    	driver.findElement(By.cssSelector("#operations-qmgr-readAuthRecs_6 .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("read mq object  authority method is success");
		}
		else
		{
			System.out.println("read mq object  authority method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
    	
		driver.findElement(By.cssSelector("#operations-qmgr-readAuthRecs_6 .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename","qmgrname","connectionID"})
    @Test(priority=3)
    public static void ReadQmgrConnection(String nodename, String qmgrname, String connectionID ) throws InterruptedException
    {
        //for reading qmgr data
    	driver.findElement(By.cssSelector("#operations-qmgr-readQMgrConnection .arrow")).click();
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
    	driver.findElement(By.xpath("//tr[3]/td[2]/input")).sendKeys(connectionID);
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[3]/button")).click();
    	Thread.sleep(3000);
    	
    	//for scrolling down
    	JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("read mq object  authority method is success");
		}
		else
		{
			System.out.println("read mq object  authority method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
    	
		driver.findElement(By.cssSelector("#operations-qmgr-readQMgrConnection .arrow")).click();
    	Thread.sleep(3000);
    }
    
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=4)
    public static void SearchQmgr(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrs .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr data method is success");
		}
		else
		{
			System.out.println("search qmgr data method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrs .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=5)
    public static void SearchQmgrAccountings(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrAccountings .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr accountings method is success");
		}
		else
		{
			System.out.println("search qmgr accountings method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrAccountings .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=5)
    public static void SearchQmgrApplicationStatuses(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrApplStatus .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr application status method  is success");
		}
		else
		{
			System.out.println("search qmgr application status method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrApplStatus .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=6)
    public static void SearchQmgrAuthorityService(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrAuthServices .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr authority service method is success");
		}
		else
		{
			System.out.println("search qmgr authority service method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrAuthServices .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=7)
    public static void SearchQmgrConnections(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrConnections .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr connections method is success");
		}
		else
		{
			System.out.println("search qmgr conenctions method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrConnections .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=8)
    public static void SearchQmgrEvents(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrEvents .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr events method is success");
		}
		else
		{
			System.out.println("search qmgr events method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrEvents .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=9)
    public static void SearchQmgrStatistics(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrStatistics .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==200)
		{
			System.out.println("search qmgr statistics method is success");
		}
		else
		{
			System.out.println("search qmgr statistics method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-searchQMgrStatistics .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=10)
    public static void Forceupdateqmgr(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-forceUpdateQMgr .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==204)
		{
			System.out.println("Force update qmngrs method is success");
		}
		else
		{
			System.out.println("Force update method is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-forceUpdateQMgr .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"qmgrname","nodename"})
    @Test(priority=11)
    public static void DiscoverNowqmgr(String qmgrname, String nodename) throws InterruptedException
    {
    	//for creating qmngr
    	driver.findElement(By.cssSelector("#operations-qmgr-discoverNowQMgr .arrow")).click();
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
    			+ "  \"full\": true\r\n"
    			+ "}");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[3]/button")).click();
    	Thread.sleep(19000);
    	
    	//scrolling down for result
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
			System.out.println("qmngr is discovered");
		}
		else
		{
			System.out.println("qmngr is not discovered");
			driver.findElement(By.xpath("fail the testcase"));
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-discoverNowQMgr .arrow")).click();
    	Thread.sleep(3000);
		
     }
    
    @Parameters({"wgsname","qmgrname","nodename"})
    @Test(priority=12)
    public static void ChangeQmgr(String wgsname,String qmgrname, String nodename) throws InterruptedException
    {
    	//for creating qmngr
    	driver.findElement(By.cssSelector("#operations-qmgr-changeQMgr .arrow")).click();
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
    			+ "  \"wgsName\": \""+wgsname+"\",\r\n"
    			+ "  \"nodeName\": \""+nodename+"\",\r\n"
    			+ "  \"name\": \""+qmgrname+"\"\r\n"
    			+ "}");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[3]/button")).click();
    	Thread.sleep(19000);
    	
    	//scrolling down for result
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
			System.out.println("qmngr is changed");
		}
		else
		{
			System.out.println("qmngr is not changed");
			driver.findElement(By.xpath("fail the testcase"));
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-changeQMgr .arrow")).click();
    	Thread.sleep(3000);
		
     }
    
    @Parameters({"qmgrname","nodename"})
    @Test(priority=13)
    public static void ChangeQmgrCustomProperties(String qmgrname, String nodename) throws InterruptedException
    {
    	//for creating qmngr
    	driver.findElement(By.cssSelector("#operations-qmgr-changeQMgrCustomProperties .arrow")).click();
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
    	Thread.sleep(19000);
    	
    	//scrolling down for result
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
			System.out.println("qmngr is changed");
		}
		else
		{
			System.out.println("qmngr is not changed");
			driver.findElement(By.xpath("fail the testcase"));
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-changeQMgrCustomProperties .arrow")).click();
    	Thread.sleep(3000);
		
     }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=14)
    public static void PingQmgr(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-pingQMgr .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==204)
		{
			System.out.println("queue maanger is pinged");
		}
		else
		{
			System.out.println("queue manager is not pinged");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-pingQMgr .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=15)
    public static void ExecuteShellCommandOnQmgr(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-shellCmd .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==201)
		{
			System.out.println("command executed");
		}
		else
		{
			System.out.println("command not executed");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-shellCmd .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=16)
    public static void StartAllWMQObjects(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-startQMgr .arrow")).click();
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
    	driver.findElement(By.xpath("//textarea")).clear();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
    			+ "  \"startChannels\": true,\r\n"
    			+ "  \"startMutiIntance\": true\r\n"
    			+ "}");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[3]/button")).click();
    	Thread.sleep(19000);
    	
    	//for scrolling down
    	JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==204)
		{
			System.out.println("started all wmq queue manager objects");
		}
		else
		{
			System.out.println("not started all wmq queue manager objects");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-startQMgr .arrow")).click();
    	Thread.sleep(3000);
    }
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=17)
    public static void StopAllWMQObjects(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-stopQMgr .arrow")).click();
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
    	driver.findElement(By.xpath("//textarea")).clear();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
    			+ "  \"stopMethod\": \"Quiesced\",\r\n"
    			+ "  \"stopReconnect\": true,\r\n"
    			+ "  \"stopSwitch\": true,\r\n"
    			+ "  \"stopStandby\": true\r\n"
    			+ "}");
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//div[3]/button")).click();
    	Thread.sleep(19000);
    	
    	//for scrolling down
    	JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==204)
		{
			System.out.println("Qmgr objects are stopped");
		}
		else
		{
			System.out.println("Qmgr objects are not stopped");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-stopQMgr .arrow")).click();
    	Thread.sleep(3000);
    } 
    
    @Parameters({"nodename", "qmgrname"})
    @Test(priority=17)
    public static void DeleteQmgr(String nodename, String qmgrname) throws InterruptedException
    {
    	//for searching qmgr
    	driver.findElement(By.cssSelector("#operations-qmgr-deleteQMgr .arrow")).click();
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
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String responsecurl=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(responsecurl.contains(qmgrname) && responsecode==204)
		{
			System.out.println("queue maanger is Deleted");
		}
		else
		{
			System.out.println("queue manager is not Deleted");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-qmgr-deleteQMgr .arrow")).click();
    	Thread.sleep(3000);
    }
    
    
    @Test(priority=18)
	public static void Logout() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[2]")).click();
		Thread.sleep(3000);
		
		driver.close();
	}
    
    
    
}
