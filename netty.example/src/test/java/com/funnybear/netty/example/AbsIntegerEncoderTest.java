package com.funnybear.netty.example;

import com.funnybear.netty.example.coder.AbsIntegerEncoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import junit.framework.TestCase;

public class AbsIntegerEncoderTest extends TestCase {
	public void testEncoded() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 1; i < 10; i++) {
			buf.writeInt(i * -1);
		}
		EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
		assertTrue(channel.writeOutbound(buf));
		assertTrue(channel.finish());
		// read bytes
		for (int i = 1; i < 10; i++) {
			assertEquals(i, channel.readOutbound());
		}
		assertNull(channel.readOutbound());
	}
}
