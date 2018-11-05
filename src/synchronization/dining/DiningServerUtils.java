/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerUtils
{  
	// different philosopher states
	enum State {THINKING, HUNGRY, EATING};
	
	// number of philosophers
	public static final int NUM_OF_PHILS = 5;
	
	// array to record each philosopher's state
	private State[] state;
	
	// synchroization variables
	Lock lock;
	Condition[] self;
	
	public DiningServerUtils()
	{
		// array of philosopher's state
		state = new State[NUM_OF_PHILS];
		
		// originally all philosopher's are thinking
		for (int i = 0; i < NUM_OF_PHILS; i++)
			state[i] = State.THINKING;
		
		lock = new ReentrantLock();
		
		self = new Condition[NUM_OF_PHILS];
		
		for (int i = 0; i < NUM_OF_PHILS; i++)
			self[i] = lock.newCondition();
	}
	
	// called by a philosopher when they wish to eat 
	public void takeForks(int pnum)  {
		lock.lock();
		try {
      		state[pnum] = State.HUNGRY;
      		test(pnum);  
			if (state[pnum] != State.EATING)
				self[pnum].await();
		}
		catch (InterruptedException ie) { }
		finally {
			lock.unlock();
		}
	}
	
	// called by a philosopher when they are finished eating 
	public void returnForks(int pnum) {
		lock.lock();
		try {
      		state[pnum] = State.THINKING;
			
			// now test my left and right neighbors to see if they want to eat
      		test(leftNeighbor(pnum));
      		test(rightNeighbor(pnum));
		}
		finally {
			lock.unlock();
		}
	}
	
	private void test(int i)
	{
		// in other words ... if I'm hungry and my left and
		// right nieghbors aren't eating, then let me eat!
		if(state[i] == State.HUNGRY && 
		   state[leftNeighbor(i)] != State.EATING &&
		   state[rightNeighbor(i)] != State.EATING) {
			state[i] = State.EATING;
			self[i].signal();
		}
	}
	
	// return the index of the left neighbor of philosopher i
	private int leftNeighbor(int i)
	{
		if (i == 0)
			return NUM_OF_PHILS - 1;
		else
			return i - 1;
    }
	
	// return the index of the right neighbor of philosopher i
	private int rightNeighbor(int i)
	{
		if (i == NUM_OF_PHILS - 1)
			return 0;
		else
			return i + 1;
	}
}
