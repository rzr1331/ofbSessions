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

    public static void main(String[] args) throws IOException {
        logger.info("free memory : " + Runtime.getRuntime().freeMemory());
        File sourceFile = new File("/Users/avichalsahai/temp_1GB_file");
        File destinationFile = new File("/Users/avichalsahai/copied_1GB_file");

        System.out.println("file length : " + sourceFile.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));

        byte[] chunk = new byte[10000];
        long readerExitCode = 1;
        int counter = 0;
        while (readerExitCode >= 0) {
            readerExitCode = inputStream.read(chunk);
            outputStream.write(chunk);
            counter ++;
        }
        logger.info("Copied File : " + destinationFile.getName());
        logger.info("File Size : " + destinationFile.length());
        logger.info("Number Of Chunks : " + counter);

        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }
}
