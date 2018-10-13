import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetMultiURL_Modified extends Thread{
    /*
        CO324 Lab 01
        E/14/158
        gihanchanaka@gmail.com
        28-01-2018
     */
    String url;
    int index;
    static StringBuffer content=new StringBuffer("");

    GetMultiURL_Modified(String url,int index){
        this.url=url;
        this.index=index;
        //System.out.println("Made thread "+this.index+" : "+this.url);
    }

    synchronized void  app(String s){
        content.append(s);
    }

    public static void main(String[] args) {
        int N=args.length;
        GetMultiURL_Modified[] tr=new GetMultiURL_Modified[N];
        for(int x=0;x<N;x++){
            tr[x]=new GetMultiURL_Modified(args[x],x);

        }

        for(int x=0;x<N;x++){
            tr[x].start();
        }


        for(int x=0;x<N;x++){

            try{

                tr[x].join();

            }

            catch (Exception e){
                System.out.println("Error while joining thread "+x);
            }

        }

        System.out.println("Completed downlaoding");
        System.out.println(content.toString());


    }

    public void run(){
        try{

            URL u=new URL(this.url);
            InputStreamReader i=new InputStreamReader(u.openStream());
            BufferedReader br=new BufferedReader(i);

            String line="";
            while((line=br.readLine())!=null){
                app(this.index+" "+line+"\n");
            }
        }
        catch (Exception e){
            e.printStackTrace(System.out);
            System.out.println("Error while downloading "+url);

        }
    }
}
