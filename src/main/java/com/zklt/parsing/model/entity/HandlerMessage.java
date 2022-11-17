package com.zklt.parsing.model.entity;

import lombok.Data;

/**
 * @author jhzhu
 * @date 2022/11/17 17:18
 * @Description:
 */
@Data
public class HandlerMessage<T> {

    private T message;

}
