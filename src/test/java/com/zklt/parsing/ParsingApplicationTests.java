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

		String ll = parsingHandler.getJsonFilePath("C:" +
				"\\Users\\ZJH\\Desktop\\空间天气监测(1)\\20221105\\Ace(行星际空间观测资料)\\202211_ace_swepam_1h.txt","SpaceWeatherMonitoringAceSwepam");
		System.out.println(ll);
	}

}
