package software_libre.api_luna.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse{
    private int code;
    private String description;

    public ExceptionResponse(int code, String description){
        this.code = code;
        this.description = description;
    }
}
