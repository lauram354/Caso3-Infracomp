import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import client.Client;
import server.Server;

public class TestRunnerApp {

    private static final int NUM_TESTS = 5;
    private static final int[] CONCURRENT_CLIENTS = { 1, 4, 8, 32 };
    private static final int TOTAL_CLIENTS = 32;
    private static final String RESULTS_FILE = "results.csv";

    public static void main(String[] args) {
        createOutputFile();
        for (int i = 0; i < CONCURRENT_CLIENTS.length; i++) {
            int numClients = CONCURRENT_CLIENTS[i];
            TestInfo testInfo = new TestInfo("cipher_method_placeholder", numClients);
            long totalTime = 0;
            for (int j = 0; j < NUM_TESTS; j++) {
                long startTime = System.nanoTime();
                runTest(numClients);
                long endTime = System.nanoTime();
                totalTime += endTime - startTime;
            }
            long avgTime = totalTime / NUM_TESTS;
            double avgTimeMs = (double) avgTime / 1000000;
            double avgTimeS = avgTimeMs / 1000;
            testInfo.setAvgTimeNs(avgTime);
            testInfo.setAvgTimeMs(avgTimeMs);
            testInfo.setAvgTimeS(avgTimeS);
            writeOutput(testInfo);
        }
    }

    private static void runTest(int numClients) {
        Server server = new Server();
        server.setUp(numClients);
        server.start();
        Thread[] clients = new Thread[TOTAL_CLIENTS];
        for (int i = 0; i < TOTAL_CLIENTS; i++) {
            Client client = new Client();
            clients[i] = client;
            client.start();
        }
        for (int i = 0; i < TOTAL_CLIENTS; i++) {
            try {
                clients[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public static void createOutputFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESULTS_FILE))) {
            writer.println("encryption_algorithm,num_clients,avg_time_s,avg_time_ms,avg_time_ns");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeOutput(TestInfo testInfo) {
        try (FileWriter writer = new FileWriter(RESULTS_FILE, true)) {
            writer.append(testInfo.getEncryptionAlgorithm());
            writer.append(';');
            writer.append(String.valueOf(testInfo.getNumClients()));
            writer.append(';');
            writer.append(String.valueOf(testInfo.getAvgTimeS()));
            writer.append(';');
            writer.append(String.valueOf(testInfo.getAvgTimeMs()));
            writer.append(';');
            writer.append(String.valueOf(testInfo.getAvgTimeNs()));
            writer.append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
