package ${PackageName}.Resp;


import lombok.Data;

import java.util.Date;


@Data
public class BaseRespones<T> {
    private int code;//状态码
    private String message;//信息
    private String data;//日期时间戳
    private T date;//具体数据

    public BaseRespones(int code, String message, String data, T date) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.date = date;
    }

    public static <T>  BaseRespones<T> success(){
        return new BaseRespones<>(200,"访问成功",new Date(System.currentTimeMillis()).toString(),null);
    }
    public static <T>  BaseRespones <T> success(T data){
        return new BaseRespones<>(200,"访问成功",new Date(System.currentTimeMillis()).toString(),data);
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