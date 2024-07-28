package com.tyrone.blog.domain.response;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 * @description Response包装类
 */

@Data
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 6151686427139650973L;
    /**
     * 响应状态码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;
    public ResultResponse(String code,String message){
        this.code = code;
        this.message = message;
    }

    public ResultResponse(String code,String message,T data){
        this(code,message);
        this.data = data;
    }
    public static <T> ResultResponse<T> success(){
        return new ResultResponse<>(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage());
    }

    public static <T> ResultResponse<T> success(T data){
        return new ResultResponse<>(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage(),data);
    }

    public static <T> ResultResponse<T> fail(){
        return new ResultResponse<>(CodeEnum.FAILURE.getCode(),CodeEnum.FAILURE.getMessage());
    }

    public static <T> ResultResponse<T> fail(String code,String message){
        return new ResultResponse<>(code,message);
    }

    public static <T> ResultResponse<T> error(){
        return new ResultResponse<>(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
    }
    public static <T> ResultResponse<T> error(BizException e){
        return new ResultResponse<>(e.getCode(),e.getMessage());
    }
}
