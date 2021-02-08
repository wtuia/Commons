package framework.netty.netty2socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

public class LenDecoder extends ByteToMessageDecoder {
	
	private final int maxFrameLength;
	private final int titleLen;
	
	public LenDecoder(int titleLen, int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
		this.titleLen = titleLen;
	}
	
	@Override
	protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		Object decoded = decode(ctx, in);
		if (decoded != null) {
			out.add(decoded);
		}
	}
	
	public Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
		buffer.readRetainedSlice(titleLen);
		int minFrameLength = Integer.MAX_VALUE;
		if (minFrameLength > maxFrameLength) {
			buffer.skipBytes(minFrameLength);
			fail(minFrameLength);
			return null;
		}
		return buffer.readRetainedSlice(minFrameLength);
	}
	
	private void fail(long frameLength) {
		if (frameLength > 0) {
			throw new TooLongFrameException(
					"frame length exceeds " + maxFrameLength +
							": " + frameLength + " - discarded");
		} else {
			throw new TooLongFrameException(
					"frame length exceeds " + maxFrameLength +
							" - discarding");
		}
	}
}
