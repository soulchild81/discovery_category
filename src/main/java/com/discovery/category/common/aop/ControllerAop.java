package com.discovery.category.common.aop;


import com.discovery.category.common.Constant;
import com.discovery.category.common.Exception.CommonException;
import com.discovery.category.common.annotation.DiscoveryApi;
import com.discovery.category.common.result.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAop {
    @Around(value="@annotation(discoveryApi)")
    public ServiceResult<Object> process(ProceedingJoinPoint joinPoint, DiscoveryApi discoveryApi) throws Throwable {
        ServiceResult<Object> searchResult = new ServiceResult<>();
        try{
            Object result = joinPoint.proceed();
            searchResult = (ServiceResult<Object>)result;

            this.setResult(searchResult , Constant.RESULT_CODE.SUCCESS.getMsg(), Constant.RESULT_CODE.SUCCESS.name() , Integer.toString(Constant.RESULT_CODE.SUCCESS.getCode()));
            searchResult.setData(searchResult.getResult());
            return searchResult;
        }catch(Exception e){
            e.printStackTrace();
            if(e instanceof CommonException) {
                CommonException be = (CommonException) e;
                this.setResult(searchResult , be.getResult_code().getMsg(), be.getResult_code().name() , Integer.toString(be.getResult_code().getCode()));
            } else {
                this.setResult(searchResult , Constant.RESULT_CODE.UNKNOWN_ERROR.getMsg(), Constant.RESULT_CODE.UNKNOWN_ERROR.name(), Integer.toString(Constant.RESULT_CODE.UNKNOWN_ERROR.getCode()));
            }

            return searchResult;
        }
    }

    public void setResult(ServiceResult<Object> searchResult , String msg , String type , String code){
        searchResult.setMessage(msg);
        searchResult.setMessage_type(type);
        searchResult.setReturn_code(code);
    }
}