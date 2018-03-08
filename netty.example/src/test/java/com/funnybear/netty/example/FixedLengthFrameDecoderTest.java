package com.funnybear.netty.example;

import com.funnybear.netty.example.decode.FixedLengthFrameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import junit.framework.TestCase;

public class FixedLengthFrameDecoderTest extends TestCase {
	public void testFramesDecoded() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
		// write bytes
		assertTrue(channel.writeInbound(input.retain()));
		assertTrue(channel.finish());
		// read messages
		ByteBuf read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		assertNull(channel.readInbound());
		buf.release();
	}

	public void testFramesDecoded2() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
		assertFalse(channel.writeInbound(input.readBytes(2)));
		assertTrue(channel.writeInbound(input.readBytes(7)));
		assertTrue(channel.finish());
		ByteBuf read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		assertNull(channel.readInbound());
		buf.release();
	}
}
