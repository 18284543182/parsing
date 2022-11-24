package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.AvgElectronFluxTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SpaceWeatherLaserRadarSodiumDensity;
import com.zklt.parsing.model.entity.nc.NcData;
import com.zklt.parsing.model.entity.nc.NcUtilsImpl;
import com.zklt.parsing.model.entity.nc.tab.NcElectronEnum;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wurui
 * @date 2022/11/23 22:51
 * @Description:
 */
@Service
@Mapper(type = "AvgElectronFluxTab", getAction = AvgElectronFluxTab.class)
public class AvgElectronFluxTabAction implements MessageAction<AvgElectronFluxTab> {
    List<String> strs = Stream.of("AvgDiffElectronFlux", "AvgDiffElectronFluxUncert", "AvgIntElectronFlux", "AvgIntElectronFluxUncert").collect(Collectors.toList());
    private NcUtilsImpl ncUtils = new NcUtilsImpl();
    private long fiveMin = 1000 * 60 * 5;

    @Override
    public Object doAction(HandlerMessage<AvgElectronFluxTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {

        List<String> result = new ArrayList<>();
        String filename=file.getPath();

        int val = filename.indexOf("空间天气要素");
        String topFileName = filename.substring(val, val + 6).replace(" ","");
        String typeFileName = filename.substring(val + 7, val + 13).replace(" ","");
        String goesSatelliteName = filename.substring(val + 14, val + 20).replace(" ","");
        String fiveMinFileName = filename.substring(val + 21, val + 30).replace(" ","");
        String year = filename.substring(val + 31, val + 35).replace(" ","");
        String ncFileName = filename.substring(val + 36, filename.length()).replace(" ","");

        List<AvgElectronFluxTab> list = new ArrayList<>();
        NetcdfFile ncfile = null;
        try {
            ncfile = NetcdfFile.open(filename);

            List<Variable> variables = ncfile.getVariables();
            for (Variable v : variables) {
                String name = v.getName();
                Integer ident = NcElectronEnum.getName(name);
                if (!strs.contains(name)) {
                    continue;
                }
                //  AvgIntElectronFlux
                System.out.println("name=" + v.getName() + " NameAndDimension=" + v.getNameAndDimensions() + " ElementSize=" + v.getElementSize());
                try {
                    NcData dataOfSlice = ncUtils.getDataOfSlice(v);
                    System.out.println(dataOfSlice);
                    //  原始的二维数组
                    float[][] data2Df = dataOfSlice.getData2Df();

                    //  按列生成新二维数组
                    Float[][] toNewArr = oldCastToNewArr(data2Df);

                    for (int j = 0; j < toNewArr.length; j++) {
                        System.out.println("val=" + toNewArr[j] + "val length=" + toNewArr[j].length);
                        Date date = new Date();
                        Date newDate = new Date(date.getTime() + fiveMin * j);
                        String[] datetime=DateUtil.date2Str(newDate).split(" ");
                        String datee=datetime[0];
                        String time=datetime[1];
                        String returnstrs=topFileName+" "+typeFileName+" "+goesSatelliteName+" "+fiveMinFileName+" "+
                                year+" "+ncFileName+" "+ident+" "+datee+" "+time;
                        for (int i = 0; i < toNewArr[j].length; i++) {
                            String restr=returnstrs+" "+String.valueOf(toNewArr[j][0])+" "+String.valueOf(toNewArr[j][1])+" "+
                                    String.valueOf(toNewArr[j][2])+" "+String.valueOf(toNewArr[j][3])+" "+
                                    String.valueOf(toNewArr[j][4]);
                            result.add(restr);
                        }
                    }
                } catch (InvalidRangeException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException ioe) {
        } finally {
            if (null != ncfile)
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                }
        }
        return result;
    }

    /**
     * 将原始二维数组按照列生成新的二维数组
     * @param oldArr 原始二维数组
     * @return
     */
    public static Float[][] oldCastToNewArr(float[][] oldArr){
        //  取出二维数组中的列
        List<Float> list1 = new ArrayList<>();
        List<Float> list2 = new ArrayList<>();
        List<Float> list3 = new ArrayList<>();
        List<Float> list4 = new ArrayList<>();
        List<Float> list5 = new ArrayList<>();
        for(int i = 0; i < oldArr.length; i++){
            list1.add(oldArr[i][0]);
            list2.add(oldArr[i][1]);
            list3.add(oldArr[i][2]);
            list4.add(oldArr[i][3]);
            list5.add(oldArr[i][4]);
        }

        Float[] ans1 = list1.toArray(new Float[list1.size()]);
        Float[] ans2 = list2.toArray(new Float[list2.size()]);
        Float[] ans3 = list3.toArray(new Float[list3.size()]);
        Float[] ans4 = list4.toArray(new Float[list4.size()]);
        Float[] ans5 = list5.toArray(new Float[list5.size()]);

        return casrtNewArry(ans1,ans2,ans3,ans4,ans5);
    }

    //一维数组转化为二维数组
    public static Float[][] casrtNewArry(Float[] ans1,Float[] ans2,Float[] ans3,Float[] ans4,Float[] ans5){
        Float[][] newArr = new Float[5][5];
        newArr[0] = ans1;
        newArr[1] = ans2;
        newArr[2] = ans3;
        newArr[3] = ans4;
        newArr[4] = ans5;

        //  外循环，用来控制行数
        for (int i = 0; i < newArr.length; i++) {
            // （arr静态初始化中一级花括号里面有三个整体元素所以arr）
            //  内层循环，用来控制列数，二级花括号中的元素有三个，所以arr[i].length=3
            for (int j = 0; j < newArr[i].length; j++) {
                //输出语句
                System.out.print(newArr[i][j] + "  ");
            }
            //输出完第一行元素后换行
            System.out.println();
        }
        return newArr;
    }


}
