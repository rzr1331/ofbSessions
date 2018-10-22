package Assignment4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private BlockingQueue<Chunk> sharedQueue;
    private int chunkSize;

    public Consumer(int chunkSize, BlockingQueue<Chunk> sharedQueue) {
        this.chunkSize = chunkSize;
        this.sharedQueue = sharedQueue;
    }

    @Override public void run() {
        try {
            sleep(3000);
            while (!sharedQueue.isEmpty()) {
                ChunkWriteThread chunkWriteThread = new ChunkWriteThread(sharedQueue.take());
                chunkWriteThread.start();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

