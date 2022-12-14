package com.zklt.parsing.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<Object> getDataList(String srcFilePath, String dataType) throws IOException, InstantiationException, IllegalAccessException {
        String srcPath = srcFilePath.trim();
        String[] pathArr = srcPath.split("\\.");
        File file = new File(srcPath);
        if (!file.exists()&&!file.isFile()){
            return null;
        }
        if (pathArr[pathArr.length-1].equals("json")&&checkJsonObjectFile(file)){
            List<Object> objects= JsonObject(file);
            return objects;
        }
        return handlerMapper.getHandlerActionMap().get(dataType).getResPath(file);
    }

    public String getJsonFilePath(String srcFilePath, String dataType) throws InstantiationException, IllegalAccessException, IOException {
        return getLocalPath(getDataList(srcFilePath, dataType), dataType);
    }

    private String getLocalPath(List<Object> list, String fileName) throws IOException {
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        String resStr = JSONArray.toJSONString(list);
        String filePath = path + fileName + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileType;
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
            if (line.contains("{")){
                return true;
            }
        }
        return false;
    }
    private List<Object> JsonObject(File file) throws IOException {
        List<Object> objects=new ArrayList<>();
        InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(input);
        String line = br.readLine();
        objects= JSON.parseArray(line, Object.class);

        return objects;
    }
}