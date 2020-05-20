package framework.netty.firstnettyapp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class EchoClient {
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);
	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 9999;
		try {
			new EchoClient(host, port).start();
		} catch (Exception e) {
			logger.error("",e);
		}
	}

	public void start() throws  Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(host,port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new ClientHandler());
						}
					});
			ChannelFuture future = b.connect().sync();
			future.channel().closeFuture().sync();
		}catch (Exception e) {
			logger.error("", e);
		}finally {
			group.shutdownGracefully().sync();
		}
	}
}
