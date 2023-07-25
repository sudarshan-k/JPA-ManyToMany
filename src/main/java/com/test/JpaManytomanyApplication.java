package com.test;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.validator.UrlValidator;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.ws.rs.core.UriBuilder;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@SpringBootApplication
public class JpaManytomanyApplication {

/*	@PostConstruct
	public void initESAPI() {
		ESAPI.initialize();

	}*/


	public static void main(String[] args) throws URISyntaxException {
//		SpringApplication.run(JpaManytomanyApplication.class, args);

		String url1 = "https://www.example.com/path/file.html?query=string&another=query";

		String urlString = "https://javarevisited.blogspot.com/2021/05/sql-and-database-phone-interview-questions.html";

		System.out.println(splitTest(urlString));

//		System.out.println(urlValidation(url1));
//		System.out.println(urlSanitization(url));

/*		System.out.println(URLEncoder.encode(urlString));


		System.out.println(escapeDN("<"))*/;

/*		String input = "abc'";
		String output = StringEscapeUtils.escapeHtml4(input);
		System.out.println(output); */// prints "abc&#39;"
//		ESAPI.securityConfiguration().setResourceDirectory("classpath:");
/*		String result = ESAPI.encoder().encodeForHTML(abc);
		System.out.println(result);*/

/*		Encoder enc = ESAPI.encoder();
		String input = "http://example.com/alpha?abc=def&phil=key%3dbdj";
		URI dirtyURI = new URI(input);
		UriBuilder uriData = UriBuilder.fromUri(enc.canonicalize(dirtyURI.getScheme()));
		uriData.path(enc.canonicalize(enc.canonicalize(dirtyURI.getAuthority() + dirtyURI.getPath())));
		System.out.println(uriData.build().toString());
		List<NameValuePair> params = URLEncodedUtils.parse(dirtyURI, "UTF-8");
		Iterator<NameValuePair> it = params.iterator();
		while(it.hasNext()) {
			org.apache.http.NameValuePair nValuePair = it.next();
			uriData.queryParam(enc.canonicalize(nValuePair.getName()), enc.canonicalize(nValuePair.getValue()));
		}
		String canonicalizedUrl = uriData.build().toString();
		System.out.println(canonicalizedUrl);*/


//		System.out.println(oldEscapeDN(abc));
	}

/*	private static String oldEscapeDN(String name) {
		if(null != name) {
			StringBuffer sb = new StringBuffer();
			if ((name.length() > 0) && ((name.charAt(0) == ' ') || (name.charAt(0) == '#'))) {
				sb.append('\\'); // add the leading backslash if needed
			}
			for (int i = 0; i < name.length(); i++) {
				char curChar = name.charAt(i);
				switch (curChar) {
					case '\\':
						sb.append("\\\\");
						break;
					case ',':
						sb.append("\\,");
						break;
					case '+':
						sb.append("\\+");
						break;
					case '"':
						sb.append("\\\"");
						break;
					case '<':
						sb.append("\\<");
						break;
					case '>':
						sb.append("\\>");
						break;
					case ';':
						sb.append("\\;");
						break;
					default:
						sb.append(curChar);
				}
			}
			if ((name.length() > 1) && (name.charAt(name.length() - 1) == ' ')) {
				sb.insert(sb.length() - 1, '\\'); // add the trailing backslash if needed
			}
			return sb.toString();
		}
		return name;
	}*/


//	String output = ESAPI.encoder().encodeForLDAP("hello");

    /*	String value = "(uid=jdoe)";
        String escapedValue = AttributeUtils.escapeValue(value).toString();*/
	public final static boolean disableSanitization  = false;

	public static String escapeDN(String name) {

/*        if (UseOldSMSUtilFunctions == true) {
            return oldEscapeDN(name);
        }*/

		if(disableSanitization){
			return name;
		} else {
			return StringEscapeUtils.escapeXml(name);
		}
	}

	public static String urlValidation(String url){
		UrlValidator urlValidator = new UrlValidator();
		if (!urlValidator.isValid(url)) {
			return null;
		}
		else {
			return url;
		}
	}

	public static String urlSanitization(String url) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_./&:";
		StringBuilder sanitizedUrl = new StringBuilder();
		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);
			if (allowedChars.indexOf(c) != -1) {
				sanitizedUrl.append(c);
			} else {
				sanitizedUrl.append("-");
			}
		}
		System.out.println(sanitizedUrl.toString());
		return sanitizedUrl.toString();
	}

	public static String anotherUrlSanitization(String url){
//		String input = "https://www.example.com/path/file.html?query=string&another=query";
		String regex = "[a-zA-Z0-9_\\-./&]+";
		if (Pattern.matches(regex,url)) {
			System.out.println("Input string is valid.");
			return url;
		} else {
			System.out.println("Input string contains invalid characters.");
			return "Invalid";
		}
	}

	public static String urlSanitizationNew(String urlString) {

		String encodedUrl = null;
		// URL Validation
/*		try {
			URL url = new URL(urlString);
			// Valid URL
			System.out.println("Valid URL: " + url.toString());
		} catch (MalformedURLException e) {
			// Invalid URL
			System.out.println("Invalid URL: " + urlString);
		}

		// URL Encoding
		try {
			encodedUrl = URLEncoder.encode(urlString, "UTF-8");

			System.out.println("Encoded URL: " + encodedUrl);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Failed to encode URL.");
		}
		return encodedUrl;
	}*/

		// URL Validation
		try {
			URL url = new URL(urlString);
			// Valid URL
			System.out.println("Valid URL: " + url.toString());
		} catch (MalformedURLException e) {
			// Invalid URL
			System.out.println("Invalid URL: " + urlString);
		}

		// URL Encoding
		try {
			String[] urlParts = urlString.split("&");
			String baseUrl = urlParts[0];
			String queryString = urlParts.length > 1 ? urlParts[1] : "";

			String encodedBaseUrl = URLEncoder.encode(baseUrl, "UTF-8");
			String encodedQueryString = URLEncoder.encode(queryString, "UTF-8");

			encodedUrl = encodedBaseUrl + "?" + encodedQueryString;
			System.out.println("Encoded URL: " + encodedUrl);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Failed to encode URL.");
		}
		return encodedUrl;
	}

	String urlString = "https://example.com/path?param1=value1&param2=value2&param3=value3";
	public static String splitTest(String urlString){
		String[] urlParts = urlString.split("&");
		String baseurl = escapeDN(urlParts[0]);
		String concat = "";

		if(urlParts.length >1) {
			for (int i = 1; i < urlParts.length; i++) {
				String splitParamsandEncode = splitParamsandEncode(urlParts[i]);

				concat = concat + splitParamsandEncode;
			}
		}
		String url = baseurl + concat;
		return  url;
	}
	private static String splitParamsandEncode(String params){
		String[] paramValues = params.split("=");
		String paramhead =  paramValues[0];
		String paramvalue = paramValues[1];
		String concat;
		try {
			 concat = "&"+ paramhead + "=" +URLEncoder.encode(paramvalue, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return concat;
	}

}