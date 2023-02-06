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
public class IBMNode
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
			driver.findElement(By.xpath("fail the testcase"));
		}
		else
		{
			System.out.println("login is successfull");
		}
		
		driver.findElement(By.xpath("//button[2]")).click();
		Thread.sleep(4000);
		
	}
	
	@Parameters({"nodename"})
	@Test(priority=1)
	public static void  DeleteNodeifexist(String nodename) throws InterruptedException
	{
		//for deleting node
		driver.findElement(By.cssSelector("#operations-node-deleteNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("#operations-node-deleteNode .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","IP","port"})
	@Test(priority=1)
	public static void CreateNode(String nodename, String IP, String port ) throws InterruptedException
	{
		//for creating node
		driver.findElement(By.cssSelector("#operations-node-createNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(.,'Try it out')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
			    + "  \r\n"
				+ "  \"name\": \""+nodename+"\",\r\n"
				+ "  \r\n"
				+ "  \"identity\":\r\n"
				+ " {\r\n"
				+ "    \r\n"
				+ "    \"netAddress\": \""+IP+"\",\r\n"
				+ "    \"portNumber\": "+port+"\r\n"
				+ "\r\n"
				+ "  }\r\n"
				+ "\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(.,'Execute')]")).click();
		Thread.sleep(4000);
		
		//scroll down to result
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the result
		String responsedata=driver.findElement(By.cssSelector(".live-responses-table .response > .response-col_status")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==201)
		{
			System.out.println("node is created");
		}
		else
		{
			System.out.println("node is not created");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-createNode .arrow")).click();
		Thread.sleep(3000);
	}
		
	@Parameters({"nodename"})	
	@Test(priority=2)	
	public static void ReadNodeData(String nodename) throws InterruptedException
	{
		//for getting read node data
		driver.findElement(By.cssSelector("#operations-node-readNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement Element1 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js1.executeScript("arguments[0].scrollIntoView();", Element1);
		Thread.sleep(5000);
		
		//for comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==200)
		{
			System.out.println("readnodedata is success");
		}
		else
		{
			System.out.println("readnodedata is failed");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-readNode .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename"})
	@Test(priority=3)
	public static void SearchNode(String nodename) throws InterruptedException
	{
		//for searching node data
		driver.findElement(By.cssSelector("#operations-node-searchNodes .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==200)
		{
			System.out.println("searchnode is success");
		}
		else
		{
			System.out.println("searchnode is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-searchNodes .arrow")).click();
		Thread.sleep(3000);
	}
	@Parameters({"nodename"})
	@Test(priority=4)
	public static void DiscoverNowNode(String nodename) throws InterruptedException
	{
		// for discover full the node
		driver.findElement(By.cssSelector("#operations-node-discoverNowNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("Discovernow is success");
		}
		else
		{
			System.out.println("Discovernow is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-discoverNowNode .arrow")).click();
		Thread.sleep(3000);
		
	}
	
	@Parameters({"nodename"})
	@Test(priority=5)
	public static void StartAllWMQObjects(String nodename) throws InterruptedException
	{
		// for discover full the node
		driver.findElement(By.cssSelector("#operations-node-startNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("if node is started then it is success");
		}
		else
		{
			System.out.println("if node is not started then it is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-startNode .arrow")).click();
		Thread.sleep(3000);
		
	}
	
	@Parameters({"nodename"})
	@Test(priority=6)
	public static void StopAllWMQObjects(String nodename) throws InterruptedException
	{
		// for discover full the node
		driver.findElement(By.cssSelector("#operations-node-stopNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("if node is stopped then it is success");
		}
		else
		{
			System.out.println("if node is not stopped then it is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-stopNode .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename"})
	@Test(priority=7)
	public static void SearchNodeEvents(String nodename) throws InterruptedException
	{
		//for searching node data
		driver.findElement(By.cssSelector("#operations-node-searchNodeEvents .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==200)
		{
			System.out.println("search node events are success");
		}
		else
		{
			System.out.println("searchnode events are unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		driver.findElement(By.cssSelector("#operations-node-searchNodeEvents .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename","changednodename","IP","port"})
	@Test(priority=8)
	public static void ChangeNode(String nodename, String changednodename, String IP, String port) throws InterruptedException
	{
		//for change node
		driver.findElement(By.cssSelector("#operations-node-changeNode_1 .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(.,'Try it out')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \r\n"
				+ "  \"name\": \""+changednodename+"\",\r\n"
				+ "  \r\n"
				+ "  \"identity\":\r\n"
				+ " {\r\n"
				+ "   \r\n"
				+ "    \"netAddress\": \""+IP+"\",\r\n"
				+ "    \"portNumber\": "+port+"\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(changednodename) && responsecode==204)
		{
			System.out.println("node name is changed");
		}
		else
		{
			System.out.println("node name is not changed");
			driver.findElement(By.xpath("fail the test case"));
		}
		
		driver.findElement(By.cssSelector("#operations-node-changeNode_1 .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename"})
	@Test(priority=9)
	public static void ChangeNodeCustomProperties(String nodename) throws InterruptedException
	{
		//for changing node custom properties
		driver.findElement(By.cssSelector("#operations-node-changeNodeCustomProperties .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("node custom properties are changed");
		}
		else
		{
			System.out.println("node custom properties are not changed");
			driver.findElement(By.xpath("fail the test case"));
		}
		
		driver.findElement(By.cssSelector("#operations-node-changeNodeCustomProperties .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename"})
	@Test(priority=10)
	public static void ManageNode(String nodename) throws InterruptedException
	{
		// for discover full the node
		driver.findElement(By.cssSelector("#operations-node-manageNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		//for managing the node
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"manage\": \"Enabled\",\r\n"
				+ "  \"discoveryPeriod\": 0\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("managing of node is success");
		}
		else
		{
			System.out.println("managing of  node  is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-manageNode .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename"})
	@Test(priority=11)
	public static void UnmanageNode(String nodename) throws InterruptedException
	{
		// for discover full the node
		driver.findElement(By.cssSelector("#operations-node-manageNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		//for unmanaging the node
		driver.findElement(By.xpath("//textarea")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//textarea")).sendKeys("{\r\n"
				+ "  \"manage\": \"Disabled\",\r\n"
				+ "  \"discoveryPeriod\": 0\r\n"
				+ "}");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		
		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("unmanaging of node is success");
		}
		else
		{
			System.out.println("unmnaging of  node  is unsuccess");
			driver.findElement(By.xpath("fail the testcase")).click();
		}
		
		driver.findElement(By.cssSelector("#operations-node-manageNode .arrow")).click();
		Thread.sleep(3000);
	}
	
	@Parameters({"nodename"})
	@Test(priority=11)
	public static void  DeleteNode(String nodename) throws InterruptedException
	{
		//for deleting node
		driver.findElement(By.cssSelector("#operations-node-deleteNode .arrow")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[2]/input")).sendKeys(nodename);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[3]/button")).click();
		Thread.sleep(3000);
		
		
		//scroll down to result
		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Element10 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td"));
		js10.executeScript("arguments[0].scrollIntoView();", Element10);
		Thread.sleep(5000);
		

		//comparing the results
		String responsedata=driver.findElement(By.xpath("//div[4]/div[2]/div/div/table/tbody/tr/td")).getText();
		int responsecode=Integer.parseInt(responsedata);
		
		String curldata=driver.findElement(By.xpath("//div[2]/pre")).getText();
		if(curldata.contains(nodename) && responsecode==204)
		{
			System.out.println("node is deleted");
		}
		else
		{
			System.out.println("node is not deleted");
			driver.findElement(By.xpath("fail the test case"));
		}
		
		driver.findElement(By.cssSelector("#operations-node-deleteNode .arrow")).click();
		Thread.sleep(3000);
		
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
		
		
		
		
		
		
		
		
