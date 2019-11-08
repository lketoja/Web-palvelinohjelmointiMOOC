

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        

        while(true){
            Socket socket = server.accept();
            
            Scanner lukija = new Scanner(socket.getInputStream());
            String pyynto = lukija.next();
            if(pyynto.contains("/quit")){
                lukija.close();
                socket.close();
                server.close();
                break;
            }
            

            // kirjoitetaan vastaus
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 200 OK");
            kirjoittaja.println();
            Files.readAllLines(Paths.get("index.html"))
                    .forEach(rivi -> kirjoittaja.println(rivi));            kirjoittaja.println("MOi!");

            kirjoittaja.close();
            lukija.close();
            socket.close();
        }
        
        server.close();

        
   
        

    }
}
