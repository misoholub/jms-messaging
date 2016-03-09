import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsQueueMessenger extends JmsMessenger {
	
	private ConnectionFactory connFact;
	private Connection conn;
	private Session sess;
	private Destination dest;
	private MessageProducer prod;
	
	public JmsQueueMessenger(String host, String queue, String login, String password) {
		try {
			connFact = new ActiveMQConnectionFactory(host);
			conn = connFact.createConnection(login, password);
			sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);			
			dest = sess.createQueue(queue);
			prod = sess.createProducer(dest);		
			prod.setDeliveryMode(DeliveryMode.PERSISTENT);			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	@Override
	public void sendMessage(String message) {		
		try {
			Message msg = sess.createTextMessage(message);			
			System.out.println("Sending message...");
			prod.send(msg);		
			conn.close();
			System.out.println("Message sent successfully.");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}	
	
}
