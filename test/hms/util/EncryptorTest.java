package hms.util;

import static org.junit.Assert.*;

import org.junit.*;
import static org.junit.Assert.*;
import hms.util.Encryptor;


public class EncryptorTest {

	@Test
	public void test_encryptorTest() {
		String str = "  This is a Test STREIFH^*%*	 	";
//		System.out.println(str.trim());
//		System.out.println(Encryptor.decode(Encryptor.encode(str)));
//		System.out.println(str.trim());
		assertTrue("Decrypted encrypted string should equal the original string trimmed", str.trim().equals(Encryptor.decode(Encryptor.encode(str))));
	}

}
