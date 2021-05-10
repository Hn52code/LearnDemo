package com.zhn.demo.netty.netty4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

@Component
//@ChannelHandler.Sharable
public class IoTServerMessageHandler extends ChannelInboundHandlerAdapter {

    private int awakeIdle;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        /* 判断事件是否为闲置事件*/
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            // 处理读取事件闲置
            if (event.state() == IdleState.READER_IDLE) {
                // 第三次闲置后切断连接
                if (awakeIdle > 2) {
                    ctx.close();
                    return;
                }
                ctx.writeAndFlush("12345678");
                awakeIdle++;
            }
        }
    }
}
