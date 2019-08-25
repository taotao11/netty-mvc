package netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyHttpServer {
	
	private static final Logger log = LoggerFactory.getLogger(NettyHttpServer.class);
	private int port;
	private DispatcherServlet servlet;
	
	public NettyHttpServer(int port) {
		super();
		this.port = port;
	}

	public NettyHttpServer(int port, DispatcherServlet servlet) {
		super();
		this.port = port;
		this.servlet = servlet;
	}
	
	public void start() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
											.childHandler(new HttpServerInitializer(servlet))
											.option(ChannelOption.SO_BACKLOG, 128)
											.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			System.out.println("NettyHttpServer Run successfully");
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
											
		} catch(Exception e) {
			e.printStackTrace();
			log.error("NettySever start fail", e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	 
	
}
