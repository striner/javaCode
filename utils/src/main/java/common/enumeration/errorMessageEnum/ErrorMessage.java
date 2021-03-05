package common.enumeration.errorMessageEnum;

public enum ErrorMessage {

    PARAMETER_EMPTY_ERROR("The parameter is empty!"),
    FORMAT_INCORRECT_ERROR("The format of the parameter is incorrect!"),
    FORMAT_INCORRECT_IllEGAL("The format of the parameter is illegal!"),

    PATAMETER_LENGTH_TOO_LARGE("The parameter length is larger than the length of the original string, so it returns the original string."),
    PATAMETER_NUMBER_TOO_LARGE("The parameter number is larger than the number of characters in the string, so all characters are returned."),

    IOFILE_CLOSE_ERROR("IOFile close error!"),
    GET_FILE_TYPE_ERROR("Get file type error!"),
    PATH_OR_DIR_IllRGAL("SrcPath or destDir is illegal!"),
    MKDIRS_FAILED("Make directory failed!"),
    GET_PROPERTIES_FILE_FAILED("Failed to get the properties file"),

    ENCRYPTION_FAILED("Encryption failed!"),
    DECRYPTION_FAILED("Decryption failed!"),

    VERIFY_SIGNATURE_FAIL("Verify signature fall!"),
    GET_RSAKEYPAIR_FAILED("Get RSAKeyPair failed!"),
    KEY_PAIR_GEN_IS_NULL("KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance."),
    INVALID_PARAMETER("The parameter is invalid!"),
    NO_SUCH_ALGORITHM("No such algorithm!");


    private String errorInfo;

    ErrorMessage(String errorInfo){
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
