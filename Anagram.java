/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Replace the following statement with your code
		
		str1 = preProcess(str1);
		str2 = preProcess(str2);

		// Different lengths can't be anagrams
		if (str1.length() != str2.length()) {
            return false; 
        }

		for (int i = 0; i < str1.length(); i++) {
			char ch1 = str1.charAt(i);
			int countCh1 = 0;
			int countCh2 = 0;

			// check how many times each Char is in the string 1
			for (int j = 0; j < str1.length(); j++) {
				if (ch1 == str1.charAt(j)) {
					countCh1++;
				}
			}
			// check how many times each Char is in the string 2
			for (int g = 0; g < str2.length(); g++) {
				if (ch1 == str2.charAt(g)) {
					countCh2++;
				}
			}

			// if a char count is different between the two string return false.
			if (countCh1 != countCh2) {
				return false;
			}
		}


		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// Replace the following statement with your code
		String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                newStr += ch;
            }
        }
        return newStr.toLowerCase();
    } 

	
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		// Replace the following statement with your code
		String newStr = "";
        
        while (!str.isEmpty()) {
            // Generates random num (according to Index)
            int randNum = (int)(Math.random() * str.length());
            newStr += str.charAt(randNum);
            if (randNum == str.length() - 1) {
                // If it's the last character, no need for substring after randomIndex
                str = str.substring(0, randNum);
            } else {
                // Otherwise, split the string into parts and remove the character
                str = str.substring(0, randNum) + str.substring(randNum + 1);
            }       }
        return newStr;

	}
}
