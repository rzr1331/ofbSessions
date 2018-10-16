package Assignment4;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileIOEngine {

    public static void main (String[] args) throws IOException {
        System.out.println("free memory : " + Runtime.getRuntime().freeMemory());
        File sourceFile = new File("/Users/avichalsahai/temp_1GB_file");

        System.out.println("file length : " + sourceFile.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
        byte[] chunk = new byte[1024];
        int offset = 0;
        int length = 1024;
        int count =0 ;
        while (offset <= sourceFile.length()) {
            int readCount = inputStream.read(chunk, offset, length);
            System.out.println("readCount : " + readCount);
            System.out.println("free memory : " + Runtime.getRuntime().freeMemory());

            FileOutputStream fileOutputStream = new FileOutputStream("/Users/avichalsahai/copied_1GB_file");
            fileOutputStream.write(chunk);
            offset+=readCount;
        }

        System.out.println("Copied file length : " + new File("/Users/avichalsahai/copied_1GB_file").length());






    }

}
