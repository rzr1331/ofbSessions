package Assignment4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Transfer extends Thread {

    private FileChannel inChannel = null;
    private FileChannel outChannel = null;
    private long position, count;

    public Transfer(FileInputStream fis, FileOutputStream fos, long position, long count) {
        this.position = position;
        this.count = count;
        inChannel = fis.getChannel();
        outChannel = fos.getChannel();
    }

    @Override
    public void run() {
        try {
            inChannel.transferTo(position, count, outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

