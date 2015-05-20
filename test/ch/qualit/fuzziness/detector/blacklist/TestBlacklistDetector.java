package ch.qualit.fuzziness.detector.blacklist;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBlacklistDetector {
	private BlacklistDetector detector;
	private String notBlacklistedWord = "This string does not contain a blacklisted word";
	private String blacklistedWord = "This string contains the blacklisted word 'good'.";
	private String twiceBlacklistedWord = "This string contains the blacklisted word 'good' and 'bad'.";

	@Before
	public void setUp() throws Exception {
		detector = new BlacklistDetector();
	}

	@Test
	public void testReadWords() {
		HashMap<String, ArrayList<String>> words = detector.getWords();

		// test some words from the blacklisted-words file
		Assert.assertEquals(1, words.get("usually").size());
		Assert.assertEquals(1, words.get("good").size());
	}

	@Test
	public void testValidateNotBlacklistedWord() {
		ArrayList<String> suggestions = (ArrayList<String>) detector
				.validate(notBlacklistedWord);
		Assert.assertTrue(suggestions.isEmpty());
	}

	@Test
	public void testValidateBlacklistedWord() {
		ArrayList<String> suggestions = (ArrayList<String>) detector
				.validate(blacklistedWord);
		Assert.assertFalse(suggestions.isEmpty());
	}

	@Test
	public void testValidateTwiceBlacklistedWord() {
		ArrayList<String> suggestions = (ArrayList<String>) detector
				.validate(twiceBlacklistedWord);
		Assert.assertEquals(2, suggestions.size());
	}

	@Test
	public void testEmptyString() {
		ArrayList<String> suggestions = (ArrayList<String>) detector
				.validate("");
		Assert.assertTrue(suggestions.isEmpty());
	}

	@Test
	public void testNullString() {
		ArrayList<String> suggestions = (ArrayList<String>) detector
				.validate(null);
		Assert.assertTrue(suggestions.isEmpty());
	}

}
