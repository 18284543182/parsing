package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowTec;
import com.zklt.parsing.model.entity.SpaceWeatherThroughDoorMagnetometer;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author wurui
 * @date 2022/11/24 16:40
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherThroughDoorMagnetometer", getAction = SpaceWeatherThroughDoorMagnetometer.class)
public class SpaceWeatherThroughDoorMagnetometerAction implements MessageAction<SpaceWeatherThroughDoorMagnetometer> {
    private long onese = 1000  * 1;


    @Override
    public Object doAction(HandlerMessage<SpaceWeatherThroughDoorMagnetometer> message) {
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
                Date datetim=null;
                String date="";
                String time="";

                String stationCode="";
                String instrumentId="";
                String samplingRate="";
                String channel="";
                String hComponentCode="";
                String zComponentCode="";
                String dComponentCode="";
                String tComponentCode="";
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)) {
                      String head=line.substring(0,54);
                      String[] heads=head.split(" ");
                      String year=heads[0].substring(0,4);
                      String mo=heads[0].substring(4,6);
                      String day=heads[0].substring(6,8);
                      date=year+"-"+mo+"-"+day+" "+"00:00:00";
                        datetim= DateUtil.str2Date(date,"yyyy-MM-dd HH:mm:ss");

                        stationCode=heads[1];
                        instrumentId=heads[2];
                        samplingRate=heads[3];
                        channel=heads[4];
                        hComponentCode=heads[5];
                        zComponentCode=heads[6];
                        dComponentCode=heads[7];
                        tComponentCode=heads[8];

                        String returnstr=stationCode+" "+instrumentId+" "+samplingRate+" "+channel+" "+hComponentCode+" "+
                                zComponentCode+" "+dComponentCode+" "+tComponentCode;

                      String last=line.substring(54,line.length());
                      String[] datas=last.split(" ");
                      List<String> stringList=new ArrayList<>();
                      Collections.addAll(stringList,datas);
                        List<List<String>> lists=subList(stringList);
                        for (int j=0;j<lists.size();j++){
                            if (j!=0){
                                datetim = new Date(datetim.getTime() + onese);
                            }
                            List<String> strings=lists.get(j);
                            String datenew=DateUtil.date2Str(datetim);
                            String returnstrnew="";
                            if (strings.size()==4){
                                returnstrnew=returnstr+" "+datenew+" "+strings.get(0)+" "+strings.get(1)+" "+
                                        strings.get(2)+" "+strings.get(3);
                                result.add(returnstrnew);
                            }

                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private  final Integer splitSize = 4;
    public    <T> List<List<T>> subList(List<T> list) {
        List<List<T>> lists = new ArrayList<>();
        int size = list.size();
        if (size <= splitSize) {
            lists.add(list);
            return lists;
        }
        int number = size / splitSize;
        //完整的分隔部分
        for (int i = 0; i < number; i++) {
            int startIndex = i * splitSize;
            int endIndex = (i +1) * splitSize;
            lists.add(list.subList(startIndex, endIndex));
        }
        //最后分隔剩下的部分直接放入list
        if (number * splitSize == size) {
            return lists;
        }
        lists.add(list.subList(number * splitSize, size));
        return lists;
    }




}
