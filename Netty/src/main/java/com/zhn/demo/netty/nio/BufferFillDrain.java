package com.zhn.demo.netty.nio;

import java.nio.CharBuffer;

public class BufferFillDrain {

	private static String[] str = { "a random string value", "the prduct f an infinite number of monkeys",
			"hey hey we are the monkees", "help me! hep me!" };
	private static int index = 0;

	private static void drainBuffer(CharBuffer buffer) {
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
	}

	private static boolean fillBuffer(CharBuffer buffer) {
		if (index >= str.length) {
			return false;
		}
		String st = str[index++];
		for (int i = 0; i < st.length(); i++) {
			buffer.put(st.charAt(i));
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		CharBuffer buffer = CharBuffer.allocate(100);
		while (fillBuffer(buffer)) {
			buffer.flip();
			drainBuffer(buffer);
			buffer.clear();
		}
	}

}
