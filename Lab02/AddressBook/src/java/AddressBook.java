/*
    CO324 Lab 02
    E/14/158 gihanchanaka@gmail.com
    04-02-2018
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nathasha
 */

//Logic for your address book
public class AddressBook {
    
    //read from file and create Address Book
    static HashMap<String,String[]> contacts=new HashMap();
    public static void initAddressBook(String fileName){
        File file=new File(fileName);
        try{
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            
            while(br.ready()){
                String[] ar=br.readLine().split(",");
                contacts.put(ar[0], new String[]{ar[1],ar[2]});
            }
        }
        catch(IOException e){
            System.out.println("Error opening file");
        }
        

        /*TODO*/
        //Create your address book
    }
    
    //search details of the requested contact in the address book
    public static String search(String name){
	if(contacts.containsKey(name)){
            return "Name: "+name+"<br>Tel no: "+contacts.get(name)[0]+"<br>Email: "+contacts.get(name)[1]+"<br>";
        }
        return "Contact not found";
    }
    
}


