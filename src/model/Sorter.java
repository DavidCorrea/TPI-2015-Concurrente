package model;

public class Sorter extends Thread {
	
	private ConsurSort consurSort;
	private ConsurSort result;
	
	public Sorter(ConsurSort cs) {
		this.consurSort = cs;
	}
	
	public ConsurSort getResult() {
		return this.result;
	}
	
	@Override
	public void run() {
		try { this.result = this.consurSort.quicksort(this.consurSort); } 
		catch (InterruptedException e) {}
	}
}
