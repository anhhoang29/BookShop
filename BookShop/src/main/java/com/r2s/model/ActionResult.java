package com.r2s.model;

import com.r2s.enums.ErrorCodeEnum;

import lombok.Data;

@Data
public class ActionResult {
	private ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.OK;
	private Object data;
//	private String message;
//	private String token;
//	private String refreshToken;
//	private String error;
	private boolean isSuccess;

	public void setSuccess(boolean b) {
		if (isSuccess) {
			this.errorCodeEnum = ErrorCodeEnum.OK;
		} else {
			this.errorCodeEnum = ErrorCodeEnum.INTERNAL_SERVER_ERROR;
		}
	}

	public void setMessage(String message) {
		this.errorCodeEnum.setMessage(message);
	}
}