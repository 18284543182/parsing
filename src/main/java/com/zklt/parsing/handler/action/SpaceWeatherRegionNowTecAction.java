package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowNcasts4;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowTec;
import com.zklt.parsing.model.enums.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wurui
 * @date 2022/11/24 15:51
 * @Description:
 */
@Service
@Slf4j
@Mapper(type = "SpaceWeatherRegionNowTec", getAction = SpaceWeatherRegionNowTec.class)
public class SpaceWeatherRegionNowTecAction implements MessageAction<SpaceWeatherRegionNowTec> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherRegionNowTec> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        StopWatch stopWatch = new StopWatch("区域tec");
        stopWatch.start();
        log.info("StopWatch start '" + stopWatch.getId() + "': running time (millis) = " + stopWatch.getTotalTimeMillis());
        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try (InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(input)){
                String line = null;
                //避免数据中间出现空格所以获取数据总行数进行遍历
                long countLine = Files.lines(file.toPath()).count();
                String date="";
                String time="";

                BigDecimal lats=new BigDecimal("0");
                BigDecimal latsnew= new BigDecimal("0");
                BigDecimal late=new BigDecimal("0");
                BigDecimal latstep=new BigDecimal("0");

                BigDecimal lons=new BigDecimal("0");
                BigDecimal lone=new BigDecimal("0");
                BigDecimal lonstep=new BigDecimal("0");

                String all="";
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)) {
                        line=line.replaceAll("\t"," ");
                        if (i==0){
                            line = line.trim().replaceAll(" +", " ");
                            String[] datetime=line.split(" ");
                            date=datetime[0]+"-"+datetime[1]+"-"+datetime[2];
                            time=datetime[3]+":"+"00"+":"+"00";
                        }else if (i==1){
                            line = line.trim().replaceAll(" +", " ");
                            String[] strs=line.split(" ");
                            lats=new BigDecimal(strs[0]);
                            latsnew= new BigDecimal(strs[0]);
                            late=new BigDecimal(strs[1]);
                            latstep=new BigDecimal(strs[2]);
                            lons=new BigDecimal(strs[3]);
                            lone=new BigDecimal(strs[4]);
                            lonstep=new BigDecimal(strs[5]);
                            all=date+" "+time+" "+lats+" "+late+" "+latstep+" "+lons+" "+lone+" "+lonstep;
                        }else {
                            BigDecimal lonsnew=lons;
                            line = line.trim().replaceAll(" +", " ");
                            String[] dates=line.split(" ");
                            for (String str:dates){
                                String allre=all+" "+latsnew+" "+lonsnew+" "+str;
                                result.add(allre);
                                lonsnew=lonsnew.add(lonstep);
                            }
                            latsnew=latsnew.add(latstep);
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("StopWatch stop '" + stopWatch.getId() + "': running time (millis) = " + stopWatch.getTotalTimeMillis());
        stopWatch.stop();
        return result;
    }
}
