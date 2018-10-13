import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetHeaders {
    /*
        CO324 Lab 01
        E/14/158
        gihanchanaka@gmail.com
        28-01-2018
     */
    public static void main(String[] args) {

        try {
            URL url = new URL(args[0]);
            URLConnection urlCon = url.openConnection();
            Map<String, List<String>> headers = urlCon.getHeaderFields();
            Iterator<String> headerFields = headers.keySet().iterator();
            System.out.println("No of header fields: "+headers.keySet().size());
            while(headerFields.hasNext()){
                String field=headerFields.next();
                System.out.println(field);
                Iterator<String> headerValues=headers.get(field).iterator();
                while(headerValues.hasNext()){
                    System.out.println("\t"+headerValues.next());
                }


            }
        }
        catch (MalformedURLException e){
            System.out.println("Error while reaching "+args[0]);
            System.out.println(e.toString());
        }
        catch (IOException e){
            System.out.println("Error while getting headers");
            System.out.println(e.toString());
        }

    }
}
