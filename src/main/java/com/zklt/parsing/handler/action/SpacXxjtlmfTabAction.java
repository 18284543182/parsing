package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.SpacXxjtImfTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.nc.NcReadUttil;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiejian
 * @date 2023/01/13 17:25
 * @Description: oe_m1m_dscovr_s20170101000000_e20170101235959_p20170102021117_pub.nc
 */
@Service
@Mapper(type = "SpacXxjtImfTab", getAction = SpacXxjtImfTab.class)
public class SpacXxjtlmfTabAction implements MessageAction<SpacXxjtImfTab> {
    private long oneMin = 1000 * 60 * 1;
    List<String> strs = Stream.of("bt","bx_gsm","by_gsm","bz_gsm","time").collect(Collectors.toList());

    @Override
    public Object doAction(HandlerMessage<SpacXxjtImfTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try{
            String PATH=file.getPath();
            NcReadUttil.NcResult ncResult = NcReadUttil.read(strs, PATH);
            Map<String, Object> valMap = ncResult.getVals();
            Object bt = valMap.get("bt");
            Object bxGsm = valMap.get("bx_gsm");
            Object byGsm = valMap.get("by_gsm");
            Object bzGsm = valMap.get("bz_gsm");
            Object time = valMap.get("time");

            int len = Array.getLength(bt);
            for(int i=0;i<len;i++){
                String returnstrs= Array.get(bt, i)+" "+Array.get(bxGsm, i)+" "+Array.get(byGsm, i)+" "+Array.get(bzGsm, i)+" "+Array.get(time, i);
                result.add(returnstrs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
