package com.funnybear.netty.example.bytebuf;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteOperation {

	public static void main(String[] args) {
		slice();
		copy();
	}

	private static void slice() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
		ByteBuf sliced = buf.slice(0, 14);
		System.out.println(sliced.toString(utf8));
		buf.setByte(0, (byte) 'J');
		assert buf.getByte(0) == sliced.getByte(0);
	}

	private static void copy() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
		ByteBuf copy = buf.copy(0, 14);
		System.out.println(copy.toString(utf8));
		buf.setByte(0, (byte) 'J');
		assert buf.getByte(0) != copy.getByte(0);
	}

	private static void getSet() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
		System.out.println((char) buf.getByte(0));
		int readerIndex = buf.readerIndex();
		int writerIndex = buf.writerIndex();
		buf.setByte(0, (byte) 'B');
		System.out.println((char) buf.getByte(0));
		assert readerIndex == buf.readerIndex();
		assert writerIndex == buf.writerIndex();
	}

	private static void readWrite() {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
		System.out.println((char) buf.readByte());
		int readerIndex = buf.readerIndex();
		int writerIndex = buf.writerIndex();
		buf.writeByte((byte) '?');
		assert readerIndex == buf.readerIndex();
		assert writerIndex != buf.writerIndex();
	}
}
