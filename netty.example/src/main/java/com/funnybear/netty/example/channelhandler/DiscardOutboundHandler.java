package com.funnybear.netty.example.channelhandler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

@Sharable
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter {
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
		ReferenceCountUtil.release(msg);
		promise.setSuccess();
	}
}
