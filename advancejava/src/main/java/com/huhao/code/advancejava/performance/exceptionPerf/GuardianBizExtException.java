package com.huhao.code.advancejava.performance.exceptionPerf;

/**
 * Created by huhao on 16/10/14.
 */
public class GuardianBizExtException extends RuntimeException {
    public GuardianBizExtException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public GuardianBizExtException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public GuardianBizExtException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public GuardianBizExtException(Throwable cause) {
        super(cause);
    }

    @Override
    public Throwable fillInStackTrace(){
        return this;
    }
}
