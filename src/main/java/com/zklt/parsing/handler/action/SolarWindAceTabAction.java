package com.zklt.parsing.handler.action;

import com.alibaba.fastjson.JSONArray;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.handler.utils.DateUtil;
import com.zklt.parsing.model.entity.AvgProtonFluxTab;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.SolarWindAceTab;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import uk.ac.bristol.star.cdf.CdfContent;
import uk.ac.bristol.star.cdf.CdfReader;
import uk.ac.bristol.star.cdf.Variable;
import uk.ac.bristol.star.cdf.record.VariableDescriptorRecord;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static okhttp3.internal.Util.UTC;
import static uk.ac.bristol.star.cdf.EpochFormatter.AD0_UNIX_MILLIS;

/**
 * @author wurui
 * @date 2022/11/24 11:47
 * @Description:
 */
@Service
@Mapper(type = "SolarWindAceTab", getAction = SolarWindAceTab.class)
public class SolarWindAceTabAction implements MessageAction<SolarWindAceTab> {
    @Override
    public Object doAction(HandlerMessage<SolarWindAceTab> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try {
            CdfContent content = content = new CdfContent( new CdfReader( file ) );
            Variable[] vars = content.getVariables();
            VariableDescriptorRecord variableDescriptorRecord=  vars[0].getDescriptor();
            for (int i=0;i<=variableDescriptorRecord.maxRec;i++){


                SolarWindAceTab solarWindAceTab=new SolarWindAceTab();

                double[] o1=  (double[])readShapedRecord( vars[ 0 ], i, true );
                String[] datetime= DateUtil.date2Str(getdate(o1[0])).split(" ");
                String datee=datetime[0];
                String time=datetime[1];
                float[] o6=   (float[])readShapedRecord( vars[ 5 ], i, true );
                float[] o7=  (float[])readShapedRecord( vars[ 6 ], i, true );
                float[] o8=  (float[])readShapedRecord( vars[ 7 ], i, true );
                float[] o9=  (float[])readShapedRecord( vars[ 8 ], i, true );
                float[] o10=  (float[])readShapedRecord( vars[ 9 ], i, true );
                float[] o12=  (float[])readShapedRecord( vars[ 11 ], i, true );
                float[] o14=  (float[])readShapedRecord( vars[ 13 ], i, true );
                float[] o16=  (float[])readShapedRecord( vars[ 15 ], i, true );
                float[] o18=  (float[])readShapedRecord( vars[ 17 ], i, true );


                String restr=datee+" "+time+" "+o6[0]+" "+o7[0]+" "+o8[0]+" "+o9[0]+" "+arrytostr(o10)+" "+arrytostr(o12)+" "+
                        arrytostr(o14)+" "+arrytostr(o16)+" "+arrytostr(o18);
                result.add(restr);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String arrytostr(float[] floats){
        JSONArray jsonObj = (JSONArray) JSONArray.toJSON(floats);
        String str= String.valueOf(jsonObj);
        return str;
    }

    private Date getdate(double timed )
    {
        Date time1 =null;
        try {
            long unixMillis = (long) (timed + AD0_UNIX_MILLIS );
            Date date = new Date( unixMillis );
            DateFormat epochSecFormat_ =
                    createDateFormat( "yyyy-MM-dd HH:mm:ss" );
            String time= epochSecFormat_.format( date );

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time1 = sdf.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time1;
    }

    private static DateFormat createDateFormat(String pattern ) {
        DateFormat fmt = new SimpleDateFormat( pattern );
        fmt.setTimeZone( UTC );
        fmt.setCalendar( new GregorianCalendar( UTC, Locale.UK ) );
        return fmt;
    }


    private Object readShapedRecord( Variable var, int irec, boolean rowMajor )
            throws IOException {
        return var.readShapedRecord( irec, rowMajor,
                var.createRawValueArray() );
    }
}
