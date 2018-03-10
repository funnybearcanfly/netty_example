package com.funnybear.netty.example.protocol;

import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

public class SslChannelInitializer extends ChannelInitializer<Channel> {
	private final SslContext context;

	public SslChannelInitializer(SslContext context) {
		this.context = context;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		SSLEngine engine = context.newEngine(ch.alloc());
		ch.pipeline().addFirst("ssl", new SslHandler(engine));
	}
}
