/* This example will allow us to illustrate a simple lock using a semaphore.
 * The goal is not to interrupt the output of the thread .
 */

import java.util.concurrent.*;
 
class PrintingThread extends Thread {
	String name;
	Semaphore aLock;

  public PrintingThread(Semaphore l, String name){
	this.name = name;
	aLock = l;
  }

  public void run(){
    while(true){
		try{
			aLock.acquire(); //try to get the lock; if not, do bu/sy wait
			//critical section
			for(int i = 0; i < 10; i++)
				System.out.println(name + " " + i);
			aLock.release();
		} catch(Exception e){
			System.out.println("Exception!!!");
			return;
		}
    }
  }
}

public class SemaphoreExample{
	public static void main(String[] args){
		Semaphore lock = new Semaphore(1);
		PrintingThread bob = new PrintingThread(lock, "Bob");
		bob.start();
		PrintingThread ann = new PrintingThread(lock, "Ann");
		ann.start();
	}
}
