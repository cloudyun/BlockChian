package com.bc.util;

import static org.junit.Assert.*;
import org.junit.Test;
import com.alibaba.fastjson.JSONObject;
import com.bc.vo.Message;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class SpiderTest {

	@Test
	public void testGet() {
		String url = "http://localhost:8080/bc/init";
		String response = Spider.get(url);
		System.out.println(response);
		Assert.assertNotNull(response);
	}

	@Test
	public void testPost() {
		String url = "http://localhost:8080/bc/add";
		Message m = new Message();
		m.setVac(123);
		String body = JSONObject.toJSONString(m);
		String response = Spider.post(url, body);
		System.out.println(response);
		Assert.assertNotNull(response);
	}

	@Test
	public void testPut() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
//		fail("Not yet implemented");
	}

}
