/*
 * Copyright 2013-2018 Lilinfeng.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phei.netty.protocol.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author Administrator
 * @version 1.0
 * @date 2014年3月9日
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("Not a file : " + file + System.lineSeparator());
                return;
            }
            ctx.write(file + " " + file.length() + System.lineSeparator());
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(msg, "r")) {
                FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
                ctx.write(region);
                ctx.writeAndFlush(System.lineSeparator());
            }
        } else {
            ctx.writeAndFlush("File not found: " + file + System.lineSeparator());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
