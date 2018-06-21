# Simple Message Board Using RabbitMQ

This is a very simple project, which goal is only to explore the basic functionality of RabbitMQ. 

The project explore the following characteristics:

* Message sending using and exchange of tipe Fanout. 
* Subscription to a queue to display the sent messages, along with the sender and time. 
* Sending of serialized objects, to explore alternatives to JSON format. 

# Programming Languages
The project has been developed using Java. 

# Design
There 5 classes on the program:

* **SimpleMessage:** represents a message object. Contains a sender (name), a body and a timestamp. 
* **MessageBoardConsumer:** instances of this class subscribe to a particular queue, and display any received message.
* **MessageBoardProducer:**  instances of this class send message to a exchange of type **fanout**. Fanout type was chosen to deliver messages to all consumers. 

* **ProducerTest:** contains a main method. When excecuted, prompts the user for their name and then allows them to send messages to the board. 
* **ConsumerTest:** contains a main method. When excecuted, the program will start listening to messages and displaying them on the terminal. 

**Before running the program, make sure RabbitMQ is running on localhost.**

To run the consumer test program, open a terminal on the proyect diretory and run the following command (LINUX Version): 

```sh
$ java -cp "build/classes/:lib/*" simplemessages.ProducerTest
```


To run the producer test program, open a terminal on the proyect diretory and run the following command (LINUX Version): 

```sh
$ java -cp "build/classes/:lib/*" simplemessages.ConsumerTest
```


**Any number of Producers and Consumers can be running at the same time.**


# Conclusion
This is a basic example that demostrates how RabbitMQ can be used to send and receive messages. This basic example just posts messages to a board, but the same principles can be applied to more complex things like microservices to create robust applications with low coupling. 



