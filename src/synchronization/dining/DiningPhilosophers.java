/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */


public class DiningPhilosophers
{  
   public static void main(String args[])
   {
      DiningServerUtils server = new DiningServerUtils();

      Philosopher[] philosopherArray = new Philosopher[DiningServerUtils.NUM_OF_PHILS];
     
     // create the philosopher threads
     for (int i = 0; i < DiningServerUtils.NUM_OF_PHILS; i++){
         philosopherArray[i] = new Philosopher(server,i);
		 new Thread(philosopherArray[i]).start();
	 }
   }
}