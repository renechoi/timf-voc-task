package timf.voc.task.config.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {
	private static final int TOKEN_LENGTH = 30;

	public static String randomCharacter(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public static String randomCharacterWithPrefix(String prefix) {
		return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
	}
}