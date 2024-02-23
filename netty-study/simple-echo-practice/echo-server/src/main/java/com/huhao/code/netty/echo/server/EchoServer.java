package com.huhao.code.netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * EchoServerHandler的引导类
 * @author: huhao
 * @create: 2024/2/5
 */
public class EchoServer {

    private final int port;


    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if(args.length != 1){
            System.err.println(
                    "Usage: "+EchoServer.class.getSimpleName()+" <port>"
            );
        }

        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();

    }

    public void start()throws Exception{
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception{
                            socketChannel.pipeline().addLast(echoServerHandler);
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
