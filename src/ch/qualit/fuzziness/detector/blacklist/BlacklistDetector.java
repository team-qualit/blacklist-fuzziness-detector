package ch.qualit.fuzziness.detector.blacklist;

import ch.qualit.fuzziness.detector.spi.FuzzynessDetector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BlacklistDetector implements FuzzynessDetector {
	private static final String BLACKLIST_WORDS_FILE = "blacklisted-words.csv";
	private HashMap<String, ArrayList<String>> words;

	public BlacklistDetector() {
		words = readWords();
	}

	@Override
	/**
	 * Validates the word for blacklisted words and returns a list of suggestions.
	 * @param word String
	 * @return suggestions ArrayList<String>
	 */
	public List<String> validate(String word) {
		ArrayList<String> suggestions = new ArrayList<String>();
		for (String s : words.keySet()) {
			if (word != null && word.indexOf(s) > -1) {
				words.get(s);
				suggestions.addAll(words.get(s));
			}
		}
		return suggestions;
	}

	/**
	 * Reads a semicolon separated csv file and parses it into a HashMap with
	 * blacklisted words.
	 * 
	 * @return blacklistedWords HashMap<String, ArrayList<String>>
	 */
	private HashMap<String, ArrayList<String>> readWords() {
		HashMap<String, ArrayList<String>> blacklistedWords = new HashMap<String, ArrayList<String>>();

		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(this.getClass()
					.getResourceAsStream("/" + BLACKLIST_WORDS_FILE)));
			String line = null;

			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(";");
				ArrayList<String> suggestions = new ArrayList<String>();

				// files need to fulfill this schema: blacklisted
				// word;suggestion1;suggetion2;other suggestions until next line
				for (String token : tokens) {
					// skip the first token
					if (!token.equals(tokens[0])) {
						suggestions.add(token);
					}
				}
				blacklistedWords.put(tokens[0], suggestions);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return blacklistedWords;
	}

	public HashMap<String, ArrayList<String>> getWords() {
		return words;
	}

	public void setWords(HashMap<String, ArrayList<String>> words) {
		this.words = words;
	}
}
