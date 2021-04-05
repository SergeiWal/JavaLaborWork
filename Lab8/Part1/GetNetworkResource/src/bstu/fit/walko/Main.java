package bstu.fit.walko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
	// write your code here
        URL url = null;
        String urlString = "https://www.youtube.com/";

        try {
            url  = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(url == null){
            throw new RuntimeException();
        }


        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String buf = "";
            while((buf = bufferedReader.readLine())!=null){
                System.out.println(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
