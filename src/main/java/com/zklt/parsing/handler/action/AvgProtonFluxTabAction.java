package com.zklt.parsing.handler.action;

import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.AvgElectronFluxTab;
import com.zklt.parsing.model.entity.AvgProtonFluxTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.nc.NcData;
import com.zklt.parsing.model.entity.nc.NcUtilsImpl;
import com.zklt.parsing.model.entity.nc.tab.NcProtonEnum;
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
 * @date 2022/11/24 10:44
 * @Description:
 */
@Service
@Mapper(type = "AvgProtonFluxTab", getAction = AvgProtonFluxTab.class)
public class AvgProtonFluxTabAction implements MessageAction<AvgProtonFluxTab> {


    List<String> strs = Stream.of("AvgDiffProtonFlux", "AvgDiffAlphaFlux", "AvgDiffAlphaFluxObserved", "AvgDiffAlphaFluxUncert").collect(Collectors.toList());

    private NcUtilsImpl ncUtils = new NcUtilsImpl();

    private long fiveMin = 1000 * 60 * 1;


    @Override
    public Object doAction(HandlerMessage<AvgProtonFluxTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        String filename=file.getPath();
        NetcdfFile ncfile = null;
        try {
            ncfile = NetcdfFile.open(filename);
            List<Variable> variables = ncfile.getVariables();
            for (Variable v : variables) {
                String name = v.getName();
                Integer ident = NcProtonEnum.getName(name);
                if (!strs.contains(name)) {
                    continue;
                }
                System.out.println("name=" + v.getName() + " NameAndDimension=" + v.getNameAndDimensions() + " ElementSize=" + v.getElementSize());
                try {
                    NcData dataOfSlice = ncUtils.getDataOfSlice(v);
                    System.out.println("val=" + dataOfSlice.toString());
                    float[][] data2Df = dataOfSlice.getData2Df();
                    //  按列生成新二维数组
                    Float[][] toNewArr = oldCastToNewArr(data2Df);

                    for (int j = 0; j < toNewArr.length; j++) {
                        Date date = new Date();
                        Date newDate = new Date(date.getTime() + fiveMin * j);
                        String[] datetime= DateUtil.date2Str(newDate).split(" ");
                        String datee=datetime[0];
                        String time=datetime[1];
                        String returnstrs= ident+" "+datee+" "+time;
                        for (int i = 0; i < toNewArr[j].length; i++) {
                            String restr=returnstrs+" "+toNewArr[j][0]+" "+toNewArr[j][1];
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
     * 将原始二维数组按照列生成新的二维数组(质子通道只有两个通道)
     * @param oldArr 原始二维数组
     * @return
     */
    public static Float[][] oldCastToNewArr(float[][] oldArr){
        //  取出二维数组中的列
        List<Float> list1 = new ArrayList<>();
        List<Float> list2 = new ArrayList<>();
        for(int i = 0; i < oldArr.length; i++){
            list1.add(oldArr[i][0]);
            list2.add(oldArr[i][1]);
        }

        Float[] ans1 = list1.toArray(new Float[list1.size()]);
        Float[] ans2 = list2.toArray(new Float[list2.size()]);

        return casrtNewArry(ans1,ans2);
    }

    //一维数组转化为二维数组
    public static Float[][] casrtNewArry(Float[] ans1,Float[] ans2){
        Float[][] newArr = new Float[2][11];
        newArr[0] = ans1;
        newArr[1] = ans2;
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
