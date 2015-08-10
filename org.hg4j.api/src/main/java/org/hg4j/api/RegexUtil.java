package org.hg4j.api;

/**
 * @author VishalZanzrukia
 *
 */
public class RegexUtil {
	
	enum Tag{
		B,
		I,
		U
	}

	public static String BOLD_MATCHER_PATTERN = "``B\\ ([^\\s]+)(.*)";
	public static String BOLD_REPLACE_PATTERN = "<B>$1</B>$2";

	public static String replaceBoldPattern(String s) {
		return s.replaceAll(BOLD_MATCHER_PATTERN, BOLD_REPLACE_PATTERN);
	}
	
}
