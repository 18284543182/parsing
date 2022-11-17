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

    public String getResPath(File file){

        List<String> dataList = new ArrayList<>();
        List<String> formatDataArr = this.targetObject.readFile(file);

        for (String s:formatDataArr){
            HandlerMessage<?> message = DecodeBiz.decode(s,this.parameterTypes);
            String res = this.targetObject.doAction(message);
            dataList.add(res);
        }
        return getLocalPath(dataList);
    }

    private String getLocalPath(List<String> list){
        return null;
    }

}
