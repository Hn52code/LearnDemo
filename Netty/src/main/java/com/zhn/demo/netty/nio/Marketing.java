package com.zhn.demo.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.GatheringByteChannel;

/**
 * 
 * @author Administrator gather/scatter 组合多个buffer / 拆分成多个 缓冲区
 */
public class Marketing {

	private static final String DEMOGRAPHI = "blahblah.txt";

	public static void main(String[] args) throws IOException {
		int reps = 10;
		if (args.length > 0) {
			reps = Integer.parseInt(args[0]);
			FileOutputStream fos = new FileOutputStream(DEMOGRAPHI);

			GatheringByteChannel gather = fos.getChannel();
			// ByteBuffer[] bs=utt

		}
	}
}
