import java.util.Scanner;

public class Fan {
    /*
        CO324 Lab 07
        E/14/158 gihanchanaka@gmail.com
        13-06-2018

        Refer ReadMe.pdf
     */
    static FanState fan;
    static Scanner sc=new Scanner(System.in);
    void sendCounts (int count) {
        System.err.println("Sending " + count +" counts.");
    }

    public static void main (String[] args) {
        fan=FanState.OFF;
        String input;
        System.out.println("FAN: "+fan);
        System.out.println("Type dec, inc, OFF, MED,HI or EXIT and press enter");
        while(true){
            input=sc.nextLine().trim();
            switch (input.toLowerCase()){
                case "exit":
                    System.out.println("Exiting....");
                    return;
                case "dec":
                    fan=fan.dec();
                    break;
                case "inc":
                    fan=fan.inc();
                    break;
                case "off":
                    fan=fan.changeTo(fan.OFF);
                    break;
                case "med":
                    fan=fan.changeTo(fan.MED);
                    break;
                case "hi":
                    fan=fan.changeTo(fan.HI);
                    break;
                default:
                    System.out.println("Only DEC, DEC, OFF, MED, HI or EXIT is allowed");
            }
        }


    }
}
