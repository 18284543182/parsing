package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDst;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringSgas;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringSrs;
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
 * @author wurui
 * @date 2023/1/11 17:30
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringSgas", getAction = SpaceWeatherMonitoringSgas.class)
public class SpaceWeatherMonitoringSgasAction implements MessageAction<SpaceWeatherMonitoringSgas> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringSgas> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }


    @Override
    public List<String> readFile(File file) {

        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try (InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(input)){
                String line = null;
                //避免数据中间出现空格所以获取数据总行数进行遍历
                long countLine = Files.lines(file.toPath()).count();
                String date="";
                String time="";
                boolean gettrue=false;
                for (int i = 0;i<countLine+1;i++){
                    String f107="";
                    String sn="";
                    line = br.readLine();
                    if (line!=null){
                        if (i==1){
                            //把多个空格转换为一个空格
                            line = line.trim().replaceAll(" +", " ");
                            String[] times = line.split(" ");
                            String mo=String.valueOf(DateEnum.valueOf(times[2]).getNum());;
                            date=times[1]+"-"+mo+"-"+times[3];
                            time=times[4].substring(0,2)+":"+times[4].substring(2,4)+":00";
                        }
                        if (gettrue){
                            if (line.startsWith("10")){
                                //把多个空格转换为一个空格
                                line = line.trim().replaceAll(" +", " ");
                                String[] values = line.split(" ");
                                f107=values[2];
                                sn=values[4];
                            }
                            String restr=date+" "+time+" "+f107+" "+sn;
                            result.add(restr);
                            gettrue=false;
                        }

                        if (line.startsWith("E.")){
                            gettrue=true;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
