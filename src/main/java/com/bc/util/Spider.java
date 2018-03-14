package com.bc.util;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

public class Spider {
	
	public static String get(String url) {
		Connection con = Jsoup.connect(url);
		con.ignoreHttpErrors(true);
		con.ignoreContentType(true);
		con.method(Method.GET);
		try {
			return con.execute().body();
		} catch (IOException e) {
			return "failed ! msg : " + e.getMessage();
		}
	}
	
	public static String post(String url, String body) {
		Connection con = Jsoup.connect(url);
		con.ignoreHttpErrors(true);
		con.ignoreContentType(true);
		con.header("Content-Type", "application/json");
		con.requestBody(body);
		con.method(Method.POST);
		try {
			return con.execute().body();
		} catch (IOException e) {
			return "failed ! msg : " + e.getMessage();
		}
	}
	
	public static String put(String url, String body) {
		Connection con = Jsoup.connect(url);
		con.header("Content-Type", "application/json");
		con.ignoreHttpErrors(true);
		con.ignoreContentType(true);
		con.requestBody(body);
		con.method(Method.PUT);
		try {
			return con.execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			return "failed ! msg : " + e.getMessage();
		}
	}
	
	public static String delete(String url) {
		Connection con = Jsoup.connect(url);
		con.ignoreHttpErrors(true);
		con.ignoreContentType(true);
		con.method(Method.DELETE);
		try {
			return con.execute().body();
		} catch (IOException e) {
			return "failed ! msg : " + e.getMessage();
		}
	}
}