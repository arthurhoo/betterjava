package com.huhao.code.advancejava.performance.exceptionPerf;

/**
 * Created by huhao on 16/10/14.
 */
public class GuardianBizException extends RuntimeException {
    public GuardianBizException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public GuardianBizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public GuardianBizException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public GuardianBizException(Throwable cause) {
        super(cause);
    }
}
