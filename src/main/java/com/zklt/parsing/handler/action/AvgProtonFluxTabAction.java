package com.zklt.parsing.handler.action;

import com.google.common.collect.Lists;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.AvgElectronFluxTab;
import com.zklt.parsing.model.entity.AvgProtonFluxTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.nc.NcData;
import com.zklt.parsing.model.entity.nc.NcReadUttil;
import com.zklt.parsing.model.entity.nc.NcUtilsImpl;
import com.zklt.parsing.model.entity.nc.tab.NcElectronEnum;
import com.zklt.parsing.model.entity.nc.tab.NcProtonEnum;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wurui
 * @date 2022/11/24 10:44
 * @Description:
 */
@Service
@Mapper(type = "AvgProtonFluxTab", getAction = AvgProtonFluxTab.class)
public class AvgProtonFluxTabAction implements MessageAction<AvgProtonFluxTab> {

    private static final String VARIABLE_NAME = "AvgDiffProtonFlux";
    private long fiveMin = 1000 * 60 * 1;


    @Override
    public Object doAction(HandlerMessage<AvgProtonFluxTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        String filePath=file.getPath();
        try {
            List<String> shortNames = Lists.newArrayList(VARIABLE_NAME);
            NcReadUttil.NcResult ncResult = NcReadUttil.read(shortNames, filePath);
            Map<String, Object> valMap = ncResult.getVals();
            Float[][][] ats = (Float[][][]) valMap.get(VARIABLE_NAME);

            //  创建二维数组,288行10列
            Float [][] toNewArr =new Float[1440][13];
            List<Float> list = new ArrayList<>();

            for (int i = 0; i <ats.length ; i++) {
                for (int j = 0; j < ats[0].length; j++) {
                    for (int k = 0; k < ats[0][0].length; k++) {
                        //  只取第一行数据
                        if (j!=0){
                            continue;
                        }
                        //  循环取的单个数值，并存入集合中
                        list.add(ats[i][j][k]);
                    }
                }
            }

            //  集合转为一维数组
            Float[] ans2 = list.toArray(new Float[list.size()]);
            one2Two(ans2,toNewArr);
            Integer ident = NcElectronEnum.getName(VARIABLE_NAME);

            //上面三式子可视代码相似，所以我们对其进行改进化简
            for(int i=0;i<toNewArr.length;i++){
                //控制每个一维数组
                Date date = new Date();
                Date newDate = new Date(date.getTime() + fiveMin * i);
                String[] datetime=DateUtil.date2Str(newDate).split(" ");
                String datee=datetime[0];
                String time=datetime[1];
                String returnstrs=ident+" "+datee+" "+time;

                String restr=returnstrs+" "+toNewArr[i][0]+" "+toNewArr[i][1]+" "+toNewArr[i][2]+" "+toNewArr[i][3]+" "+
                       toNewArr[i][4]+" "+toNewArr[i][5]+" "+toNewArr[i][6]+" "+toNewArr[i][7]+" "+toNewArr[i][8]+" "+toNewArr[i][9]+" "+toNewArr[i][10]+" "+toNewArr[i][11]+" "+toNewArr[i][12];
                result.add(restr);
                System.out.println();//每执行完一个一维数组换行
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /*
     * 1.传入的数组里两个数组的大小（一维数组length为10，则二维数组的行数乘列数也为10
     * 2.数组类型必须一样
     * */
    public static void one2Two(Float[]data,Float[][] da) {
        int k = 0;
        int hang = da.length;
        int lie = da[0].length;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                da[i][j] = data[k];
                k++;
            }
        }
    }

}