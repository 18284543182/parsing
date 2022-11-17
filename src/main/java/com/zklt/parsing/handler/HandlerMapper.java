package com.zklt.parsing.handler;

import com.zklt.parsing.model.enums.Mapper;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jhzhu
 * @date 2022/11/17 18:09
 * @Description: 实现映射
 */

@Component
@Data
public class HandlerMapper implements InitializingBean {

    @Autowired
    List<MessageAction<?>> actionList;

    Map<String, Handler> handlerActionMap = new HashMap<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        if (CollectionUtils.isEmpty(actionList)) return;
        for (MessageAction<?> action:actionList){
            Class<?> beanClazz = action.getClass();
            Mapper annotation = beanClazz.getAnnotation(Mapper.class);
            Handler handler = new Handler();
            handler.setParameterTypes(annotation.getAction());
            handler.setTargetObject(action);
            handlerActionMap.put(annotation.type(),handler);
        }
    }
}
