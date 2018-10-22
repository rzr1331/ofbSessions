package Assignment4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{
    private File sourceFile;
    private int chunkSize;
    private BlockingQueue<Chunk> sharedQueue;

    public Producer(File sourceFile, int chunkSize, BlockingQueue<Chunk> sharedQueue) {
        this.sourceFile = sourceFile;
        this.chunkSize = chunkSize;
        this.sharedQueue = sharedQueue;
    }

    @Override public void run() {
        try {
            System.out.println("file length : " + sourceFile.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));

            byte[] buffer = new byte[chunkSize];
            long readerExitCode = 1;
            while (readerExitCode != -1) {
                long counter = 0;
                readerExitCode = inputStream.read(buffer);
                Chunk chunk = Chunk.Builder.chunk()
                    .withChunkSequenceNo(counter)
                    .withChunkBuffer(buffer)
                    .build();
                ChunkReadThread chunkThread = new ChunkReadThread(sharedQueue, chunk);
                currentThread().start();
            }
            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
