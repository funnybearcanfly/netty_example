package com.funnybear.netty.example.channelhandler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class WriteHandler extends ChannelHandlerAdapter {
	private ChannelHandlerContext ctx;

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		// Caching a ChannelHandlerContext
		this.ctx = ctx;
	}

	public void send(String msg) {
		ctx.writeAndFlush(msg);
	}
}