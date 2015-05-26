package test;

import static org.junit.Assert.*;
import model.ConsurSort;

import org.junit.Before;
import org.junit.Test;

public class TestConcurSort {

	private ConsurSort consurSort; 
	
	@Before
	public void setUp() {
		this.consurSort = new ConsurSort();
	}
	
	@Test
	public void sizeTest() {
		assertEquals(0, this.consurSort.size());
	}
	
	@Test 
	public void emptyConsurSortIsEmptyTest() {
		assertTrue(this.consurSort.isEmpty());
	}
	
	@Test 
	public void nonEmptyConsurSortIsEmptyTest() {
		this.consurSort.add(3);
		assertFalse(this.consurSort.isEmpty());
	}
	
	@Test
	public void emptyConsurSortContainsTest() {
		assertFalse(this.consurSort.contains(2));
	}
	
	@Test
	public void nonEmptyConsorSortContainsTest() {
		this.consurSort.add(2);
		assertTrue(this.consurSort.contains(2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void emptyConsurSortGetTest() {
		this.consurSort.get(11);
	}
	
	@Test
	public void addTest() {
		this.consurSort.add(3);
		this.consurSort.add(4);
		assertEquals(2, this.consurSort.size());
	}
	
	@Test
	public void getPivotTest() {
		this.consurSort.add(3);
		this.consurSort.add(4);
		int pivot = this.consurSort.getPivot();
		assertTrue(this.consurSort.contains(pivot));
	}
	
	@Test
	public void lessThanTest() {
		this.consurSort.add(1);
		this.consurSort.add(2);
		this.consurSort.add(3);
		this.consurSort.add(4);
		this.consurSort.add(5);
		ConsurSort cs = this.consurSort.lessThan(3);
		assertTrue(cs.contains(1));
		assertTrue(cs.contains(2));
	}
	
	@Test
	public void greaterThanTest() {
		this.consurSort.add(1);
		this.consurSort.add(2);
		this.consurSort.add(3);
		this.consurSort.add(4);
		this.consurSort.add(5);
		ConsurSort cs = this.consurSort.greaterThan(3);
		assertTrue(cs.contains(4));
		assertTrue(cs.contains(5));
	}
}
