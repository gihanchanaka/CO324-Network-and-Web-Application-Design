import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialServer {
    /*
        CO324 Lab06
        E/14/158 gihanchanaka@gmail.com
        05-06-2018

        This program sets the content type of the response
            according to the request file type
     */
    static int PORT;
    static ServerSocket ss;
    public static void main(String[] args) {
        try{
            PORT=Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e){
            System.out.println("java SerialServer PORT_NO");
            return;
        }

        try{
            ss=new ServerSocket(PORT);
        }
        catch (Exception e){
            System.out.println("Error while initiating server");
            return;
        }

        while(true){
            Socket clientSocket;
            BufferedReader in;
            DataOutputStream out;
            try{
                clientSocket=ss.accept();
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                out = new DataOutputStream(clientSocket.getOutputStream());
                System.out.println("Client connected");

            }
            catch (IOException e){
                System.out.print("Error connecting to client");
                continue;
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
                    continue;
                }
            }
            catch (Exception e){

            }









        }

    }
}
