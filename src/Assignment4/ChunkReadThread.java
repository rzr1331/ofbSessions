package Assignment4;

import java.io.RandomAccessFile;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ChunkReadThread implements Runnable {
    private static Logger logger = Logger.getLogger(ChunkReadThread.class.getName());
    private BlockingQueue queue;
    private RandomAccessFile randomAccessFile;
    private long offset;
    private long sequenceNo;
    private MetaData metaData;

    public ChunkReadThread(BlockingQueue queue, RandomAccessFile randomAccessFile, long offset, long sequenceNo,
        MetaData metaData) {
        this.queue = queue;
        this.randomAccessFile = randomAccessFile;
        this.offset = offset;
        this.sequenceNo = sequenceNo;
        this.metaData = metaData;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[metaData.getChunkSize()];
            randomAccessFile.seek(offset);
            randomAccessFile.read(buffer);
            Chunk chunk = new Chunk(sequenceNo,buffer);
            boolean added = queue.add(chunk);
            logger.info("Read Chunk No " + chunk.getChunkSequenceNo() + " to sharedQueue "+added);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
