import java.util.*;

class Chat {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		
		
		//removing whitespaces, special characters and numbers from the string 
		String Text = text.trim();
		String updatedText = Text.replaceAll("\\s+", " ");	
		updatedText = updatedText.replaceAll("[^a-zA-Z\\s]","");
		//System.out.println(updatedText);
		
	
		//string to lowercase
		updatedText = updatedText.toLowerCase();
		//System.out.println(updatedText);
		
		//parsing words from the string
		String[] words = updatedText.split(" ");
		
		/*
		for(int i=0; i<words.length ; i++) {
			System.out.println(words[i]);
		}
		*/
		
		//for inappropriate Words
		
		/*
		ArrayList<String> inappropriateWords = new ArrayList<>();
		inappropriateWords.add("stupid");
		inappropriateWords.add("failure");
		inappropriateWords.add("disappointment");
		inappropriateWords.add("disgrace");
		inappropriateWords.add("bad");
		*/
		String[] inappropriateWords = {"stupid","failure","disappointment","disgrace","bad","disgusting","disappointing","creep"};
		
		boolean isEqual = false;
		for(int i=0; i< words.length; i++) {
			for(int j=0; j< inappropriateWords.length ; j++) {
				isEqual = words[i].equals(inappropriateWords[j]);
				
				if(isEqual) {
					words[i] = words[i].replaceAll("[a-zA-z]","*");
					//System.out.println(words[i]);
				}
			}
		}
		/*
		for(int i=0; i<words.length ; i++) {
			System.out.println(words[i]);
		}
		*/
		
		//palindrome checker
		//boolean isPalindrome(String w) {
		//	return false;
		//}
		
		//appending array of words to string
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<words.length ; i++) {
			if(words[i].contains("*")) {
				sb.append(words[i]).append(" ");
			}
			else {
				
				if(false) {
					words[i] = "$$".concat(words[i]).concat("$$");
					words[i] = words[i].substring(0,1).toUpperCase().concat(words[i].substring(1));
					sb.append(words[i]).append(" ");
				}
				else {
					words[i] = words[i].substring(0,1).toUpperCase().concat(words[i].substring(1));
					sb.append(words[i]).append(" ");
				}
			}
		}
		
		String finalText = sb.toString();
		System.out.println("Final message: " + finalText);
	}
}