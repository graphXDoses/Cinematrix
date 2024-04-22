package com.texnologia_logismikou.Cinematrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyUtillsTest {

	@Test
	void test() {
		MyUtills m = new MyUtills();
		
		assertEquals(20, m.add(10, 10));
	}

}
