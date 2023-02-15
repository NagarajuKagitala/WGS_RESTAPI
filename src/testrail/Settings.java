package testrail;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {
	
	private static String settingTestrailAPI;
	private static String settingUsername;
	private static String settingPassword;
	private static String settingProjectId;
	private static String TestRunID;

	public static String getTestRunID() {
		return TestRunID;
	}


	public static void setTestRunID(String testRunID) {
		TestRunID = testRunID;
	}


	private static String settingURL;

	private static String settingENV;
	
	private static String URL;
	private static String RESTAPI_Username;
	private static String RESTAPI_Password;
	private static String  WGSNAME;
	private static String NODENAME;
	private static String QUEUEMANAGERNAME;
	
	
	
	public static String getSettingUsername() {
		return settingUsername;
	}


	public static void setSettingUsername(String settingUsername) {
		Settings.settingUsername = settingUsername;
	}


	public static String getSettingPassword() {
		return settingPassword;
	}


	public static void setSettingPassword(String settingPassword) {
		Settings.settingPassword = settingPassword;
	}
	
	public static String getSettingProjectId() {
		return settingProjectId;
	}


	public static void setSettingProjectId(String settingProjectId) {
		Settings.settingProjectId = settingProjectId;
	}


	public static String getSettingURL() {
		return settingURL;
	}


	public static void setSettingURL(String settingURL) {
		Settings.settingURL = settingURL;
	}


	public static String getSettingENV() {
		return settingENV;
	}


	public static void setSettingENV(String settingENV) {
		Settings.settingENV = settingENV;
	}


	public static String getSettingTestrailAPI() {
		return settingTestrailAPI;
	}


	public static void setSettingTestrailAPI(String settingTestrailAPI) {
		Settings.settingTestrailAPI = settingTestrailAPI;
	}


	public static Properties getPropertiesSetting() {
		return propertiesSetting;
	}


	public static void setPropertiesSetting(Properties propertiesSetting) {
		Settings.propertiesSetting = propertiesSetting;
	}
	
	public static String getQUEUEMANAGERNAME() {
		return QUEUEMANAGERNAME;
	}


	public static void setQUEUEMANAGERNAME(String qUEUEMANAGERNAME) {
		QUEUEMANAGERNAME = qUEUEMANAGERNAME;
	}


	public static String getNODENAME() {
		return NODENAME;
	}


	public static void setNODENAME(String nODENAME) {
		NODENAME = nODENAME;
	}


	public static String getWGSNAME() {
		return WGSNAME;
	}


	public static void setWGSNAME(String wGSNAME) {
		WGSNAME = wGSNAME;
	}


	public static String getRESTAPI_Password() {
		return RESTAPI_Password;
	}


	public static void setRESTAPI_Password(String rESTAPI_Password) {
		RESTAPI_Password = rESTAPI_Password;
	}


	public static String getRESTAPI_Username() {
		return RESTAPI_Username;
	}


	public static void setRESTAPI_Username(String rESTAPI_Username) {
		RESTAPI_Username = rESTAPI_Username;
	}


	public static String getURL() {
		return URL;
	}


	public static void setURL(String uRL) {
		URL = uRL;
	}


	private static Properties propertiesSetting = null;
	
	
	public static  void read() throws Exception {
		if (propertiesSetting == null) {
			propertiesSetting = new Properties();
			propertiesSetting.load(new FileInputStream("File.properties"));

			settingTestrailAPI = propertiesSetting.getProperty("TESTRAILAPI");
			settingUsername = propertiesSetting.getProperty("USERNAME");
			settingPassword = propertiesSetting.getProperty("PASSWORD");
			
			
			URL=propertiesSetting.getProperty("URL");
			RESTAPI_Username=propertiesSetting.getProperty("RESTAPI_Username");
			RESTAPI_Password=propertiesSetting.getProperty("RESTAPI_Password");
			WGSNAME=propertiesSetting.getProperty("WGSNAME");
			NODENAME=propertiesSetting.getProperty("NODENAME");
			QUEUEMANAGERNAME=propertiesSetting.getProperty("QUEUEMANAGERNAME");
			
			settingProjectId = propertiesSetting.getProperty("PROJECTID");
			settingURL = propertiesSetting.getProperty("URL");
			settingENV = propertiesSetting.getProperty("ENV");
			TestRunID = propertiesSetting.getProperty("TestRunID");
			
		}
	}
	
	
	



	
	
	




	



	



		
	

}
