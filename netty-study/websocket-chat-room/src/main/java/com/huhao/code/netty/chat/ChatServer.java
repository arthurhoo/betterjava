package com.huhao.code.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * @author: huhao
 * @create: 2024/2/23
 */
public class ChatServer {

    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress inetSocketAddress){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup));
        ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress);
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
        return channelFuture;

    }

    protected ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup){
        return new ChatServerChannelInitializer(channelGroup);
    }

    public void destroy(){
        if(null != channel){
            channel.close();
        }
        channelGroup.close();
        eventLoopGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please give port as argument");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        final ChatServer endPoint = new ChatServer();
        ChannelFuture channelFuture = endPoint.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                endPoint.destroy();
            }
        });
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
