package Assignment4;

import Assignment1.Task;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

public class FileIOEngine {
    private static Logger logger = Logger.getLogger(FileIOEngine.class.getName());
    private static long sourceFileSize = 0;
    private static final int CHUNK_SIZE = 1024 * 1000;

    public static void main(String[] args) throws IOException {
        BlockingQueue<Chunk> sharedQueue = new LinkedBlockingDeque<>();
        //logger.info("free memory : " + Runtime.getRuntime().freeMemory());
        File sourceFile = new File("/Users/avichalsahai/temp_1GB_file");

        Producer producer = new Producer(sourceFile,CHUNK_SIZE,sharedQueue);
        producer.start();
        Consumer consumer = new Consumer(CHUNK_SIZE,sharedQueue);
        consumer.start();

        int numberOfChunks = (int) sourceFileSize / CHUNK_SIZE;
        //logger.info("No. Of Chunks : " + numberOfChunks);
    }

    public void getMetadata(){

    }

}
