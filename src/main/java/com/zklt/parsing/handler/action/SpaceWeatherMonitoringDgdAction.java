package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDgd;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * @author wurui
 * @date 2022/11/18 11:58
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringDgd", getAction = SpaceWeatherMonitoringDgd.class)
public class SpaceWeatherMonitoringDgdAction implements MessageAction<SpaceWeatherMonitoringDgd> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringDgd> message) {
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
                String stationcode1="";
                String stationcode2="";
                String stationcode3="";
                List<String> cachestr=new ArrayList<>();

                List<String> cachestrtrue=new ArrayList<>();
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    cachestr.add(line);
                    if (!StringUtils.isEmpty(line) &&!line.startsWith("#")&&!line.startsWith(":")) {
                        String stationcodes=cachestr.get(i-2);
                        String[] stars=stationcodes.split(" ");
                        for (int j=0;j<stars.length;j++){
                            String str=stars[j];
                            if (str != null && str.length() != 0&&!str.equals(" ")&&!str.contains("-")&&!str.contains("#")){
                                cachestrtrue.add(str);
                            }
                        }

                        String linenew=  line.replace("-"," -");

                        String[] datasnew=linenew.split(" ");

                        List<String> datas=new ArrayList<>();

                        for (int b=0;b<datasnew.length;b++){
                            String str=datasnew[b];
                            if (str != null && str.length() != 0&&!str.equals(" ")){
                                datas.add(str);
                            }
                        }
                        String year=datas.get(0);
                        String month=datas.get(1);
                        String day=datas.get(2);
                        int h1=0;
                        int h2=0;
                        int h3=0;

                        for (int a=0;a<datas.size();a++){
                            if (a>=4&&a<=11){
                                String hour="";
                                if (h1<10) {
                                    hour="0"+h1+":00:00";
                                }else {
                                    hour=h1+":00:00";
                                }
                                String returnstr=year+" "+month+" "+day+" "+hour+" "+cachestrtrue.get(0)+" "+datas.get(a);
                                result.add(returnstr);
                                h1=h1+3;
                            }
                            if (a>=13&&a<=20){
                                String hour="";
                                if (h2<10) {
                                    hour="0"+h2+":00:00";
                                }else {
                                    hour=h2+":00:00";
                                }
                                String returnstr=year+" "+month+" "+day+" "+hour+" "+cachestrtrue.get(1)+" "+datas.get(a);
                                result.add(returnstr);
                                h2=h2+3;
                            }

                            if (a>=22&&a<=29){
                                String hour="";
                                if (h3<10) {
                                    hour="0"+h3+":00:00";
                                }else {
                                    hour=h3+":00:00";
                                }
                                String returnstr=year+" "+month+" "+day+" "+hour+" "+cachestrtrue.get(2)+" "+datas.get(a);
                                result.add(returnstr);
                                h3=h3+3;
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
}
