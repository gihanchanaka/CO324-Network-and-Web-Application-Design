import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServer {
    /*
        CO324 Lab06
        E/14/158 gihanchanaka@gmail.com
        05-06-2018

        This program sets the content type of the response
            according to the request file type
    */
    static int PORT,NO_THREADS;
    static ServerSocket ss;
    static ExecutorService pool;
    public static void main(String[] args) {
        try{
            PORT=Integer.parseInt(args[0]);
            NO_THREADS=Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e){
            System.out.println("java SerialServer PORT_NO NO_THREADS");
            return;
        }

        try{
            ss=new ServerSocket(PORT);
            pool= Executors.newFixedThreadPool(NO_THREADS);
        }
        catch (Exception e){
            System.out.println("Error while initiating server");
            return;
        }


        boolean bigProblem=false;
        while(true) {
            try{
                Socket clientSocket=ss.accept();
                ClientHandler thisClient=new ClientHandler(clientSocket);
                pool.execute(thisClient);
                bigProblem=false;
            }
            catch (Exception e){
                if(bigProblem){
                    System.out.println("There seems to be a problem");
                    pool.shutdown();
                    return;
                }
                else{
                    continue;
                }
            }

        }
    }
}




class ClientHandler implements Runnable{
    Socket clientSocket;
    ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    public void run(){
        BufferedReader in;
        DataOutputStream out;
        try{
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            out = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("Client connected to thread "+Thread.currentThread().getName());

        }
        catch (IOException e){
            System.out.print("Error connecting to client");
            return;
        }

        try {
            String[] ar = in.readLine().split(" ");
            if(ar[0].equals("GET")){
                String fileName=ar[1].substring(1);

                System.out.println("Reading file "+fileName);

                File file=new File(fileName);
                FileInputStream fis=new FileInputStream(file);
                byte[] fileBytes=new byte[(int)file.length()];
                fis.read(fileBytes);

                String fileContentType= Files.probeContentType(file.toPath());
                out.writeBytes("HTTP/1.1 200 OK\r\n");
                out.writeBytes(""+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+"\r\n");
                out.writeBytes("Accept-Ranges: bytes\r\n");
                out.writeBytes("Content-Length: "+fileBytes.length+"\r\n");
                out.writeBytes("Content-Type: "+fileContentType+"\r\n");
                out.writeBytes("\r\n");
                out.write(fileBytes);
                out.flush();
                clientSocket.close();
                System.out.println("File "+fileName+" sent and client disconnected");


            }
            else{
                return;
            }
        }
        catch (Exception e){

        }
    }


}
