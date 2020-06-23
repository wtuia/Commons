package restful.netty_client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class Client {
	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	private final String host;
	private final int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 8888;
		try {
			new Client(host, port).start();
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
						protected void initChannel(SocketChannel socketChannel) {
							socketChannel.pipeline().addLast(new HttpClientCodec())
									.addLast(new HttpObjectAggregator(2048))
									.addLast(new HttpContentDecompressor())
									.addLast(new ClientHandler());
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
