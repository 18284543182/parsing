package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherElectronDensity;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wurui
 * @date 2022/11/24 21:03
 * @Description:
 */
@Service
@Mapper(type = "SpaceWeatherElectronDensity", getAction = SpaceWeatherElectronDensity.class)
public class SpaceWeatherElectronDensityAction implements MessageAction<SpaceWeatherElectronDensity> {
    @Override
    public Object doAction(HandlerMessage<SpaceWeatherElectronDensity> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        String filpath=file.getPath();
        String[] paths=filpath.split("\\\\");

        String name=paths[paths.length-1].split("\\.")[0];
        String[] datetimes=name.split(File.separator);
        String datetime=datetimes[datetimes.length-1];
        String year=datetime.substring(0,4);
        String month=datetime.substring(4,6);
        String day=datetime.substring(6,8);
        String hour=datetime.substring(8,10);
        String minute=datetime.substring(10,12);
        String second=datetime.substring(12,14);

        String filename=file.getName();

        String returnstr=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second+" "+filename;
        result.add(returnstr);
        return result;
    }
}
