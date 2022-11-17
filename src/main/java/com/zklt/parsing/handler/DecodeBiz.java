package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;
import org.springframework.stereotype.Component;

/**
 * @author jhzhu
 * @date 2022/11/17 16:41
 * @Description:
 */
public class DecodeBiz {

    public static  <T extends Message> HandlerMessage<T> decode(String data, Class<? extends Message> clazz){
        return null;
    }

}