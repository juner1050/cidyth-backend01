package com.hyzs.cidyth.communicate.websocket.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.commons.io.IOUtils;

public class SerializableHelper {
	public static Object deSerialize(byte[] bytes) throws Exception {
		Object result = null;
		if (bytes != null || bytes.length > 0) {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(bis);
				Object o = ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				throw e;
			} finally {
				IOUtils.closeQuietly(ois);
				IOUtils.closeQuietly(bis);
			}
		}
		return result;
	}
}
