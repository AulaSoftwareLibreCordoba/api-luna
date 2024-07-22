package software_libre.api_luna.exceptions;

public abstract class BaseException extends RuntimeException{
    private int code;
    private String description;

    public BaseException(){}

    public BaseException(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        if(this.code==0)
            return -1;//TODO:Sustituir por constante

        return this.code;
    }

    public String getDescription(){
        if(this.description== null || this.description.isBlank())
            return "Void";//TODO:Cambiar por constante

        return this.description;
    }
}
