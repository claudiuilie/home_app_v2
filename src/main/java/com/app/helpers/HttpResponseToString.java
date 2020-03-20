package com.app.helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpResponseToString {

    public static String parseContent(HttpURLConnection connection) throws IOException {
        BufferedReader bufferedReader = null;
        String inputLine;
        StringBuilder content = new StringBuilder();

        try{
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }

        assert bufferedReader != null;
        while ((inputLine = bufferedReader.readLine()) != null) {
            content.append(inputLine);
        }

        bufferedReader.close();
        connection.disconnect();
        return String.valueOf(content);

    }
}
