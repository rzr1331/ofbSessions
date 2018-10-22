package Assignment4;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ChunkReadThread extends Thread {
    private static Logger logger = Logger.getLogger(ChunkReadThread.class.getName());
    private BlockingQueue queue;
    private Chunk chunk;

    public ChunkReadThread(BlockingQueue queue, Chunk chunk) {
        this.queue = queue;
        this.chunk = chunk;
    }

    public BlockingQueue getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void run() {
        try {
            queue.put(getChunk());
            logger.info("Wrote Chunk No : " + chunk.getChunkSequenceNo() + "to sharedQueue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
