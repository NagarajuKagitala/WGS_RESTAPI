package testrail;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {
	private static Properties propertiesSetting = null;

	private static String settingTestrailAPI;

	private static String settingUsername;
	private static String settingPassword;

	private static String settingProjectId;

	private static String settingURL;

	private static String settingENV;
	

	private static String TestRunID;

	
	

	public static  void read() throws Exception {
		if (propertiesSetting == null) {
			propertiesSetting = new Properties();
			propertiesSetting.load(new FileInputStream("File.properties"));

			settingTestrailAPI = propertiesSetting.getProperty("TESTRAILAPI");
			settingUsername = propertiesSetting.getProperty("USERNAME");
			settingPassword = propertiesSetting.getProperty("PASSWORD");
			settingProjectId = propertiesSetting.getProperty("PROJECTID");
			settingURL = propertiesSetting.getProperty("URL");
			settingENV = propertiesSetting.getProperty("ENV");
			TestRunID =propertiesSetting.getProperty("TestRunID");
			
			
			
			
			
		}
	}

	public static String getTestRunID() {
		return TestRunID;
	}


	public static void setTestRunID(String testRunID) {
		TestRunID = testRunID;
	}
	
	public static String getUserName() {
		return UserName;
	}


	public static void setUserName(String userName) {
		UserName = userName;
	}

	private static String UserName;


	public static Properties getPropertiesSetting() {
		return propertiesSetting;
	}

	public static void setPropertiesSetting(Properties propertiesSetting) {
		Settings.propertiesSetting = propertiesSetting;
	}
	
	public static String getSettingTestrailAPI() {
		return settingTestrailAPI;
	}

	public static void setSettingTestrailAPI(String settingTestrailAPI) {
		Settings.settingTestrailAPI = settingTestrailAPI;
	}

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

}
