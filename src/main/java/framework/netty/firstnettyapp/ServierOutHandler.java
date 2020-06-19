package framework.netty.firstnettyapp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServierOutHandler extends ChannelOutboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ServierOutHandler.class);

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
		logger.info("out write");
	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		super.read(ctx);
		logger.info("out read");
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.warn("service out Handler 被载入pipeline");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.warn("service out Handler 从 pipeline卸载");
	}
}
