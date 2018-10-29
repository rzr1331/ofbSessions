package Assignment4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChunkWriteThread extends Thread {

    private static Logger logger = Logger.getLogger(Assignment4.ChunkReadThread.class.getName());
    private Chunk chunk;

    public static int counter = 0;
    public ChunkWriteThread(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void run() {
        String destinationDirectory = "/Users/avichalsahai/chunkDirectory/";
        String chunkFileName = destinationDirectory + chunk.getChunkSequenceNo();

        try {
            FileOutputStream chunkFileOS = new FileOutputStream(chunkFileName);
            OutputStream outputStream =
                new BufferedOutputStream(chunkFileOS);
            outputStream.write(chunk.getChunkBuffer());
            outputStream.flush();
            outputStream.close();
            logger.info("Wrote Chunk No : " + chunk.getChunkSequenceNo() + "to chunkFile");
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

