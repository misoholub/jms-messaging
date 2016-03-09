import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class JmsMessenger {

	public JmsMessenger() {
		super();
	}
	
	public abstract void sendMessage(String message);
	
	public void sendMessageFromFile(String path) throws IOException {
		String textMessage = readFile(path, StandardCharsets.UTF_8);
		sendMessage(textMessage);
	}

	/**
	 * Sends all files in specified directory. The directory should contain only XML files to be sent as messages.
	 * Does NOT work recursively for sub directories. 
	 * @param path
	 * @throws IOException
	 */
	public void sendMessageFromDir(String path) throws IOException {
		Files.walk(Paths.get(path)).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				try {
					sendMessageFromFile(filePath.toString());
				} catch (Exception e) {					
					System.out.println("Exception: " + e);
				}
			}
		});
	}
	
	protected String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}