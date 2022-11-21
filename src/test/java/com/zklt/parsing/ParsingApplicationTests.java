package com.zklt.parsing;

import com.zklt.parsing.handler.ParsingHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ParsingApplicationTests {

	@Autowired
	ParsingHandler parsingHandler;

	@Test
	void contextLoads() throws IOException, InstantiationException, IllegalAccessException {

		String ll = parsingHandler.getJsonFilePath("C:\\Users\\xj\\Desktop\\空间天气1119 (2)\\空间天气1119\\激光雷达\\激光雷达-大气密度\\zwdata.20221119181017.26130\\HFT_LID01_DAM_L21_01D_20150101000000.dat","SpaceWeatherMonitoringAtmosphericDensity");
//		String ll = parsingHandler.getJsonFilePath("C:\\Users\\xj\\Desktop\\空间天气监测\\20221104\\Ace\\202211_ace_epam_1h.txt","SpaceWeatherMonitoringAceEpam");
		System.out.println(ll);
	}

}
