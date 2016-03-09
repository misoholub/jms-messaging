public final class QueueMessagingTester {
			
	public static void main(final String[] args) throws Exception {
		final PropertiesLoader prop = PropertiesLoader.getPropertiesLoader();
		final String host = prop.getHost();
		final String queue = prop.getQueueName();
		final String login = prop.getLogin();
		final String pass = prop.getPassword();
		final String file = prop.getInputFileName();
		final JmsMessenger msg = new JmsQueueMessenger(host, queue, login, pass);
		msg.sendMessageFromFile(file);		
	}
	
}