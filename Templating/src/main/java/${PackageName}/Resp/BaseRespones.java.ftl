package ${PackageName}.Resp;


import lombok.Data;

import java.util.Date;


@Data
public class BaseRespones<T> {
    private int code;//status code
    private String message;//message
    private String date;//timestamp
    private T data;//detail data

    public BaseRespones(int code, String message, String date, T data) {
        this.code = code;
        this.message = message;
        this.date = date;
        this.data = data;
    }

    public static <T>  BaseRespones<T> success(){
        return new BaseRespones<>(200,"success visited",new Date(System.currentTimeMillis()).toString(),null);
    }
    public static <T>  BaseRespones <T> success(T data){
        return new BaseRespones<>(200,"success visited",new Date(System.currentTimeMillis()).toString(),data);
    }
    public static <T> BaseRespones<T> success(String message){
        return new BaseRespones<>(200,message,new Date(System.currentTimeMillis()).toString(),null);
    }
    public static <T> BaseRespones<T> success(String message,T date){
        return new BaseRespones<>(200,message,new Date(System.currentTimeMillis()).toString(),date);
    }

    public static <T> BaseRespones<T> failed(String message){
        return new BaseRespones<>(400,message,new Date(System.currentTimeMillis()).toString(),null);
    }
    public static <T extends Exception> BaseRespones<T> failed(String message,T e){
        return new BaseRespones<>(400,message,new Date(System.currentTimeMillis()).toString(),e);
    }
    public static <T> BaseRespones<T> failed(String message,T data){
        return new BaseRespones<>(400,message,new Date(System.currentTimeMillis()).toString(),data);
    }

}
