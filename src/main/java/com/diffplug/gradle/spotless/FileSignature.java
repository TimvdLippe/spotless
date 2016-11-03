/*
 * Copyright 2016 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.gradle.spotless;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.diffplug.common.collect.ImmutableSortedSet;

/** Computes a signature for any needed files. */
public class FileSignature implements Serializable {
	private static final long serialVersionUID = 1L;

	final String[] filenames;
	final long[] filesizes;
	final long[] lastModified;

	public FileSignature(Iterable<File> files) throws IOException {
		ImmutableSortedSet<File> sorted = ImmutableSortedSet.copyOf(files);

		filenames = new String[sorted.size()];
		filesizes = new long[sorted.size()];
		lastModified = new long[sorted.size()];

		int i = 0;
		for (File file : sorted) {
			filenames[i] = file.getCanonicalPath();
			filesizes[i] = file.length();
			lastModified[i] = file.lastModified();
			++i;
		}
	}
}
