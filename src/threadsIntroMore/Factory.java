/**
 * Factory class that creates the MessageQueue class and
 * the producer and consumer threads. 
 *
 * Figure 4.13
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */
package threadsIntroMore;

import java.util.Date;

public class Factory
{
	public static void main(String[] args) {
		// create the message queue
		Channel<Date> queue = new MessageQueue<Date>();

		// create the producer and consumer threads
		Producer a = new Producer(queue);
		Thread producer = new Thread(a);
		Thread consumer = new Thread(new Consumer(queue));

		System.out.println("Producer: " + producer.getState());
		System.out.println("Consumer: " + consumer.getState());
		// start the threads
		producer.start();
		System.out.println("Producer: " + producer.getState());
		consumer.start();
		System.out.println("Consumer: " + consumer.getState());
	
	}
}
