package com.iamalokit.anotherblog.util;

import java.net.URI;

public class URLUtil {
	public static URI getHost(URI uri) {
		URI effectiveURI = null;
		try {
			effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
		} catch (Throwable var4) {
			effectiveURI = null;
		}
		return effectiveURI;
	}
}
