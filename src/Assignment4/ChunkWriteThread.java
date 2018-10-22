package Assignment4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ChunkWriteThread extends Thread {

    private static Logger logger = Logger.getLogger(Assignment4.ChunkReadThread.class.getName());
    private Chunk chunk;

    public ChunkWriteThread(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void run() {
        try {
            String destinationDirectory = "/Users/avichalsahai/chunkDirectory";
            String chunkFileName = destinationDirectory + "Chunk-" + chunk.getChunkSequenceNo();

            OutputStream outputStream =
                new BufferedOutputStream(new FileOutputStream(destinationDirectory + chunkFileName));
            outputStream.write(chunk.getChunkBuffer());
            outputStream.flush();
            outputStream.close();

            // TODO : Append all chunk Files

            logger.info("Wrote Chunk No : " + chunk.getChunkSequenceNo() + "to sharedQueue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

