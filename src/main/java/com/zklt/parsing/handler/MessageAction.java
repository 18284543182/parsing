package com.zklt.parsing.handler;

import com.zklt.parsing.model.entity.HandlerMessage;
import com.zklt.parsing.model.entity.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jhzhu
 * @date 2022/11/17 17:09
 * @Description:
 */

public interface MessageAction<T extends Message> {


    String doAction(HandlerMessage<T> message);

    default List<String> readFile(File file){
        List<String> result = new ArrayList<>();
        if(file.exists())
        {
            try (InputStreamReader input = new InputStreamReader(new FileInputStream(file), "utf-8"); BufferedReader br = new BufferedReader(input);){
                String line = null;
                while((line = br.readLine()) != null)
                {
                    result.add(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return result;
    }

}
