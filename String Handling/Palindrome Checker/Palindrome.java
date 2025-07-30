//next lab
//1st pract - 1,2
//2nd pract - 1,2
//3rd pract - inheritance, package

//2 hours exam - 2 questions
/*
 Design a utility for a book reading app that 
highlights palindrome words/sentences. Read a paragraph and identify all 
palindromes—words or full sentences. Ignore punctuation and case.
*/
import java.util.*;
class Palindrome {
	
	static boolean isPalindrome(char[] letters) {
    for (int i = 0, j = letters.length - 1; i < j; i++, j--) {
        if (Character.toLowerCase(letters[i]) != Character.toLowerCase(letters[j])) {
            return false;
        }
    }
    return true;
}

	public static void main(String[] args) {
		int palindromeCount = 0;
		Scanner sc = new Scanner(System.in);
		String sentence = sc.nextLine();
		
		System.out.println(sentence);
		
		//parsing words from the sentence
		String words[] = sentence.split(" ");
		
		for(int i=0; i < words.length ; i++) {
			//System.out.println(words[i]);
			char[] letters = words[i].toCharArray();
			
			/*for(int p=0 ; p<letters.length ; p++) {
				System.out.println(letters[p]);
			}*/
			if(Palindrome.isPalindrome(letters)) {
				words[i] = "**".concat(words[i]).concat("**");
				palindromeCount++;
				//System.out.println(words[i]);
			}
		}
		
		StringBuilder sb= new StringBuilder();
		for(int i=0; i< words.length ; i++) {
			sb.append(words[i]).append(" ");
		}
		System.out.println(sb);
		System.out.println("Palindrome Count Frequency : " + palindromeCount);
	}
}