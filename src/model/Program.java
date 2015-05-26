package model;

public class Program {
	
	private int maxAmountOfThreads;
	private int threadsRunning;

	public Program(int maxAmountOfThreads, ConsurSort cs) {
		this.maxAmountOfThreads = maxAmountOfThreads;
		this.threadsRunning = 0;
	}
	
	private boolean canStartAThread() {
		return threadsRunning < maxAmountOfThreads;
	}
	
	public synchronized void execute() throws InterruptedException {
		while(!this.canStartAThread())
			wait();
		
	}
	
	public static void main(String[] args) {
		
	}
}
