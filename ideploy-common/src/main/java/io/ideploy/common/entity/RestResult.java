package io.ideploy.common.entity;

import io.ideploy.common.constants.ApiCode;

/***
 * @author code4china
 * @param <T>
 */
public class RestResult<T> {

	/** 返回代码 */
	protected int code;

	/** 错误信息 */
	protected String message;

	/** 返回对象 */
	protected T object;

	/** 是否成功，readonly */
	protected boolean success;

	@Deprecated
	public RestResult() {
	}

	public RestResult(T object) {
		this(ApiCode.SUCCESS, "success", object);
	}

	public RestResult(int code, String message) {
		setCode(code);
		setMessage(message);
	}

	public RestResult(int code, String message, T object) {
		setCode(code);
		setMessage(message);
		setObject(object);
	}

	// private func

	private void setCode(int code) {
		this.code = code;
		this.success = (code == ApiCode.SUCCESS);
	}

	private void setMessage(String message) {
		this.message = message;
	}

	private void setObject(T object) {
		this.object = object;
	}

	// get func

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getObject() {
		return object;
	}

	public boolean isSuccess() {
		return success;
	}

}
