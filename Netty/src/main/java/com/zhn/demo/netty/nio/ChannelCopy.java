package com.zhn.demo.netty.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 
 * @author Administrator
 *  channel copy
 */
public class ChannelCopy {

	public static void main(String[] args) throws IOException {
		ReadableByteChannel source = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);

		copyChannel_1(source, dest);
//		copyChannel_2(source, dest);

		source.close();
		dest.close();
	}

	public static void copyChannel_1(ReadableByteChannel readhannel, WritableByteChannel writeCahnnel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		while (readhannel.read(buffer) != -1) {
			buffer.flip();
			writeCahnnel.write(buffer);
			buffer.compact();
		}
		//下面部分是为了确认是否还有没被读取的内容？？？？
		buffer.flip();
		while (buffer.hasRemaining()) {
			writeCahnnel.write(buffer);
		}
	}

	public static void copyChannel_2(ReadableByteChannel readhannel, WritableByteChannel writeCahnnel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
		while (readhannel.read(buffer) != -1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				writeCahnnel.write(buffer);
			}
			buffer.clear();
		}
	}
}
