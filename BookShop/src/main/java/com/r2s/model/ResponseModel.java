package com.r2s.model;

import org.springframework.http.HttpStatus;

import com.r2s.enums.ErrorCodeEnum;

import lombok.Data;

@Data
public class ResponseModel {
	private HttpStatus status;
	private int code;
	private String message;
	private final long time = System.currentTimeMillis();
	private Object data;

	public ResponseModel() {
		this(ErrorCodeEnum.OK);
	}

	public ResponseModel(final ErrorCodeEnum returnCode) {
		this.status = HttpStatus.valueOf(returnCode.getStatus());
		this.code = returnCode.getCode();
		this.message = returnCode.getMessage();
	}

	public ResponseModel(Object data) {
		this(ErrorCodeEnum.OK);
		this.data = data;
	}

    public ResponseModel(ErrorCodeEnum errorCode, Object o) {
		this.status = HttpStatus.valueOf(errorCode.getStatus());
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.data = o;
    }
}
