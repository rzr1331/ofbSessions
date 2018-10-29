package Assignment4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Consumer extends Thread {
    private static Logger logger = Logger.getLogger(Producer.class.getName());
    private static ExecutorService consumerExecuterService = Executors.newFixedThreadPool(1);

    private BlockingQueue<Chunk> sharedQueue;
    private MetaData metaData;

    public Consumer(MetaData metaData, BlockingQueue<Chunk> sharedQueue) {
        this.metaData = metaData;
        this.sharedQueue = sharedQueue;
    }

    @Override public void run() {
        try {
            sleep(200); // for testing
            int counter = 0;
            String destinationDirectory = "/Users/avichalsahai/chunkDirectory/";
            System.out.println("metaData.getNoOfChunks(): "+metaData.getNoOfChunks()+" , "+sharedQueue.size());
            for (int i = 0; i < metaData.getNoOfChunks(); i++) {
                Chunk chunk = sharedQueue.take();
                consumerExecuterService.submit(
                    new ChunkWriteThread(chunk));
                System.out.println("Submitting Write Thread : " + chunk.getChunkSequenceNo() + " , "+counter);
                //logger.info("Submitting Write Thread : " + chunk.getChunkSequenceNo());
                counter++;
            }

            File destinationFolder = new File(destinationDirectory);
            OutputStream outputStream =
                new BufferedOutputStream(new FileOutputStream(destinationDirectory + "outputFile"));

            List<File> listFile = Arrays.asList(destinationFolder.listFiles());
            List<File> sortedListFile =
                listFile.stream().sorted(Comparator.comparing(item -> item.getName())).collect(Collectors.toList());

            int deleteCounter = 0;
            for (File file : sortedListFile) {
                try {
                    InputStream inputStream =
                        new BufferedInputStream(new FileInputStream(file));
                    outputStream.write(inputStream.read());
                    logger.info("Output File written with chunk no. :" + file.getName());
                    file.delete();
                    deleteCounter++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logger.info("Delete Counter : " + deleteCounter);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

