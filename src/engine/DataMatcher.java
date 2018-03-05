package engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMatcher {
	
	public static String matchData(String text, String regex) throws Exception {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group();
		}
		throw new Exception("Can't extract correct data");
	}

}
