import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GoogleSearch {
    /*
        CO324 Lab 01
        E/14/158
        gihanchanaka@gmail.com
        28-01-2018
     */
    public static void main(String[] args) {
        String keyWord="";
        int x;
        for(x=0;x<args.length-1;x++)keyWord=keyWord+args[x]+" ";
        keyWord=keyWord+args[args.length-1];
        try{
            URL url=new URL("https://www.google.lk/search?q="+URLEncoder.encode(keyWord));
            URLConnection ucom=url.openConnection();
            ucom.setRequestProperty("User-Agent","Mozilla/5.0");
            InputStreamReader isr=new InputStreamReader(ucom.getInputStream());
            BufferedReader br=new BufferedReader(isr);


            System.out.println("Fetching "+ucom.getURL().toString());
            while(br.ready()){
                String thisLine=br.readLine();
                if(thisLine==null)break;
                System.out.println(thisLine);
            }

        }
        catch (MalformedURLException e){
            System.out.println("Error while reaching "+args[0]);
            System.out.println(e.toString());
        }
        catch (Exception e){
            System.out.println("Error while getting data");
            System.out.println(e.toString());
        }
    }
}
