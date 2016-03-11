package org.cbioportal.cgds_java.framework.rest.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.NewCookie;

public class CookieHandler implements ClientResponseFilter, ClientRequestFilter {

	private Map<String, NewCookie> newCookies = null;

	@Override
	public void filter(ClientRequestContext clientRequestContext) throws IOException {
		if (this.newCookies != null && !this.newCookies.isEmpty()) {
			List<Object> cookieValues = new ArrayList<>();
			String value = null;
			for (String key : this.newCookies.keySet()) {
				NewCookie cookie = this.newCookies.get(key);
				if (value == null)
					value = cookie.getName() + "=" + cookie.getValue() + ";";
				else
					value = value + " " + cookie.getName() + "=" + cookie.getValue() + ";";
			}
			cookieValues.add(value);
			clientRequestContext.getHeaders().put("Cookie", cookieValues);
		}
	}

	@Override
	public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext)
			throws IOException {
		@SuppressWarnings("unused")
		List<?> headerCookies = null;
		if (clientResponseContext.getCookies() != null) {
			if ((headerCookies = clientResponseContext.getHeaders().get("Set-Cookie")) != null) {
				if (this.newCookies == null)
					this.newCookies = new HashMap<>();
				this.newCookies.putAll(clientResponseContext.getCookies());
			}
		}
	}

}
