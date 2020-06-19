package cn.freeFly.springcloud.dto;

import cn.freeFly.springcloud.constants.BaseConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String code;
    private String responseMsg;
    private Object data;

    public static BaseResponse success(String resMsg) {
        return success(resMsg, null);
    }

    public static BaseResponse success(Object resData) {
        return success(null, resData);
    }

    public static BaseResponse success(String resMsg, Object resData) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(BaseConstant.success);
        baseResponse.setResponseMsg(resMsg);
        baseResponse.setData(resData);
        return baseResponse;
    }

    public static BaseResponse failed(String resMsg) {
        return failed(resMsg, null);
    }

    public static BaseResponse failed(Object resData) {
        return failed(null, resData);
    }

    public static BaseResponse failed(String resMsg, Object resData) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(BaseConstant.fail);
        baseResponse.setResponseMsg(resMsg);
        baseResponse.setData(resData);
        return baseResponse;
    }
}
