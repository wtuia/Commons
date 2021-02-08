package framework.netty.netty2socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	private int i = 0;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	/**
	 * 接收到服务器返回是调用
	 *
	 * by P22
	 * 由服务器发送的消息可能会被分块接收。 也就是说，如果服务器发送了 5 字节， 那么不
	 * 能保证这 5 字节会被一次性接收。 即使是对于这么少量的数据， channelRead0()方法也可能
	 * 会被调用两次，第一次使用一个持有 3 字节的 ByteBuf（ Netty 的字节容器），第二次使用一个
	 * 持有 2 字节的 ByteBuf。作为一个面向流的协议， TCP 保证了字节数组将会按照服务器发送它
	 * 们的顺序被接收
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		logger.info("Client received: " + in.toString(CharsetUtil.UTF_8));
		ctx.writeAndFlush("hello world" + ++i);
		//channelHandlerContext.close();
	}
	
	/**
	 * 发生异常是调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		logger.error("", cause.getCause());
		ctx.close();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("发送消息");
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.warn("client handler 被添加到 pipeline中");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.warn("client handler 从 pipeline中移除");
	}
	
}
