package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.springframework.lang.Nullable;

/**
 * 资源接口类
 * <p>
 * Resource用来表示资源
 * ResourceLoader用来加载资源
 */
public interface Resource extends InputStreamSource {

	/**
	 * 资源是否存在
	 */
	boolean exists();

	/**
	 * 资源是否可读
	 */
	default boolean isReadable() {
		return exists();
	}

	/**
	 * 资源代表句柄是否被一个stream打开
	 */
	default boolean isOpen() {
		return false;
	}

	/**
	 * 资源是否为File
	 */
	default boolean isFile() {
		return false;
	}

	/**
	 * 返回资源的URL句柄
	 */
	URL getURL() throws IOException;

	/**
	 * 返回资源的URI句柄
	 */
	URI getURI() throws IOException;

	/**
	 * 返回资源的File
	 */
	File getFile() throws IOException;

	/**
	 * 返回一个ReadableByteChannel
	 */
	default ReadableByteChannel readableChannel() throws IOException {
		return Channels.newChannel(getInputStream());
	}

	/**
	 * 资源内容长度
	 */
	long contentLength() throws IOException;

	/**
	 * 资源最后修改事件
	 */
	long lastModified() throws IOException;

	/**
	 * 根据当前资源路径创建新资源
	 */
	Resource createRelative(String relativePath) throws IOException;

	/**
	 * 资源的名称
	 */
	@Nullable
	String getFilename();

	/**
	 * 资源的描述
	 */
	String getDescription();

}
