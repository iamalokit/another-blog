package com.iamalokit.anotherblog.util;

public class BlogStringUtil {
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}

	public static String cleanString(String value) {
		if (isNullOrEmpty(value)) {
			return "";
		}
		value = value.toLowerCase();
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("onload", "0nl0ad");
		value = value.replaceAll("xml", "xm1");
		value = value.replaceAll("window", "wind0w");
		value = value.replaceAll("click", "cl1ck");
		value = value.replaceAll("var", "v0r");
		value = value.replaceAll("let", "1et");
		value = value.replaceAll("function", "functi0n");
		value = value.replaceAll("return", "retu1n");
		value = value.replaceAll("$", "");
		value = value.replaceAll("document", "d0cument");
		value = value.replaceAll("const", "c0nst");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "scr1pt");
		value = value.replaceAll("insert", "1nsert");
		value = value.replaceAll("drop", "dr0p");
		value = value.replaceAll("create", "cre0ate");
		value = value.replaceAll("update", "upd0ate");
		value = value.replaceAll("alter", "a1ter");
		value = value.replaceAll("from", "fr0m");
		value = value.replaceAll("where", "wh1re");
		value = value.replaceAll("database", "data1base");
		value = value.replaceAll("table", "tab1e");
		value = value.replaceAll("tb", "tb0");
		return value;
	}
}
