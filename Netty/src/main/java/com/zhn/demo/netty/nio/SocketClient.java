package com.zhn.demo.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient.client();
	}

	public static void client() {
		ByteBuffer buffer = ByteBuffer.allocate(128);
		SocketChannel channel = null;
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("211.149.163.71", 8161));
			if (channel.finishConnect()) {
				int i = 0;
				while (true) {
					TimeUnit.SECONDS.sleep(2);
					String info = "I'm" + i++ + "the information from client";
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					while (buffer.hasRemaining()) {
						System.out.println(buffer);
						channel.write(buffer);
						buffer.compact();
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			System.err.println("socket error");
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					System.err.println("socket Close error");
				}
			}
		}
	}

	public void test1() throws IOException {

		InetSocketAddress address1 = new InetSocketAddress("192.168.0.18", 7777);
		InetSocketAddress address2 = new InetSocketAddress(7777);

		SocketChannel sc = SocketChannel.open();
		sc.connect(address1);
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(address2);

		DatagramChannel dc = DatagramChannel.open();
		
		RandomAccessFile file = new RandomAccessFile("test.txt", "wr");
		FileChannel fc = file.getChannel();

	
		
	}

}
