package com.zklt.parsing.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

/**
 * @author jhzhu
 * @date 2022/11/17 15:55
 * @Description:
 */
@Service
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