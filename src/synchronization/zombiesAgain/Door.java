//This class represents the friend opening the door to let the zombie in
//package zombies;

public class Door implements Runnable{
	private ZombieStats stats;
	private int friendNum;
	
	public Door(int num, ZombieStats s){
		stats = s;
		friendNum = num;
	}
	
	@Override
	public void run(){
		// if there are zombies outside
		while(true){
			// if the killer is only battling 1 zombie -- let one more in -- he/she can handle it
			try {
				stats.letZombieIn();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				// wait a random time before looping 
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				System.out.println("Interrupted friend " + friendNum);
			}
		}
	}

}