package com.zklt.parsing;

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


	@Test
	void contextLoads() throws IOException, InstantiationException, IllegalAccessException {

	}

}
