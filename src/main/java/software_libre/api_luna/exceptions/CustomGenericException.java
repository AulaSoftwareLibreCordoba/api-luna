package software_libre.api_luna.exceptions;

public class CustomGenericException extends BaseException{

    public CustomGenericException(int code, String description) {
        super(code, description);
    }
}
