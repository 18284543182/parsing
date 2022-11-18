package com.zklt.parsing.handler;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private HandlerMapper handlerMapper;

    public String getJsonFilePath(String srcFilePath, String dataType) throws InstantiationException, IllegalAccessException, IOException {
        String srcPath = srcFilePath.trim();
        String[] pathArr = srcPath.split("\\.");
        File file = new File(srcPath);
        if (pathArr[pathArr.length-1].equals("json")&&checkJsonObjectFile(file)){
            return srcFilePath;
        }
        if (!file.exists()&&!file.isFile()){
            return null;
        }
        List<Object> resList = handlerMapper.getHandlerActionMap().get(dataType).getResPath(file);

        return getLocalPath(resList, dataType);
    }

    private String getLocalPath(List<Object> list, String fileName) throws IOException {
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        String resStr = JSONArray.toJSONString(list);
        String filePath = path + fileName + fileType + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
        out.write(resStr);
        out.close();
        return filePath;
    }

    private boolean checkJsonObjectFile(File file) throws IOException {
        InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(input);
        String line = null;
        while((line = br.readLine()) != null) {
            if (line.contains("}")){
                return true;
            }
        }
        return false;
    }
}