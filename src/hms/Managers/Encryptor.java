//package hms.Managers;
public class Encryptor {
	
	
	public static void main(String[] args){
		System.out.println("                 OMG, This is the shit ma man");
		System.out.println(Encryptor.decode(Encryptor.encode("OMG, This is the shit ma man")));
	}
	/*
	*@param word the string to be encoded
	*@return an encoded version of the word
	*/
	static String encode(String word){
		String RV = word.trim();
		for(int i = 0; i< word.length(); i++){

			System.out.print(".");
			RV += " ";
		}
		char[] value = RV.toCharArray();
		for(int i = 0; i<word.length(); i++){
			if(RV.charAt(i)*4+7 < Character.MAX_VALUE){
				value[i] = (char) (RV.charAt(i)*4+7);
				value[i+word.length()] = '\t';
			}
		}
		
		return new String(value);
		
	}
	
	
	/*
	*@param word an already encoded string
	*@return an an un-encoded string
	*/
	static String decode(String word){
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