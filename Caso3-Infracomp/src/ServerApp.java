import server.Server;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.setUpWithConsole();
        server.start();
    }
}
