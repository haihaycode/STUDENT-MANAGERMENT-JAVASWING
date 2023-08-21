package Helper;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

public class Converter {
	public static String removeDiacritics(String input) {
		String normalizedString = Normalizer.normalize(input, Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(normalizedString).replaceAll("").toUpperCase();
	}
}