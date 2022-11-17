package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.enums.FileTypeEnums;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/17 15:55
 * @Description:
 */
public class ParsingHandler {

    @Autowired
    HandlerMapper handlerMapper;

    public String getJsonFilePath(String srcFilePath, String dataType){
        String srcPath = srcFilePath.trim();
        File file = new File(srcPath);
        if (!file.exists()&&!file.isFile()){
            return null;
        }
        return handlerMapper.getHandlerActionMap().get(dataType).getResPath(file);
    }

}