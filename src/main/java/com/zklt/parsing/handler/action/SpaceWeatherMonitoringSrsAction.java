package com.zklt.parsing.handler.action;

import cn.hutool.core.io.FileUtil;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringSrs;
import com.zklt.parsing.model.enums.DateEnum;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 解析SRS(太阳活动区参数）txt文件
 * @author xiejian
 * @date 2022-11-19 22:13:00
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringSrs", getAction = SpaceWeatherMonitoringSrs.class)
public class SpaceWeatherMonitoringSrsAction implements MessageAction<SpaceWeatherMonitoringSrs> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringSrs> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file){
        System.out.println("读取srs文件");
        List<String> result = new ArrayList<>();
        if(file.exists()){
            try{
                List<String> datas = FileUtil.readLines(file, "UTF-8");
                String dDtime="";
                //  判断读取列以数字开头
                for (int i=0;i<datas.size();i++) {
                    if (i==1){
                        String time = datas.get(i).trim().replaceAll(" +", " ");
                        String[] times=time.split(" ");
                        String year=times[1];
                        String monthint= String.valueOf(DateEnum.valueOf(times[2]).getNum());
                        String Date=year+"-"+monthint+"-"+times[3];
                        String hour=times[4].substring(0,2);
                        String min=times[4].substring(2,4);
                        dDtime=Date+" "+hour+":"+min+":00";
                    }
                    if(isStartWithNumber(datas.get(i))){
                        String restrs=dDtime+" "+datas.get(i);
                        result.add(restrs);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(result.toString());
        return result;
    }

    @Override
    public HandlerMessage<SpaceWeatherMonitoringSrs> decode(String data, Class<SpaceWeatherMonitoringSrs> clazz) throws IllegalAccessException, InstantiationException {
        //把多个空格转换为一个空格
        data = data.trim().replaceAll(" +", " ");
        String[] datas = data.split(" ");
        Field[] fields = clazz.getFields();

        HandlerMessage<SpaceWeatherMonitoringSrs> resMessage = new HandlerMessage<>();
        SpaceWeatherMonitoringSrs body = clazz.newInstance();
        for (int i= 0;i<datas.length;i++){
            Field field = fields[i];
            field.setAccessible(true);
            field.set(body,datas[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }

        /**
         * 判断字符串是不是以数字开头
         */
    public static boolean isStartWithNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}