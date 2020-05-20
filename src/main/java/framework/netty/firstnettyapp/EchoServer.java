package framework.netty.firstnettyapp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class EchoServer {
	private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		int port = 9999;
		EchoServer server = new EchoServer(port);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void start() throws Exception {
		final ServerHandler handler = new ServerHandler();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(handler);
						}
					});
			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();
		}catch (Exception e){
			logger.error("", e);
		}finally {
			group.shutdownGracefully().sync();
		}

	}
}
