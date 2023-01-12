package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.AvgProtonFluxTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SolarAtmosphereXrayFluxTab;
import com.zklt.parsing.model.entity.nc.NcReadUttil;
import com.zklt.parsing.model.entity.nc.NcUtilsImpl;
import com.zklt.parsing.model.entity.nc.tab.NcXrayEnum;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wurui
 * @date 2022/11/24 11:31
 * @Description:
 */
@Service
@Mapper(type = "SolarAtmosphereXrayFluxTab", getAction = SolarAtmosphereXrayFluxTab.class)
public class SolarAtmosphereXrayFluxTabAction implements MessageAction<SolarAtmosphereXrayFluxTab> {

    List<String> strs = Stream.of("xrsa1_flux","xrsa2_flux").collect(Collectors.toList());

    private long oneMin = 1000 * 60 * 1;


    @Override
    public Object doAction(HandlerMessage<SolarAtmosphereXrayFluxTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

//    @Override
//    public List<String> readFile(File file) {
//        List<String> result = new ArrayList<>();
//        String PATH=file.getPath();
//        NetcdfFile ncfile = null;
//        try {
//            ncfile = NetcdfFile.open(PATH);
//            List<Variable> variables = ncfile.getVariables();
//            for(Variable v : variables){
//                String name = v.getName();
//                Integer ident = NcXrayEnum.getName(name);
//                if (!strs.contains(name)){
//                    continue;
//                }
//                System.out.println("name="+v.getName()+" NameAndDimension="+v.getNameAndDimensions()+" ElementSize="+v.getElementSize());
//                Object data1Df=v.read().getStorage();
//                int len = Array.getLength(data1Df);
//
//                if (data1Df!=null){
//                    Date date = new Date();
//                    for(int i=0;i<len;i++){
//                        date = new Date(date.getTime() + oneMin);
//                        String[] datetime= DateUtil.date2Str(date).split(" ");
//                        String datee=datetime[0];
//                        String time=datetime[1];
//                        String returnstrs= ident+" "+datee+" "+time+" "+Array.get(data1Df, i);
//                        result.add(returnstrs);
//                    }
//                }
//            }
//        } catch (IOException ioe) {
//        } finally {
//            if (null != ncfile)
//                try {
//                    ncfile.close();
//                } catch (IOException ioe) {
//                }
//        }
//        return result;
//    }
    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try{
            String PATH=file.getPath();
            NcReadUttil.NcResult ncResult = NcReadUttil.read(strs, PATH);
            Map<String, Object> valMap = ncResult.getVals();
            Object xrsa1Flux = valMap.get("xrsa1_flux");
            Object xrsa2Flux = valMap.get("xrsa2_flux");

            int len = Array.getLength(xrsa1Flux);
            if (xrsa1Flux!=null && xrsa2Flux!=null ){
                Date date = new Date();
                for(int i=0;i<len;i++){
                    date = new Date(date.getTime() + oneMin);
                    String[] datetime= DateUtil.date2Str(date).split(" ");
                    String datee=datetime[0];
                    String time=datetime[1];
                    String returnstrs= datee+" "+time+" "+Array.get(xrsa1Flux, i)+" "+Array.get(xrsa2Flux, i);
                    result.add(returnstrs);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
