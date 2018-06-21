/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplemessages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;
import message.SimpleMessage;
import producer.MessageBoardProducer;

/**
 * Example use of message sending through RabbitMQ.
 * @author José Carranza
 */
public class ProducerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);        
        // Get the user's name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        try{
            // We will be subscribing to the message_bord queue. 
            MessageBoardProducer producer = new MessageBoardProducer("message_board");
          
            String messageString; // Will be read from terminal
            do{
                // Request message
                System.out.print("Enter a message: ");
                messageString = scanner.nextLine();
                
                if(!messageString.equals("exit")){
                    // Create message and send it. 
                    SimpleMessage messageObject =  new SimpleMessage(name, messageString, new Date());
                    producer.sendMessage(messageObject);
                    
                }
            }while(!messageString.equals("exit"));
            
            System.out.println("Goodbye.");
                        
        }catch(IOException ioe){
            System.out.println("There was an error running the program.");
        }
    }
    
}
