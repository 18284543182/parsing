package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/17 18:18
 * @Description:
 */
@Data
public class Handler {

    private MessageAction targetObject;

    private Class<? extends Message> parameterTypes;

    public Handler() {
    }

    public List<Object> getResPath(File file) throws InstantiationException, IllegalAccessException {

        List<Object> dataList = new ArrayList<>();
        List<String> formatDataArr = this.targetObject.readFile(file);

        for (String s:formatDataArr){
            HandlerMessage<?> message = this.targetObject.decode(s,this.parameterTypes);
            Object res = this.targetObject.doAction(message);
            dataList.add(res);
        }
        return dataList;
    }


}
