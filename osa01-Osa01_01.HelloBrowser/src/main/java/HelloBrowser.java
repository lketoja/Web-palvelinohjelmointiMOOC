
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Where to?");       
        String osoite = input.nextLine();
        int portti = 80;
        Socket socket = new Socket(osoite, portti);
        
        PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
        kirjoittaja.println("GET / HTTP/1.1");
        kirjoittaja.println("Host: " + osoite);
        kirjoittaja.println();
        kirjoittaja.flush();
        
        System.out.println("RESPONSE:");
        Scanner lukija = new Scanner(socket.getInputStream());
        while(lukija.hasNextLine()){
            System.out.println(lukija.nextLine());
        }
        
        lukija.close();
        kirjoittaja.close();
        socket.close();

    }
}
