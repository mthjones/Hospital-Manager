package hms.util;

public class Priority implements Comparable
{
	// Our static values. These are used instead of using the constructor in order to limit the values that can
	// be used
	public static Priority HIGH = new Priority(3);
	public static Priority MEDIUM = new Priority(2);
	public static Priority LOW = new Priority(1);
	
	private Integer priority;
	
	/**
	 * Constructs a new Priority based on the given priority value
	 * @param priority The priority value for the object
	 */
	private Priority(int priority) {
		this.priority = priority;
	}
	
	/**
	 * Compares this Priority to another Priority object and returns a value based on the comparison.
	 * @param other The other object to compare this Priority to
	 * @return 0 if the other Priority is equal; -1 if it is less, 1 if it is greater
	 * @throws ClassCastException if the given object is not a Priority
	 */
	public int compareTo(Object other) throws ClassCastException {
		if (!(other instanceof Priority)) {
			throw new ClassCastException();
		}
		return this.toInteger().compareTo(((Priority)other).toInteger());
	}
	
	/**
	 * Returns a boolean value dependent on whether this Priority is equal to the given Priority.
	 * @param other The other object to test if this Priority is equivalent to.
	 * @return false if the other Priority is not equal; true if it is
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		return this.hashCode() == other.hashCode();
	}
	
	/**
	 * Returns the hashcode for the object which is dependent only on its priority value.
	 * @return The hashcode value for this Priority
	 */
	public int hashCode() {
		return this.priority.hashCode();
	}
	
	/**
	 * Returns an integer representation of the priority.
	 * @return an integer representation of the priority
	 */
	public Integer toInteger() {
		return this.priority;
	}
	
	/**
	 * Returns the appropriate preset priority from the given value. If the value is not a preset value, then it returns null.
	 * @param value The integer value to get the Priority of
	 * @return The appropriate Priority for the value
	 */
	public static Priority fromInteger(Integer value) {
		switch (value) {
			case 3: return Priority.HIGH;
			case 2: return Priority.MEDIUM;
			case 1: return Priority.LOW;
			default: return null;
		}
	}
	
	/**
	 * Returns a string representation of the priority.
	 * @return a string representation of the priority
	 */
	public String toString() {
		switch (this.priority) {
			case 3: return "High";
			case 2: return "Medium";
			case 1: return "Low";
			default: return "Unknown";
		}
	}
}