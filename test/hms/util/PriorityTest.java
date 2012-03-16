package hms.util;

import org.junit.*;
import static org.junit.Assert.*;

import hms.util.Priority;

public class PriorityTest
{
	@Test
	public void test_PrioritiesOfSameLevelAreEqual() {
		assertEquals(Priority.HIGH, Priority.HIGH);
		assertEquals(Priority.MEDIUM, Priority.MEDIUM);
		assertEquals(Priority.LOW, Priority.LOW);
	}
	
	@Test
	public void test_PrioritiesAreOrderedCorrectly() {
		assertEquals(Priority.HIGH.compareTo(Priority.HIGH), 0);
		assertEquals(Priority.HIGH.compareTo(Priority.MEDIUM), 1);
		assertEquals(Priority.HIGH.compareTo(Priority.LOW), 1);
		
		assertEquals(Priority.MEDIUM.compareTo(Priority.HIGH), -1);
		assertEquals(Priority.MEDIUM.compareTo(Priority.MEDIUM), 0);
		assertEquals(Priority.MEDIUM.compareTo(Priority.LOW), 1);
		
		assertEquals(Priority.LOW.compareTo(Priority.HIGH), -1);
		assertEquals(Priority.LOW.compareTo(Priority.MEDIUM), -1);
		assertEquals(Priority.LOW.compareTo(Priority.LOW), 0);
	}
	
	@Test
	public void test_PrioritiesToStringRepresentation() {
		assertEquals(Priority.HIGH.toString(), "High");
		assertEquals(Priority.MEDIUM.toString(), "Medium");
		assertEquals(Priority.LOW.toString(), "Low");
	}
	
	@Test
	public void test_PrioritiesCanConvertToAndFromIntegers() {
		assertEquals(Priority.fromInteger(Priority.HIGH.toInteger()), Priority.HIGH);
		assertEquals(Priority.fromInteger(Priority.MEDIUM.toInteger()), Priority.MEDIUM);
		assertEquals(Priority.fromInteger(Priority.LOW.toInteger()), Priority.LOW);
	}
}