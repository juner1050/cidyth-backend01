package com.hyzs.cidyth.core.exception;

import com.hyzs.psd.gafa.exception.BizException;
/**
 * 服务异常
 * @author jidw
 *
 */
public class ServiceException extends BizException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1338518212525147756L;
	public ServiceException(String message) {
		super(null);
		super.setErrCode(super.getErrCode());
		super.setMessage(message);
	}
	public ServiceException(String code, String message) {
		super(null);
		super.setErrCode(code);
		super.setMessage(message);
	}
	public ServiceException(String message,Throwable cause){
		super(message,cause);
	}
}
