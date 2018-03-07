package com.funnybear.netty.example.channelhandler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
		promise.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture f) {
				if (!f.isSuccess()) {
					f.cause().printStackTrace();
					f.channel().close();
				}
			}
		});
	}
}