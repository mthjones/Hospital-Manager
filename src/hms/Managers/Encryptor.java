//package hms.Managers;
public class Encryptor {
	
	
	public static void main(String[] args){
		System.out.println("  This is a Test STREIFH^*%*	 	".trim());
		System.out.println(Encryptor.decode(Encryptor.encode("  This is a Test STREIFH^*%*	 	")));
		System.out.println("  This is a Test STREIFH^*%*	 	".trim().equals(Encryptor.decode(Encryptor.encode("  This is a Test STREIFH^*%*	 	"))));
	}
	/*
	*@param word the string to be encoded
	*@return an encoded version of the word
	*/
	public static String encode(String word){
		String RV = word.trim();
		for(int i = 0; i< word.trim().length(); i++){

			//System.out.print(".");
			RV += " ";
		}
		char[] value = RV.toCharArray();
		for(int i = 0; i<word.trim().length(); i++){
			if(RV.charAt(i)*4+7 < Character.MAX_VALUE){
				value[i] = (char) (RV.charAt(i)*4+7);
				value[i+word.trim().length()] = '\t';
			}
		}
		
		return new String(value);
		
	}
	
	
	/*
	*@param word an already encoded string
	*@return an an un-encoded string
	*/
	public static String decode(String word){
		char[] RV = word.toCharArray();
		int j = word.length()/2;
		for(int i = 0; i< j; i++){
			if(RV[i+j] == '\t')
				RV[i] = (char) ((RV[i] - 7)/4);
		}
		String V = new String(RV);
		return V.trim();
		
	}
}
