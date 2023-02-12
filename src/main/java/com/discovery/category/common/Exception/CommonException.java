package com.discovery.category.common.Exception;

import com.discovery.category.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Supplier;


@Data
@EqualsAndHashCode(callSuper=false)
public class CommonException extends RuntimeException {

    public CommonException() {
        super();
    }

    public CommonException(Constant.RESULT_CODE resultCode) {
        super();
        this.result_code = resultCode;
    }

    private Constant.RESULT_CODE result_code;
}
