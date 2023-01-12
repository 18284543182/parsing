package com.zklt.parsing.handler.action;


import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDsd;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringDst;
import com.zklt.parsing.model.enums.Mapper;
import com.zklt.parsing.model.enums.MonthEnums;
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
 * @date 2022/11/19 22:04
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringDst", getAction = SpaceWeatherMonitoringDst.class)
public class SpaceWeatherMonitoringDstAction implements MessageAction<SpaceWeatherMonitoringDst> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringDst> message) {
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
                String yy="";
                String time_tag="";
                boolean start=false;
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)){
                        if (i==2){
                            String yearm = line.trim().replaceAll(" +", " ");
                            String[] dates = yearm.split(" ");
                            String mo=dates[0];
                            yy=dates[1]+"-"+MonthEnums.valueOf(mo).getValue();
                        }
                        if (line.startsWith("DAY")){
                            start=true;
                        }
                        if (start&&!line.startsWith("DAY")&&!line.equals("")){
                            String day=line.substring(0,2);
                            int dayi= Integer.parseInt(day.trim());
                            if (dayi<10){
                                day="0"+dayi;
                            }
                            if (dayi==10){
                                System.out.println("sdkfh");
                            }

                            String dst1=line.substring(2,7).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 01:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(7,11).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 02:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(11,15).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 03:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(15,19).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 04:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(19,23).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 05:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(23,27).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 06:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(27,31).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 07:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }


                            dst1=line.substring(31,35).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 08:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(35,40).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 09:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(40,44).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 10:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }


                            dst1=line.substring(44,48).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 11:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }


                            dst1=line.substring(48,52).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 12:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(52,56).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 13:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(56,60).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 14:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }


                            dst1=line.substring(60,64).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 15:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(64,68).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 16:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(68,73).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 17:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(73,77).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 18:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(77,81).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 19:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(81,85).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 20:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(85,89).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 21:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(89,93).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 22:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(93,97).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 23:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
                            }

                            dst1=line.substring(97,101).trim();
                            if (!"9999".equals(dst1)){
                                time_tag=yy+"-"+day+" 24:00:00";
                                String returndata=time_tag+" "+dst1;
                                result.add(returndata);
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
