package com.discovery.category.common;

import lombok.Data;
import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {
    private String return_code;
    private String message;
    private String message_type;
    private T data;
}
