package com.hyzs.cidyth.common.utils;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * http 客户端工具
 * 
 * @author Administrator
 *
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	public static final String JSON_MIME = new String("application/json");
	public static final String PLAINTXT_MIME = new String("text/plain");
	public static final String DEFAULT_MIME = new String("application/octet-stream");

	/**
	 * get 请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String get(String url) throws IOException {
		return this.get(url, null, null, null, null);
	}

	/**
	 * get 请求
	 * 
	 * @param url
	 *            url
	 * @param requestParameter
	 *            请求参数
	 * @return
	 * @throws IOException
	 */
	public String get(String url, Map<String, String> requestParameter) throws IOException {
		return this.get(url, requestParameter, null, null, null);
	}

	/**
	 * get 请求
	 * 
	 * @param url
	 *            url
	 * @param requestParameter
	 *            请求参数
	 * @param httpHeader
	 *            http头
	 * @return
	 * @throws IOException
	 */
	public String get(String url, Map<String, String> requestParameter, Map<String, String> httpHeader)
			throws IOException {
		return this.get(url, requestParameter, httpHeader, null, null);
	}

	/**
	 * get 请求
	 * 
	 * @param url
	 *            url
	 * @param requestParameter
	 *            请求参数
	 * @param httpHeader
	 *            http头
	 * @param proxy
	 *            代理
	 * @param proxySelector
	 *            代理选择器
	 * @return
	 * @throws IOException
	 */
	public String get(String url, Map<String, String> requestParameter, Map<String, String> httpHeader, Proxy proxy,
			ProxySelector proxySelector) throws IOException {
		String result = null;
		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setReadTimeout(60, TimeUnit.SECONDS);
		httpClient.setWriteTimeout(30, TimeUnit.SECONDS);
		httpClient.setConnectTimeout(30, TimeUnit.SECONDS);
		if (proxy != null) {
			httpClient.setProxy(proxy);
		}
		if (proxySelector != null) {
			httpClient.setProxySelector(proxySelector);
		}
		Request.Builder builer = new Request.Builder();
		url = this.reBuildUrl(url, requestParameter);
		if (httpHeader != null && !httpHeader.isEmpty()) {
			ImmutableMap<String, String> map = ImmutableMap.copyOf(httpHeader);
			Iterator<Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				builer.addHeader(entry.getKey(), entry.getValue());
			}
		}
		builer.url(url);
		logger.info("HTTP-Method: GET\tURL: " + url);
		Response response = httpClient.newCall(builer.build()).execute();
		if (response.isSuccessful()) {
			result = response.body().string();
			logger.debug(result);
		} else {
			throw new IOException("code:" + response.code() + ",message:" + response.message());
		}
		return result;
	}

	private String reBuildUrl(String url, Map<String, String> requestParameter) {
		String finalUrl = null;
		StringBuilder strb = new StringBuilder(url != null ? url : "");
		if (requestParameter != null && !requestParameter.isEmpty()) {
			strb.append("?");
			ImmutableMap<String, String> map = ImmutableMap.copyOf(requestParameter);
			Iterator<Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				strb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
			finalUrl = strb.substring(0, strb.length() - 1);
		} else {
			finalUrl = strb.toString();
		}
		return finalUrl;
	}

	/**
	 * post 请求,发送字节内容
	 * 
	 * @param url
	 *            url
	 * @param content
	 *            字节内容
	 * @param mimeType
	 *            MIME类型,例如:"application/octet-stream"等.
	 * @param charset
	 *            编码集
	 * @param httpHeader
	 *            请求头
	 * @param proxy
	 *            代理
	 * @param proxySelector
	 *            代理选择器
	 * @return
	 * @throws IOException
	 */
	public Response postByte(String url, byte[] content, String mimeType, Charset charset,
			Map<String, String> httpHeader, Proxy proxy, ProxySelector proxySelector) throws IOException {
		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setReadTimeout(60, TimeUnit.SECONDS);
		httpClient.setWriteTimeout(30, TimeUnit.SECONDS);
		httpClient.setConnectTimeout(60, TimeUnit.SECONDS);
		if (proxy != null) {
			httpClient.setProxy(proxy);
		}
		if (proxySelector != null) {
			httpClient.setProxySelector(proxySelector);
		}
		Request.Builder builer = new Request.Builder();
		if (httpHeader != null && !httpHeader.isEmpty()) {
			ImmutableMap<String, String> map = ImmutableMap.copyOf(httpHeader);
			Iterator<Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				builer.addHeader(entry.getKey(), entry.getValue());
			}
		}
		logger.info("HTTP-Method: POST\tURL: " + url);
		RequestBody requestBody = null;
		MediaType mediaType = null;
		if (mimeType != null && mimeType.length() > 0) {
			mediaType = MediaType.parse(mimeType);
		}
		if (mimeType == null) {
			mediaType = MediaType.parse(DEFAULT_MIME);
		}
		if (charset != null) {
			mediaType.charset(charset);
		}
		if (content == null) {
			content = new byte[] {};
		}
		requestBody = RequestBody.create(mediaType, content);
		Response response = httpClient.newCall(builer.post(requestBody).url(url).build()).execute();
		return response;
	}

	public Response postByte(String url, byte[] content, String mimeType, Charset charset,
			Map<String, String> httpHeader) throws IOException {
		return this.postByte(url, content, mimeType, charset == null ? Charset.forName("UTF-8") : charset, httpHeader,
				null, null);
	}

	/**
	 * post 请求,发送字节内容
	 * 
	 * @param url
	 *            url
	 * @param content
	 *            字节内容
	 * @return
	 * @throws IOException
	 */
	public Response postByte(String url, byte[] content) throws IOException {
		return this.postByte(url, content, null, null, null, null, null);
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 *            url
	 * @return
	 * @throws IOException
	 */
	public Response postByte(String url) throws IOException {
		return this.postByte(url, null, null, null, null, null, null);
	}

	/**
	 * post 请求,发送字符内容
	 * 
	 * @param url
	 *            url
	 * @param content
	 *            字符内容
	 * @param mimeType
	 *            MIME类型,例如:"application/json"、"text/plain"
	 * @param charset
	 *            编码集
	 * @param httpHeader
	 *            请求头
	 * @param proxy
	 *            代理
	 * @param proxySelector
	 *            代理选择器
	 * @return
	 * @throws IOException
	 */
	public String postText(String url, String content, String mimeType, Charset charset, Map<String, String> httpHeader,
			Proxy proxy, ProxySelector proxySelector) throws IOException {
		String result = null;
		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setReadTimeout(60, TimeUnit.SECONDS);
		httpClient.setWriteTimeout(30, TimeUnit.SECONDS);
		httpClient.setConnectTimeout(30, TimeUnit.SECONDS);
		if (proxy != null) {
			httpClient.setProxy(proxy);
		}
		if (proxySelector != null) {
			httpClient.setProxySelector(proxySelector);
		}
		Request.Builder builer = new Request.Builder();
		if (httpHeader != null && !httpHeader.isEmpty()) {
			ImmutableMap<String, String> map = ImmutableMap.copyOf(httpHeader);
			Iterator<Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				builer.addHeader(entry.getKey(), entry.getValue());
			}
		}
		logger.info("HTTP-Method: POST\tURL: " + url);
		RequestBody requestBody = null;
		MediaType mediaType = null;
		if (mimeType != null && mimeType.length() > 0) {
			mediaType = MediaType.parse(mimeType);
		}
		if (mediaType == null) {
			mediaType = MediaType.parse(PLAINTXT_MIME);
		}
		if (charset != null) {
			mediaType.charset(charset);
		}
		requestBody = RequestBody.create(mediaType, content);
		Response response = httpClient.newCall(builer.post(requestBody).url(url).build()).execute();
		if (response.isSuccessful()) {
			result = response.body().string();
			logger.debug(result);
		} else {
			throw new IOException("code:" + response.code() + ",message:" + response.message());
		}
		return result;

	}

	/**
	 * post 请求,发送字符内容
	 * 
	 * @param url
	 *            url
	 * @param content
	 *            字符内容
	 * @param mimeType
	 *            MIME类型,例如:"application/json"、"text/plain"等.
	 * @param charset
	 *            编码集
	 * @param httpHeader
	 *            请求头
	 * @return
	 * @throws IOException
	 */
	public String postText(String url, String content, String mimeType, Charset charset, Map<String, String> httpHeader)
			throws IOException {
		return this.postText(url, content, mimeType, charset, httpHeader, null, null);
	}

	/**
	 * post 请求,发送字符内容
	 * 
	 * @param url
	 *            url
	 * @param content
	 *            字符内容
	 * @param mimeType
	 *            MIME类型,例如:"application/json"、"text/plain"、"application/octet-stream"等.
	 * @param charset
	 *            编码集
	 * @return
	 * @throws IOException
	 */
	public String postText(String url, String content, Charset charset) throws IOException {
		return this.postText(url, content, PLAINTXT_MIME, charset, null, null, null);
	}

	/**
	 * post 请求,发送字符内容
	 * 
	 * @param url
	 *            url
	 * @param content
	 *            字符内容
	 * @return
	 * @throws IOException
	 */
	public String postText(String url, String content) throws IOException {
		return this.postText(url, content, PLAINTXT_MIME, null, null, null, null);
	}

	/**
	 * post 请求,发送json字符串
	 * 
	 * @param url
	 *            url
	 * @param json
	 *            json字符串
	 * @param httpHeader
	 *            请求头
	 * @param proxy
	 *            代理
	 * @param proxySelector
	 *            代理选择器
	 * @return
	 * @throws IOException
	 */
	public String postJSON(String url, String json, Map<String, String> httpHeader, Proxy proxy,
			ProxySelector proxySelector) throws IOException {
		return postText(url, json, JSON_MIME, null, httpHeader, proxy, proxySelector);
	}

	/**
	 * post 请求,发送json字符串
	 * 
	 * @param url
	 *            url
	 * @param json
	 *            json字符串
	 * @param httpHeader
	 *            请求头
	 * @return
	 * @throws IOException
	 */
	public String postJSON(String url, String json, Map<String, String> httpHeader) throws IOException {
		return postText(url, json, JSON_MIME, null, httpHeader, null, null);
	}

	/**
	 * post 请求,发送json字符串
	 * 
	 * @param url
	 *            url
	 * @param json
	 *            json字符串
	 * @return
	 * @throws IOException
	 */
	public String postJSON(String url, String json) throws IOException {
		return postText(url, json, JSON_MIME, null, null, null, null);
	}
}
