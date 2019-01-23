package com.hyzs.cidyth.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * 跨线程上下文工具类
 * 
 * @author jidw
 *
 */
public class ThreadLocalUtil {
	static final ThreadLocalUtil self = new ThreadLocalUtil();
	Object tlm;
	private Method removeMethod;

	private ThreadLocalUtil() {
		this.tlm = new ThreadLocalMap();
		try {
			this.removeMethod = ThreadLocal.class.getMethod("remove", null);
		} catch (NoSuchMethodException e) {
		}
	}

	public static void put(String key, Object o) {
		if (self != null)
			self.put0(key, o);
	}

	public static Object get(String key) {
		if (self != null) {
			return self.get0(key);
		}
		return null;
	}

	public static void remove(String key) {
		if (self != null)
			self.remove0(key);
	}

	public static Hashtable getContext() {
		if (self != null) {
			return self.getContext0();
		}
		return null;
	}

	public static void clear() {
		if (self != null)
			self.clear0();
	}

	private void put0(String key, Object o) {
		if (this.tlm == null) {
			return;
		}
		Hashtable ht = (Hashtable) ((ThreadLocalMap) this.tlm).get();
		if (ht == null) {
			ht = new Hashtable(7);
			((ThreadLocalMap) this.tlm).set(ht);
		}
		ht.put(key, o);
	}

	private Object get0(String key) {
		if (this.tlm == null) {
			return null;
		}
		Hashtable ht = (Hashtable) ((ThreadLocalMap) this.tlm).get();
		if ((ht != null) && (key != null)) {
			return ht.get(key);
		}
		return null;
	}

	private void remove0(String key) {
		if (this.tlm != null) {
			Hashtable ht = (Hashtable) ((ThreadLocalMap) this.tlm).get();
			if (ht != null) {
				ht.remove(key);

				if (ht.isEmpty())
					clear0();
			}
		}
	}

	private Hashtable getContext0() {
		if (this.tlm == null) {
			return null;
		}
		return (Hashtable) ((ThreadLocalMap) this.tlm).get();
	}

	private void clear0() {
		if (this.tlm != null) {
			Hashtable ht = (Hashtable) ((ThreadLocalMap) this.tlm).get();
			if (ht != null) {
				ht.clear();
			}
			if (this.removeMethod == null)
				return;
			try {
				this.removeMethod.invoke(this.tlm, null);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
	}
}

final class ThreadLocalMap extends InheritableThreadLocal {
	public final Object childValue(Object parentValue) {
		Hashtable ht = (Hashtable) parentValue;
		if (ht != null) {
			return ht.clone();
		}
		return null;
	}
}