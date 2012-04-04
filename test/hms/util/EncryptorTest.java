package hms.util;

import org.junit.*;
import static org.junit.Assert.*;
import hms.util.Encryptor;

public class EncryptorTest {
	@Test
	public void test_encodingTrimsWhitespaceFromBeginningAndEnd() {
		String test = "     test string    ";
		assertEquals(Encryptor.encode(test.trim()), Encryptor.encode(test));
	}
	
	@Test
	public void test_decodingTrimsWhitespaceFromBeginningAndEnd() {
		String test = "     test string    ";
		assertEquals(Encryptor.decode(test.trim()), Encryptor.decode(test));
	}
	
	@Test
	public void test_encodingAndThenDecodingReturnsTheSameValue() {
		String test = "this is a test";
		assertEquals(test, Encryptor.decode(Encryptor.encode(test)));
	}
}
