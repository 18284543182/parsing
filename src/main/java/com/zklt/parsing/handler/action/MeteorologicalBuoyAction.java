package com.zklt.parsing.handler.action;

import cn.hutool.core.util.XmlUtil;
import com.zklt.parsing.handler.MessageAction;
import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.MeteorologicalBuoy;
import com.zklt.parsing.model.enums.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wurui
 * @date 2022/11/22 15:32
 * @Description:
 */
@Service
@Mapper(type = "MeteorologicalBuoy", getAction = MeteorologicalBuoy.class)
public class MeteorologicalBuoyAction implements MessageAction<MeteorologicalBuoy> {
    @Override
    public Object doAction(HandlerMessage<MeteorologicalBuoy> message) {
        message.getMessage().setPath(message.getSrcFilePath());
        return message.getMessage();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try {
                Document document = XmlUtil.readXML(file.getPath());
                Element goalElement = XmlUtil.getElementByXPath("//BuoyInfo", document);
                String id = goalElement.getAttribute("id");
                String type = goalElement.getAttribute("Type");
                String name = goalElement.getAttribute("Name");
                String noinfo = goalElement.getAttribute("NO");
                String kind = goalElement.getAttribute("Kind");

                Element goalElementDateTime = XmlUtil.getElementByXPath("//DateTime", document);
                String dt=goalElementDateTime.getAttribute("DT");
                String year=dt.substring(0,4);
                String month=dt.substring(4,6);
                String day=dt.substring(6,8);
                String hour=dt.substring(8,10);
                String minute=dt.substring(10,12);
                String second="00";

                Element goalElementLocation = XmlUtil.getElementByXPath("//Location", document);
                String longitude = goalElementLocation.getAttribute("longitude");
                String latitude = goalElementLocation.getAttribute("latitude");

                Element goalElementBuoyData = XmlUtil.getElementByXPath("//BuoyData", document);
                String WS = goalElementBuoyData.getAttribute("WS");
                String WD = goalElementBuoyData.getAttribute("WD");
                String WSM = goalElementBuoyData.getAttribute("WSM");
                String AT = goalElementBuoyData.getAttribute("AT");
                String BP = goalElementBuoyData.getAttribute("BP");
                String HU = goalElementBuoyData.getAttribute("HU");
                String WT = goalElementBuoyData.getAttribute("WT");
                String BG = goalElementBuoyData.getAttribute("BG");
                String BX = goalElementBuoyData.getAttribute("BX");
                String ZQ = goalElementBuoyData.getAttribute("ZQ");
                String YBG = goalElementBuoyData.getAttribute("YBG");
                String YZQ = goalElementBuoyData.getAttribute("YZQ");
                String TenthBG = goalElementBuoyData.getAttribute("TenthBG");
                String TenthZQ = goalElementBuoyData.getAttribute("TenthZQ");
                String ZBG = goalElementBuoyData.getAttribute("ZBG");
                String ZZQ = goalElementBuoyData.getAttribute("ZZQ");

                String returnstr=year+" "+month+" "+day+" "+hour+" "+minute+" "+second+" "+id+" "+type+" "+name+" "+
                        noinfo+" "+kind+" "+longitude+" "+latitude+" "+WS+" "+WD+" "+WSM+" "+AT+" "+BP+" "+HU+" "+
                        WT+" "+BG+" "+BX+" "+ZQ+" "+YBG+" "+YZQ+" "+TenthBG+" "+TenthZQ+" "+ZBG+" "+ZZQ;

                Element goalElementSeaCurrent = XmlUtil.getElementByXPath("//SeaCurrent", document);
                List<Element> elementList = XmlUtil.getElements(goalElementSeaCurrent,"SCurrent");
                for (Element element:elementList){
                    String CS=element.getAttribute("CS");
                    String CD=element.getAttribute("CD");
                    String SE=element.getAttribute("SE");
                    String NO=element.getAttribute("NO");
                    String restr=returnstr+" "+CS+" "+CD+" "+SE+" "+NO;
                    result.add(restr);
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
