package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpacDlctTecMapTab;
import com.zklt.parsing.model.entity.SpaceWeatherRegionNowTec;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
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
@Mapper(type = "SpacDlctTecMapTab", getAction = SpacDlctTecMapTab.class)
public class SpacDlctTecMapTabAction implements MessageAction<SpacDlctTecMapTab> {
    @Override
    public Object doAction(HandlerMessage<SpacDlctTecMapTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try{
                //避免数据中间出现空格所以获取数据总行数进行遍历
                long countLine = Files.lines(file.toPath()).count();
                InputStreamReader input = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(input);
                String line = null;
                for (int i = 0;i<countLine+1;i++){
                    line = br.readLine();
                    if (!StringUtils.isEmpty(line)&&i!=0){
                        String[] arr = line.split("\\s+");
                        String yyyy = arr[0];
                        String MM = arr[1];
                        String dd = arr[2];
                        String hh = arr[3];
                        String mm = arr[4];
                        String ss = arr[5];

//                        String dateTime = yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss;

                        String PRN = arr[6];
                        String Az = arr[7];
                        String Elv = arr[8];
                        String S4 = arr[9];
                        String Sig = arr[10];
                        String TEC0 = arr[11];

                        String returndata=yyyy+" "+MM+" "+dd+" "+hh+" "+mm+" "+ss+" "+PRN+" "+Az+" "+Elv+" "+S4+" "+Sig+" "+TEC0;
                        result.add(returndata);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
