package com.huysor.projectschool.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResultResp {
    private Integer code;
    private String msg;
    private Object data;

    public ResultResp(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static ResultResp success(Object data){
        return of(200,"Success",data);
    }
    public static ResultResp success(){
        return of(200,"Success",null);
    }
    public static ResultResp success(String msg){
        return of(200,msg,null);
    }
    public static ResultResp fail(String msg){
        return of(400,msg,null);
    }
    public static ResultResp fail(){
        return of(400,"Bad Request",null);
    }
    public static ResultResp unauthorized(){
        return of(401,"Unauthorized",null);
    }

    public static ResultResp of(Integer code, String msg, Object data) {
        return new ResultResp(code, msg, data);
    }
}
