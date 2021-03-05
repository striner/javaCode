/**
 * @author striner
 * @create 2018/11/29 19:37
 */
package common.Exception;

import common.enumeration.errorMessageEnum.ErrorMessage;

public class IncorrectFormatException extends Exception {

    /**
     * 错误编码
     */
    private static int errorCode = -100;
    private static String message = ErrorMessage.FORMAT_INCORRECT_ERROR.getErrorInfo();

    /**
     * 构造一个基本异常.
     */
    public IncorrectFormatException() {
        super(message);
    }

    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     */
    public IncorrectFormatException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message 信息描述
     */
    public IncorrectFormatException(int errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
