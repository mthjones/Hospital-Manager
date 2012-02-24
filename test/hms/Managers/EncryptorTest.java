package hms.Managers;

import static org.junit.Assert.*;

import org.junit.*;
import static org.junit.Assert.*;
import hms.Managers.Encryptor;


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
