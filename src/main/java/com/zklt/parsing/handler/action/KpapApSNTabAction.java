package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.AvgProtonFluxTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.KpapApSNTab;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author wurui
 * @date 2022/11/28 18:24
 * @Description:
 */
@Service
@Mapper(type = "KpapApSNTab", getAction = KpapApSNTab.class)
public class KpapApSNTabAction implements MessageAction<KpapApSNTab> {
    @Override
    public Object doAction(HandlerMessage<KpapApSNTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

}
