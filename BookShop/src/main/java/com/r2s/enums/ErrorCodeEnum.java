package com.r2s.enums;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodeEnum {
	OK(200, 200, "OK"),


	NO_CONTENT(204, 20401, "Not have data"),
	BAD_REQUEST(400, 40000, "Bad request"),
	INVALID_USER(400, 40001, "User not exist"),
	INVALID_CREATE_USER(400, 40002, "Create user not success"),
	INVALID_UPDATE_USER(400, 40003, "Update user not success"),
	INVALID_DELETE(400, 40004, "Delete not success, entity is used"),
	INTERNAL_SERVER_ERROR(500, 50000, "Internal Server Error"),
	FORBIDDEN(403, 403, "Không có quyền"),
	UNAUTHORIZED(401, 401, "Chưa xác thực"),;

	private static Map<Integer, ErrorCodeEnum> map = new HashMap<>();

	static {
		for (ErrorCodeEnum returnCode : ErrorCodeEnum.values()) {
			map.put(returnCode.code, returnCode);
		}
	}

	private int status;
	private int code;
	private String message;

	private ErrorCodeEnum(int status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public static ErrorCodeEnum valueOf(int code) {
		return map.get(code);
	}

	public int getStatus() {
		return status;
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.valueOf(status);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}