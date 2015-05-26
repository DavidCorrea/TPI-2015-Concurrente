package model;
import java.util.ArrayList;
import java.util.Random;

public class ConsurSort {
	
	private ArrayList<Integer> buffer;
	private int maxAmountOfThreads;
	private int threadsRunning;
	
	public ConsurSort() {
		this.buffer = new ArrayList<Integer>();
	}
	
	public void setMaxAmountOfThreads(int maxAmountOfThreads) {
		this.maxAmountOfThreads = maxAmountOfThreads;
	}
	
	public void setThreadsRunning(int threadsRunning) {
		this.threadsRunning = threadsRunning;
	}
	
	private boolean canStartAThread() {
		return threadsRunning < maxAmountOfThreads;
	}
	
	/**
	 * Returns the size of the ConcurSort.
	 * @return The size of the ConcurSort.
	 */
	public int size() {
		return buffer.size();
	}

	/**
	 * Returns if the ConcurSort is empty.
	 * @return If the ConcurSort is empty.
	 */
	public boolean isEmpty() {
		return buffer.isEmpty();
	}
	
	/**
	 * Given a Parameter i, returns if ConcurSort contains it.
	 * @param i Value you're looking for.
	 * @return If the ConcurSort contains "i".
	 */
	public boolean contains(int i) {
		return buffer.contains(i);
	}
	
	/**
	 * 
	 * @param i
	 */
	public synchronized void add(int i) {
		buffer.add(i);
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public int get(int position) {
		return buffer.get(position);
	}
	
	/**
	 * 
	 * @param position
	 * @param newValue
	 */
	public void set(int position, int newValue) {
		buffer.set(position, newValue);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPivot() {
		Random rand = new Random();
		int random = rand.nextInt(this.size());
		return buffer.get(random);
	}
	
	/**
	 * 
	 * @param pivot
	 * @return
	 */
	public ConsurSort lessThan(int pivot) {
		ConsurSort cs = new ConsurSort();
		for (int i : buffer) {
			if(i < pivot)
				cs.add(i);
		}
		return cs;
	}
	
	/**
	 * 
	 * @param pivot
	 * @return
	 */
	public ConsurSort greaterThan(int pivot) {
		ConsurSort cs = new ConsurSort();
		for (int i : buffer) {
			if(i > pivot)
				cs.add(i);
		}
		return cs;
	}
	
	public synchronized ConsurSort quicksort(ConsurSort cs) throws InterruptedException {
		if(cs.size() <= 1) {
			threadsRunning--;
			notifyAll();
			return cs;	
		}
		int pivot = cs.getPivot();
		ConsurSort leftCs = cs.lessThan(pivot);
		ConsurSort leftResult = sortInThread(cs, leftCs);
		ConsurSort rightCs = cs.greaterThan(pivot);
		ConsurSort rightResult = sortInThread(cs, rightCs);
		return this.build(leftResult, pivot, rightResult);
	}
	
	private synchronized ConsurSort sortInThread(ConsurSort rootCs, ConsurSort branchCs) throws InterruptedException{
		while(!rootCs.canStartAThread())
			wait();	
		threadsRunning++;		
		branchCs.setMaxAmountOfThreads(rootCs.maxAmountOfThreads);
		branchCs.setThreadsRunning(rootCs.threadsRunning);
		Sorter strRes = new Sorter(branchCs);
		strRes.run();
		threadsRunning--;
		notifyAll();
		return strRes.getResult();
	}
	
	private ConsurSort build(ConsurSort leftResult, int pivot, ConsurSort rightResult) {
		ConsurSort result = new ConsurSort();
		for (int i = 0; i < leftResult.size(); i++) {
			int toAdd = leftResult.get(i);
			result.add(toAdd);
		}
		result.add(pivot);
		for (int i = 0; i < rightResult.size(); i++) {
			int toAdd = rightResult.get(i);
			result.add(toAdd);
		}
		return result;
	}

	public static void main(String[] args) throws InterruptedException {		
		ConsurSort cs = new ConsurSort();
		cs.setMaxAmountOfThreads(3);
		cs.add(5);
		cs.add(2);
		cs.add(8);
		cs.add(6);
		cs.add(1);
		cs.add(3);
		cs.add(9);
		cs.add(7);
		cs.add(10);
		cs.add(4);
		ConsurSort result = cs.quicksort(cs);
	}
}
