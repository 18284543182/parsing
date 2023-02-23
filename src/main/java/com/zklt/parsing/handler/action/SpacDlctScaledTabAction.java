package com.zklt.parsing.handler.action;


import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpacDlctScaledTab;
import com.zklt.parsing.model.enums.DateEnum;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiejian
 * @date 2023/02/02 10:25
 * @Description: 空间天气1122\单站实时-foF2、电离层最高可用频率\ionosoude_2022110710.dat
 */
@Service
@Mapper(type = "SpacDlctScaledTab", getAction = SpacDlctScaledTab.class)
public class SpacDlctScaledTabAction implements MessageAction<SpacDlctScaledTab> {


    @Override
    public Object doAction(HandlerMessage<SpacDlctScaledTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try {
            //避免数据中间出现空格所以获取数据总行数进行遍历
            long countLine = Files.lines(file.toPath()).count();
            InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(input);
            String line = null;
            for (int i = 0;i<countLine+1;i++){
                line = br.readLine();
                if (!StringUtils.isEmpty(line)){
                    String[] arr = line.split("\\s+");
                    String stationCode = arr[0];
                    String year = arr[1];
                    String time = arr[2];
                    String val1 = arr[3];
                    String val2 = arr[4];
                    String val3 = arr[5];
                    String val4 = arr[6];

                    String returndata=stationCode+" "+year+" "+time+" "+val1+" "+val2+" "+val3+" "+val4;
                    result.add(returndata);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
