package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;

import java.io.File;

/**
 * @author jhzhu
 * @date 2022/11/17 17:09
 * @Description:
 */

public interface MessageAction<T extends Message> {


    String doAction(HandlerMessage<T> message);

    default String[] readFile(File file){
        return null;
    }

    default HandlerMessage<?> decode(String data, Class<? extends Message> clazz){
        return null;
    }

}
