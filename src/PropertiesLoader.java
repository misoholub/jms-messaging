import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {	
	private InputStream inputStream;	
	private String host;
	private String queueName;
	private String login;
	private String password;
	private String inputFileName;
	
	private static PropertiesLoader instance = null;
	
	public static PropertiesLoader getPropertiesLoader() throws IOException {
		if (instance == null) {
			instance = new PropertiesLoader();
		}
		return instance;
	}
	
	private PropertiesLoader() throws IOException {		
		try {			
			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file " + propFileName + " not found in the classpath.");
			}
			
			host = prop.getProperty("host");
			queueName = prop.getProperty("queueName");
			login = prop.getProperty("login");
			password = prop.getProperty("password");
			inputFileName = prop.getProperty("inputFileName");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
	
	public String getHost() {
		return host; 
	}
	
	public String getQueueName() {
		return queueName;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getInputFileName() {
		return inputFileName;
	}
	
}
