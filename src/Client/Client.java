package Client;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;


    public Client(InetAddress localHost, int i, IClientStrategy clientStrategy) {
        this.serverIP = localHost;
        this.serverPort = i;
        this.strategy = clientStrategy;

    }

    public void communicateWithServer() {
        try{
            Socket serverSocket = new Socket(serverIP,serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(),serverSocket.getOutputStream());
            serverSocket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}