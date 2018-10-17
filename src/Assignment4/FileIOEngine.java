package Assignment4;

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
import java.util.logging.Logger;

public class FileIOEngine {
    private static Logger logger = Logger.getLogger(FileIOEngine.class.getName());
    private static final int CHUNK_SIZE = 10240;

    public static void main(String[] args) throws IOException {
        logger.info("free memory : " + Runtime.getRuntime().freeMemory());
        File sourceFile = new File("/Users/avichalsahai/temp_1GB_file");
        File destinationFile = new File("/Users/avichalsahai/copied_1GB_file");

        System.out.println("file length : " + sourceFile.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));

        long inputFileSize = sourceFile.length();
        int numberOfChunks = (int) inputFileSize/CHUNK_SIZE;
        logger.info("No. Of Chunks : " + numberOfChunks);

        byte[] chunk = new byte[CHUNK_SIZE];

        long readerExitCode = 1;
        int counter = 0;
        while (readerExitCode >= 0) {
            counter++;
            readerExitCode = inputStream.read(chunk);
            outputStream.write(chunk);
        }

        long outputFileSize = destinationFile.length();
        if (outputFileSize != inputFileSize) {
            logger.info("Failed to copy file completely");
            destinationFile.delete();
        }

        logger.info("Copied File : " + destinationFile.getName() + " File Size : " + outputFileSize + " Number Of "
            + "Chunks : " + counter);

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
