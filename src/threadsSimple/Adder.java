package threadsSimple;

import java.util.Vector;

public class Adder {

	public static void main(String[] args) throws InterruptedException {
		MyThread one = new MyThread("bob");
		MyThread two = new MyThread("ann");
		
		one.start();
		two.start();
		
		Thread one = new Thread(new MyRunnable("bob"));
		Thread two = new Thread(new MyRunnable("ann"));
		
		one.start();
		two.start();
		
		Vector<Integer> results = new Vector<Integer>();
		
		Thread one = new Thread(new WorkerBee(0, 1000, results));
		Thread two = new Thread(new WorkerBee(1000, 2000, results));
		Thread three = new Thread(new WorkerBee(2000, 3000, results));
		
		Runnable fourInt = () -> {
			int sum = 0;
			for(int i = 0; i < 100; i++)
				sum = sum + i;
			//result to be sent back to the main thread
			results.addElement(sum);
		};
		Thread four = new Thread(fourInt);
		
		one.start();
		two.start();
		three.start();
		four.start();
		
		one.join();
		two.join();
		three.join();
		//all thread above should be done executing
		
		int total = 0;
		for(Integer num:results){
			System.out.println(num);
			total = total + num;
		}
		System.out.println("Done! Total of all math " + total);
	}
}

class WorkerBee implements Runnable{
	private int start;
	private int end;
	private Vector<Integer> result; //address of where to store result
	
	public WorkerBee(int start, int end, Vector<Integer> result){
		this.start = start;
		this.end = end;
		this.result = result;
	}
	
	@Override
	public void run() {
		int sum = 0;
		for(int i = start; i < end; i++)
			sum = sum + i;
		//result to be sent back to the main thread
		result.addElement(sum);
		System.out.println("Total is " + sum);
	}
}
