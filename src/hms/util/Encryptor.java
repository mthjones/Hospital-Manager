package hms.util;
public class Encryptor {
	
	
	public static void main(String[] args){
		String str = "  This is a Test STREIFH^*%*	 	";
		System.out.println(str.trim());
		System.out.println(Encryptor.decode(Encryptor.encode(str)));
		System.out.println(str.trim());
		System.out.println(str.trim().equals(Encryptor.decode(Encryptor.encode(str))));
	}
	/*
	*@param word the string to be encoded
	*@return an encoded version of the word
	*/
	public static String encode(String word){
		String RV = word.trim();
		char[] value = RV.toCharArray();
		for(int i = 0; i<RV.length(); i++){
			if(value[i] >= 'a' && value[i] <= 'z'){
				value[i] -= 'a';
				value[i] = (char)((value[i] + 13)%26);
				value[i] += 'a';
			}
			if(value[i] >= 'A' && value[i] <= 'Z'){
				value[i] -= 'A';
				value[i] = (char)((value[i] + 13)%26);
				value[i] += 'A';
			}
		}
		
		return new String(value);
		
	}
	
	
	/*
	*@param word an already encoded string
	*@return an an un-encoded string
	*/
	public static String decode(String word){
		return encode(word);		
	}
}
