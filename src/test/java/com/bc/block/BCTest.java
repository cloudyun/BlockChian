package com.bc.block;

import com.alibaba.fastjson.JSONObject;
import com.bc.util.Spider;
import com.bc.vo.Message;

public class BCTest {
	
	public static void main(String[] args) {
//		init();
		add(125);
	}
	
	public static void init() {
		String url = "http://localhost:8080/bc/init";
		System.out.println(Spider.get(url));
	}
	
	public static void add(int vac) {
		String url = "http://localhost:8080/bc/add";
		Message m = new Message();
		m.setVac(vac);
		String body = JSONObject.toJSONString(m);
		System.out.println(Spider.post(url, body));
	}
}
