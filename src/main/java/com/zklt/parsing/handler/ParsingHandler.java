package com.zklt.parsing.handler;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/17 15:55
 * @Description:
 */
@Service
public class ParsingHandler {

    @Value("${target.result.path}")
    private String path;

    @Value("${target.result.type}")
    private String fileType;

    @Autowired
    HandlerMapper handlerMapper;

    public String getJsonFilePath(String srcFilePath, String dataType) throws InstantiationException, IllegalAccessException, IOException {
        String srcPath = srcFilePath.trim();
        File file = new File(srcPath);
        if (!file.exists()&&!file.isFile()){
            return null;
        }
        List<String> resList = handlerMapper.getHandlerActionMap().get(dataType).getResPath(file);

        return getLocalPath(resList, dataType);
    }

    private String getLocalPath(List<String> list, String fileName) throws IOException {
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        String resStr = JSONArray.toJSONString(list);
        String filePath = path + fileName + fileType;
        BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
        out.write(resStr);
        out.close();
        return filePath;
    }
}