// This class represents the killer of the zombies
//package zombies;

public class Killer implements Runnable{
	private ZombieStats stats;
	
	public Killer(ZombieStats s){
		stats = s;
	}
	
	@Override
	public void run(){
		while(true){
			// if there are zombies in the room -- kill one
			try {
				stats.killZombie();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(stats.isGameOver())
				return;
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException e){
				System.out.println("Interrupted the killer");
			}
		}	
	}
}