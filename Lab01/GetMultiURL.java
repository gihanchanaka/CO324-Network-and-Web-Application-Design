import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetMultiURL implements Runnable{
    /*
        CO324 Lab 01
        E/14/158
        gihanchanaka@gmail.com
        28-01-2018
     */
    String url;
    int index;
    static String[] content;

    GetMultiURL(String url,int index){
        this.url=url;
        this.index=index;
    }

    public static void main(String[] args) {
        int N=args.length;
        content=new String[N];
        GetMultiURL[] ar=new GetMultiURL[N];
        Thread[] tr=new Thread[N];
        for(int x=0;x<N;x++){
            ar[x]=new GetMultiURL(args[x],x);
            tr[x]=new Thread(ar[x]);
        }
        for(int x=0;x<N;x++)tr[x].start();
        for(int x=0;x<N;x++){
            try{
                tr[x].join();
            }
            catch (Exception e){

            }
        }

        System.out.println("Completed downlaoding");
        for(int x=0;x<N;x++){
            System.out.println((x+1)+" "+args[x]);
            System.out.println(content[x]);
        }


    }

    public void run(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            while((line=br.readLine())!=null){
                content[index]=content[index]+line+"\n";
            }
        }
        catch (Exception e){
            System.out.println("Error while downloading "+url);
            System.out.println(e.toString());
        }
    }
}
