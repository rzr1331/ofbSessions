package Assignment4;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Producer extends Thread {
    private static Logger logger = Logger.getLogger(Producer.class.getName());
    private static ExecutorService producerExecutorService = Executors.newFixedThreadPool(1);

    private File sourceFile;
    private int chunkSize;
    private BlockingQueue<Chunk> sharedQueue;
    private MetaData metaData;

    public Producer(File sourceFile, int chunkSize, BlockingQueue<Chunk> sharedQueue, MetaData metaData) {
        this.sourceFile = sourceFile;
        this.chunkSize = chunkSize;
        this.sharedQueue = sharedQueue;
        this.metaData = metaData;
    }

    @Override public void run() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(sourceFile.getAbsolutePath(), "r");

            long sequence = 0;
            long offset = 0;
            for (int i = 0; i < metaData.getNoOfChunks(); i++) {
                producerExecutorService.submit(
                    new ChunkReadThread(sharedQueue, randomAccessFile, offset, sequence, metaData));
                System.out.println("Submitting Read Thread : " + sequence);
                sequence++;
                offset += chunkSize;
            }
            System.out.println("Sequence :" +sequence);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
