import static org.junit.Assert.*;

import org.junit.Test;
import hms.Managers.Encryptor;


public class Encryptortest {

	@Test
	public void test() {
		String testString = "  This is a Test STREIFH^*%*	 	";
		assertTrue("Encryptor should be able to encrypt and decrypt, and give the same output as the original", testString.trim().equals(Encryptor.decode(Encryptor.encode(testString)))  );
	}

}
