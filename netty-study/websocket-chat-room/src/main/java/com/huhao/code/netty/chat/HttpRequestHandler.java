package com.huhao.code.netty.chat;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author: huhao
 * @create: 2024/2/22
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;

    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        try{
            String path = location.toURI()+"index.html";
            path = !path.contains("file:")? path:path.substring(5);
            INDEX = new File(path);

        }catch (URISyntaxException uriSyntaxException){
            throw new IllegalStateException(
                    "Unable to locate index.html", uriSyntaxException);
        }
    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }



    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        if(wsUri.equals(request.uri())){
            ctx.fireChannelRead(request.retain());
        }else{
            if(HttpUtil.is100ContinueExpected(request)){
                send100Continue(ctx);
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(INDEX,"r");
            HttpResponse httpResponse = new DefaultHttpResponse(request.protocolVersion(),HttpResponseStatus.OK);
            httpResponse.headers().set(HttpHeaders.Names.CONTENT_TYPE,
                    "text/plain; charset=UTF-8");
            boolean isKeepAlive = HttpUtil.isKeepAlive(request);
            if(isKeepAlive){
                httpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, randomAccessFile.length());
                httpResponse.headers().set(HttpHeaders.Names.CONNECTION,
                        HttpHeaders.Values.KEEP_ALIVE);
            }

            ctx.write(httpResponse);
            if(ctx.pipeline().get(SslHandler.class) == null){
                ctx.write(new DefaultFileRegion(randomAccessFile.getChannel(),0,randomAccessFile.length()));
            }else{
                ctx.write(new ChunkedNioFile(randomAccessFile.getChannel()));
            }
            ChannelFuture channelFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if(!isKeepAlive){
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }

    }

    private static void send100Continue(ChannelHandlerContext ctx){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception{
        cause.printStackTrace();
        ctx.close();
    }
}
