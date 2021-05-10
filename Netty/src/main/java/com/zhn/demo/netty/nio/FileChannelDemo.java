package com.zhn.demo.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("test.txt", "rw");
		FileChannel inChannel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(20);
		int byteread = inChannel.read(buffer);

		while (byteread != -1) {
			System.out.println("读取的：" + byteread);
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.println((char) buffer.get());
			}
			buffer.clear();
			byteread = inChannel.read(buffer);
		}
		buffer.get();
		file.close();
	}

}