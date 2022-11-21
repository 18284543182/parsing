package com.zklt.parsing.handler.action;

import cn.hutool.core.io.FileUtil;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAtmosphericDensity;
import com.zklt.parsing.model.entity.SpaceWeatherMonitoringAtmosphericWind;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author xiejian
 * @date 2022/11/21 12:18
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherMonitoringAtmosphericDensity", getAction = SpaceWeatherMonitoringAtmosphericDensity.class)
public class SpaceWeatherMonitoringAtmosphericDensityAction implements MessageAction<SpaceWeatherMonitoringAtmosphericDensity> {

    public static String stationCode = "";
    public static String deviceCode = "";
    public static String happenTime = "";


    @Override
    public Object doAction(HandlerMessage<SpaceWeatherMonitoringAtmosphericDensity> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {

        List<String> strings = null;
        if (file.isFile()){
            List<String> fileDatas = FileUtil.readLines(file, "UTF-8");
             stationCode = fileDatas.get(0).split("-")[0];
             deviceCode = fileDatas.get(0).split("-")[1];
             happenTime = fileDatas.get(1);
             strings  = fileDatas.subList(2, fileDatas.size());
        }
        return strings;
    }

    @Override
    public HandlerMessage<SpaceWeatherMonitoringAtmosphericDensity> decode(String data, Class<SpaceWeatherMonitoringAtmosphericDensity> clazz) throws InstantiationException, IllegalAccessException {

        //  把多个空格转换为一个空格
        data = data.trim().replaceAll(" +", " ");
        //  解析出的数据数组
        String[] dates = data.split(" ");
        //  创建新的数组
        String[] newDatas = new String[5];
        newDatas[0] = stationCode;
        newDatas[1] = deviceCode;
        newDatas[2] = happenTime;

        for (int i = 0; i < dates.length; i++) {
            int j=3+i;
            newDatas[j]=dates[i];
        }

        Field[] files = clazz.getFields();
        if (newDatas.length!=files.length){
            return null;
        }
        HandlerMessage<SpaceWeatherMonitoringAtmosphericDensity> resMessage = new HandlerMessage<>();
        SpaceWeatherMonitoringAtmosphericDensity body = clazz.newInstance();
        for (int i= 0;i<files.length;i++){
            Field field = files[i];
            field.setAccessible(true);
            field.set(body,newDatas[i]);
        }
        resMessage.setMessage(body);
        return resMessage;
    }
}