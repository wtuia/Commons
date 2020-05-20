package framework.netty.firstnettyapp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	/**
	 * 处理任务的方法
	 * @param ctx 事件上下文
	 * @param msg
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		logger.info("Server received: " + in.toString(CharsetUtil.UTF_8));
	}

	/**
	 * 完成任务时的回调
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("server Netty rocks!", CharsetUtil.UTF_8))
				.addListener(ChannelFutureListener.CLOSE);
	}

	/**
	 * 异常处理
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("", cause.getCause());
		ctx.close();
	}
}
