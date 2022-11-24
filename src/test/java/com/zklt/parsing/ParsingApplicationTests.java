package com.zklt.parsing;

import com.zklt.parsing.handler.JsonTestHandler;
import com.zklt.parsing.handler.ParsingHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ParsingApplicationTests {

	@Autowired
	ParsingHandler parsingHandler;

	@Autowired
	JsonTestHandler jsonTestHandler;

	@Test
	void contextLoads() throws IOException, InstantiationException, IllegalAccessException {

		List<Object> ll= jsonTestHandler.getDataList("E:\\气象数据\\空间天气1122\\电离层电子密度剖面\\MHT_DPS01_IIG_L31_STP_20220531214500.PNG","SpaceWeatherElectronDensity");
		System.out.println(ll);
	}

}
