import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetURL {
    /*
        CO324 Lab 01
        E/14/158
        gihanchanaka@gmail.com
        28-01-2018
     */
    public static void main(String[] args) {
        try{
            URL url=new URL(args[0]);
            InputStreamReader isr=new InputStreamReader(url.openStream());
            BufferedReader br=new BufferedReader(isr);


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
