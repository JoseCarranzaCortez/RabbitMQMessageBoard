package consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeoutException;
import message.SimpleMessage;

/**
 * Listens for incoming messages and displays them. 
 * @author José Carranza
 */
public class MessageBoardConsumer {
    
    // Name of the queue.
    private final String CHANNEL_NAME;
    
    public MessageBoardConsumer(String channelName){
        this.CHANNEL_NAME = channelName;
    }
    
    public void listen() throws IOException, InterruptedException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        try{
            // Establish a new connection
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(CHANNEL_NAME, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, CHANNEL_NAME, "");
            
            System.out.println("Waiting for messages. To exit, press CTRL+C.");
            
            // Create a consumer and define how the message will be handled. 
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    ByteArrayInputStream in = new ByteArrayInputStream(body);
                    ObjectInputStream ois = new ObjectInputStream(in);
                    // De-serialize the message object
                    try{
                        SimpleMessage message = (SimpleMessage) ois.readObject();
                        // Print object
                        System.out.println(message.getSender()+" ["+message.getTimestamp()+"]: "+message.getContent()+"");
                    } catch (ClassNotFoundException e){
                        System.out.println("Unrecognized message object. The message might habe been corrupted.");
                    }
                }
            };
            
            // Start listening for messages
            channel.basicConsume(queueName, true, consumer);
            
            
        }catch(TimeoutException te){
            System.out.println("Timeout");
        }
        
        
    }
}
