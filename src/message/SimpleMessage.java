/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author José Carranza
 */
public class SimpleMessage implements Serializable {
    private String sender;
    private String content;
    private Date timestamp;
    
    public SimpleMessage(String sender, String content, Date timestamp){
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    
    public String toString(){
        return "Message from " + this.sender + ".";
    }
    
    
}
