
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        int laskuri=0;
        
        while (true) {
            // odotetaan pyyntöä
            Socket socket = server.accept();
            
            System.out.println(++laskuri);

            // kirjoitetaan vastaus
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 302 Found");
            kirjoittaja.println("Location: http://localhost:8080");

            // vapautetaan resurssit
            kirjoittaja.close();
            socket.close();
        }

    }
}
