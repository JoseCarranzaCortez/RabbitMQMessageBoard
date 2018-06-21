/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplemessages;

import consumer.MessageBoardConsumer;
import java.io.IOError;
import java.io.IOException;

/**
 * Example of the use of a MessageBoardConsumer.
 * @author José Carranza
 */
public class ConsumerTest {
    public static void main(String[] args) {
        try{
            // Create consumer to be subscribed to queue 'message_board'.
            MessageBoardConsumer consumer = new MessageBoardConsumer("message_board");
            // Start listening
            consumer.listen();
        } catch(InterruptedException e) {
            System.out.println("There was a problem.");
        } catch(IOException e){
            System.out.println("There was a problem establishing the connetion. Please make sure RabbitMQ server is running. ");
        }
    }
}
