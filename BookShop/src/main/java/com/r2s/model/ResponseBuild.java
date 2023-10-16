package com.r2s.model;

import org.springframework.stereotype.Component;

import com.r2s.enums.ErrorCodeEnum;

@Component
public class ResponseBuild {

	public ResponseModel build(ActionResult actionResult) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setMessage(actionResult.getErrorCodeEnum().getMessage());
		responseModel.setCode(actionResult.getErrorCodeEnum().getCode());
		responseModel.setData(actionResult.getData());
		return responseModel;
	}

}
