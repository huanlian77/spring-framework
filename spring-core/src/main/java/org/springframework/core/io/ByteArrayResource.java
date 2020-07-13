/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.io;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * 字节数组类型的Resource，继承AbstractResource
 */
public class ByteArrayResource extends AbstractResource {

	private final byte[] byteArray;

	private final String description;


	public ByteArrayResource(byte[] byteArray) {
		this(byteArray, "resource loaded from byte array");
	}


	public ByteArrayResource(byte[] byteArray, @Nullable String description) {
		Assert.notNull(byteArray, "Byte array must not be null");
		this.byteArray = byteArray;
		this.description = (description != null ? description : "");
	}


	/**
	 * 获取字节数组
	 * @return
	 */
	public final byte[] getByteArray() {
		return this.byteArray;
	}


	@Override
	public boolean exists() {
		return true;
	}


	@Override
	public long contentLength() {
		return this.byteArray.length;
	}

	/**
	 * 通过ByteArrayInputStream类把字节数组转InputStream
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(this.byteArray);
	}

	@Override
	public String getDescription() {
		return "Byte array resource [" + this.description + "]";
	}

	@Override
	public boolean equals(@Nullable Object other) {
		return (this == other || (other instanceof ByteArrayResource &&
				Arrays.equals(((ByteArrayResource) other).byteArray, this.byteArray)));
	}

	@Override
	public int hashCode() {
		return (byte[].class.hashCode() * 29 * this.byteArray.length);
	}

}
