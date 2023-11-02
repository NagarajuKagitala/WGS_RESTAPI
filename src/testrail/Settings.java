package testrail;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {
	
	private static Properties propertiesSetting = null;
	private static String settingTestrailAPI;
	
		
	public static String getSettingTestrailAPI() {
		return settingTestrailAPI;
	}

	public static void setSettingTestrailAPI(String settingTestrailAPI) {
		Settings.settingTestrailAPI = settingTestrailAPI;
	}


	public static  void read() throws Exception {
		if (propertiesSetting == null) {
			propertiesSetting = new Properties();
			propertiesSetting.load(new FileInputStream("File.properties"));

			settingTestrailAPI = propertiesSetting.getProperty("TESTRAILAPI");
			
			
		}
	}
	
	
	



	
	
	




	



	



		
	

}
