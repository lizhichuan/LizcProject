package com.njcool.lzccommon.network.http.basis.exception.base;


import com.njcool.lzccommon.network.http.basis.config.HttpCode;

/**
 * 作者：leavesC
 * 时间：2018/10/25 21:31
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
public class BaseException extends RuntimeException {

    private int errorCode = HttpCode.CODE_UNKNOWN;

    public BaseException() {
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}