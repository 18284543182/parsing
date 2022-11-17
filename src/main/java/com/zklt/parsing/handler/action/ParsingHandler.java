package com.zklt.parsing.handler.action;

import java.io.File;

/**
 * @author jhzhu
 * @date 2022/11/17 15:55
 * @Description:
 */
public class ParsingHandler {

    public String getJsonFilePath(String srcFilePath){
        String srcPath = srcFilePath.trim();
        File file = new File(srcPath);
        if (!file.exists()&&!file.isFile()){
            return null;
        }
        //获取文件后缀

    }
}