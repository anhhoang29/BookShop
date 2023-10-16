package com.r2s.model;

import com.r2s.enums.ErrorCodeEnum;

import lombok.Data;

@Data
public class ActionResult {
	private ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.OK;
	private Object data;

}