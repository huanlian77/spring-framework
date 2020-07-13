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

import java.io.IOException;
import java.io.InputStream;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * InputStream类型的Resource，继承AbstractResource
 */
public class InputStreamResource extends AbstractResource {

	private final InputStream inputStream;

	private final String description;

	private boolean read = false;


	public InputStreamResource(InputStream inputStream) {
		this(inputStream, "resource loaded through InputStream");
	}


	public InputStreamResource(InputStream inputStream, @Nullable String description) {
		Assert.notNull(inputStream, "InputStream must not be null");
		this.inputStream = inputStream;
		this.description = (description != null ? description : "");
	}


	/**
	 * 如果是InputStream，说明一定存在
	 *
	 * @return
	 */
	@Override
	public boolean exists() {
		return true;
	}

	/**
	 * 如果是InputStream，说明一定打开
	 */
	@Override
	public boolean isOpen() {
		return true;
	}

	/**
	 * 获取流，如果要多次获取流不要使用InputStreamResource类
	 */
	@Override
	public InputStream getInputStream() throws IOException, IllegalStateException {
		if (this.read) {
			throw new IllegalStateException("InputStream has already been read - " +
					"do not use InputStreamResource if a stream needs to be read multiple times");
		}
		this.read = true;
		return this.inputStream;
	}


	@Override
	public String getDescription() {
		return "InputStream resource [" + this.description + "]";
	}


	@Override
	public boolean equals(@Nullable Object other) {
		return (this == other || (other instanceof InputStreamResource &&
				((InputStreamResource) other).inputStream.equals(this.inputStream)));
	}


	@Override
	public int hashCode() {
		return this.inputStream.hashCode();
	}

}
