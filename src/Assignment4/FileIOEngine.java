package Assignment4;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

public class FileIOEngine {
    private static Logger logger = Logger.getLogger(FileIOEngine.class.getName());
    private static long sourceFileSize = 0;
    private static final int CHUNK_SIZE = 1024 * 1000;
    private static File sourceFile = new File("/Users/avichalsahai/temp_1GB_file");

    public static void main(String[] args) throws IOException {
        BlockingQueue<Chunk> sharedQueue = new ArrayBlockingQueue<>(2000);

        // Starting Producer + Consumer seperate to simulate real time file transfer.
        MetaData metaData = getMetadata(sourceFile);
        Producer producer = new Producer(sourceFile, CHUNK_SIZE, sharedQueue, metaData);
        producer.start();
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Consumer consumer = new Consumer(metaData, sharedQueue);
        consumer.start();

        System.out.println("Bye bye");
        System.in.read();

    }

    private static MetaData getMetadata(File sourceFile) {
        long sourceFileSize = sourceFile.length();
        int noOfChunks = (int) Math.ceil(sourceFileSize / CHUNK_SIZE);
        return new MetaData(CHUNK_SIZE, noOfChunks, sourceFileSize);
    }
}
