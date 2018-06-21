/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package producer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;
import message.SimpleMessage;

/**
 * This class posts messages to the message board. 
 * @author José Carranza
 */
public class MessageBoardProducer {
    // Name of the queue
    private final String CHANNEL_NAME;
    
    public MessageBoardProducer(String queueName){
        CHANNEL_NAME = queueName;
    }
    
    
    public void sendMessage(SimpleMessage message) throws IOException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        // Objects for serializing SimpleMessage objects
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            // Declare the queue to which we will send a message.
            channel.exchangeDeclare(CHANNEL_NAME, "fanout");
            //channel.queueDeclare(CHANNEL_NAME, false,false,false, null);
            // Serialize the object and publish it to the queue.
            oos.writeObject(message);
            channel.basicPublish(CHANNEL_NAME, "", null, out.toByteArray());            
            channel.close();
            connection.close();
        } catch(TimeoutException te){
            System.out.println("Timeout while sending the message. ");
        }
    }
}
