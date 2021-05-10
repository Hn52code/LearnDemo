package com.zhn.demo.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpNettyServer {

    /* *
     * 1.UDP协议是由首部和数据组成。
     * 2.首部部分很简单，只有8个字节，由四个字段组成，每个字段都是两个字节。各个字段意义分别是：
     * a.源端口号，这是在源主机上运行的进程所使用的端口号，如果源主机是客户端（发起请求的一方），则在大多数情况下这个端口号是临时端口号，
     *   如果源主机是服务器端（发送响应时），则在大多数情况下这个端口号时熟知端口号。
     * b.目的端口号，这是在目的主机上运行的进程所使用的端口号，如果目的主机是客户端（发起请求的一方），则在大多数情况下这个端口号是临时端口号，
     *   服务器需要将这个临时端口号复制下来，如果目的主机是服务器端（发送响应时），则在大多数情况下这个端口号时熟知端口号。
     * c.长度，定义了用户数据报的总长度，首部加上数据，数据部分的长度范围时0～65507。
     * d.检验和，这个字段用来检验这个用户数据报（首部加上数据）出现的差错。
     * 3.伪首部:
     * a.伪首部是用来校验的，它必须和首部中的校验和结合起来使用。
     * b.在计算检验和时临时加上去的，伪首部既不向下传送也不向上提交，而仅仅时为了计算检验和
     * c.在计算检验和时，需要在UDP用户数据报之前增加12个字节的伪首部。这个伪首部并不是UDP真正的首部，这是在计算检验和时临时和UDP用户数据报连接在一起，
     *   得到一个过渡的UDP用户数据报，检验和就是按照这个过渡的UDP用户数据报来计算的。伪首部既不向下传送也不向上提交，而仅仅时为了计算检验和。
     *   UDP计算检验和的方法和计算IP数据报首部检验和的方法相似，不同的是，IP数据报的检验和只是检验IP数据报的首部，但是UDP的检验和是将首部和数据部分一起都检验。
     */

    /**
     * 1.UDP传送数据前并不与对方建立连接，即UDP是无连接的。
     * 2.UDP接收到的数据报不发送确认信号，发送端不知道数据是否被正确接收
     * 3.UDP传送数据比TCP快，系统开销也少;
     */

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            //由于我们用的是UDP协议，所以要用NioDatagramChannel来创建
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)//支持广播
                    .handler(new UdpMsgHandler());//ChineseProverbServerHandler是业务处理类
//            ChannelFuture channelFuture =b.connect("192.168.0.66",8888).sync();
            ChannelFuture channelFuture = b.bind(8888).sync();
            System.out.println(channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

}