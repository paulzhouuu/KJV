package kjv.cloud.api.domain;

import org.springframework.util.StringUtils;

public class WordUtils {
	public static String findBookName(String word) {
		return StringUtils.capitalize(word);
	}

}
