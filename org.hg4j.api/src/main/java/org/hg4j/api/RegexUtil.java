package org.hg4j.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author VishalZanzrukia
 *
 */
public class RegexUtil {

	enum Tag {
		B, I, U
	}

	public static String BOLD_MATCHER_PATTERN = "``B\\ ([^\\s]+)(.*)";
	public static String BOLD_REPLACE_PATTERN = "<B>$1</B>$2";

	public static String replaceBoldPattern(String s) {
		return s.replaceAll(BOLD_MATCHER_PATTERN, BOLD_REPLACE_PATTERN);
	}

	public static String manupulateLine(String s) {
		char c;
		boolean isDone = false;
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isWhitespace(c) && !isDone) {
				buffer.append("&nbsp;&nbsp;&nbsp;");
			} else if (c == '<') {
				buffer.append("&lt;");
			} else if (c == '>') {
				buffer.append("&gt;");
			} else {
				isDone = true;
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	public static void testString() throws IOException {

		try (BufferedReader reader = Files.newBufferedReader(Paths.get("./resources/test_text_input.txt"), StandardCharsets.UTF_8)) {

			StringBuilder stringBuilder = new StringBuilder();
			if (!new File("./resources/test_text_output.txt").exists()) {
				Files.createFile(Paths.get("./resources/test_text_output.txt"));
			}

			reader	.lines()
					.forEach(s -> stringBuilder	.append(manupulateLine(s))
												.append("</BR>\n"));
			Files.write(Paths.get("./resources/test_text_output.txt"), stringBuilder.toString()
																					.getBytes(),
					StandardOpenOption.APPEND);
		}

		try (BufferedReader reader = Files.newBufferedReader(Paths.get("./resources/test_text_output.txt"), StandardCharsets.UTF_8)) {
			reader	.lines()
					.forEach(System.out::println);
		}

	}

	public static void helloRegex() {
		Pattern pattern = Pattern.compile("\\ ");
		Matcher matcher;
		matcher = pattern.matcher("		➤ Input Type : nothing");
		while (matcher.find()) {
			System.out.println(matcher.replaceFirst("&nbsp;&nbsp;"));
		}
	}

}
