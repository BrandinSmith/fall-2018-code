// This class keeps track of how many zombies are inside vs outside
//package zombies;

public class ZombieStats{
	private int zombiesIn;
	private int zombiesOut;
	
	public ZombieStats(int in, int out){
		zombiesIn = in;
		zombiesOut = out;
	}
	
	// Add the synchronized keyword below
	// While the # of zombies is 2 or there are no zombies outside - wait
	public synchronized void letZombieIn() throws InterruptedException{
		while(zombiesIn >= 2 || zombiesOut <= 0)
			wait();
		zombiesIn++;
		zombiesOut--;
		// let other threads know that a condition has changed
		
		
		System.out.println("Zombie let in. Zombie count inside is " + zombiesIn + " Outside is " + zombiesOut);
	}
	
	// Add the synchronized keyword below
	// While there are no zombies inside - wait
	public void killZombie() throws InterruptedException{
		zombiesIn--;
		// let other threads know that a condition has changed
		System.out.println("Killed Zombie. Zombie count inside is " + zombiesIn);
	}
	
	// Add the synchronized keyword below
	public boolean isGameOver(){
		if (zombiesIn == 0 && zombiesOut == 0)
			return true;
		else
			return false;
	}
}