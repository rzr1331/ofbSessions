package Assignment4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class FileTransfer {

    long CHUNK_FILE_SIZE = 5 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        System.out.println("free memory : " + Runtime.getRuntime().freeMemory());
        File sourceFile = new File("/Users/anurag/Desktop/temp_1GB_file");
        System.out.println("file length : " + sourceFile.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));

        int readCount = inputStream.read(new byte[1024000], 0, 1024000);

        System.out.println("readCount : " + readCount);

        System.out.println("free memory : " + Runtime.getRuntime().freeMemory());



    }

    private static class FileChunk implements Serializable {
        long fileSize;

        long chunkOffset;

        long chunkSize;

        byte[] chunk;

        public FileChunk(long fileSize, long chunkOffset, long chunkSize, byte[] chunk) {
            this.fileSize = fileSize;
            this.chunkOffset = chunkOffset;
            this.chunkSize = chunkSize;
            this.chunk = chunk;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public long getChunkOffset() {
            return chunkOffset;
        }

        public void setChunkOffset(long chunkOffset) {
            this.chunkOffset = chunkOffset;
        }

        public long getChunkSize() {
            return chunkSize;
        }

        public void setChunkSize(long chunkSize) {
            this.chunkSize = chunkSize;
        }

        public byte[] getChunk() {
            return chunk;
        }

        public void setChunk(byte[] chunk) {
            this.chunk = chunk;
        }

        public static interface FileSizeStep {
            ChunkOffsetStep withFileSize(long fileSize);
        }

        public static interface ChunkOffsetStep {
            ChunkSizeStep withChunkOffset(long chunkOffset);
        }

        public static interface ChunkSizeStep {
            ChunkStep withChunkSize(long chunkSize);
        }

        public static interface ChunkStep {
            BuildStep withChunk(byte[] chunk);
        }

        public static interface BuildStep {
            FileChunk build();
        }

        public static class Builder
            implements FileSizeStep, ChunkOffsetStep, ChunkSizeStep, ChunkStep, BuildStep {
            private long fileSize;

            private long chunkOffset;

            private long chunkSize;

            private byte[] chunk;

            private Builder() {
            }

            public static FileSizeStep fileChunk() {
                return new Builder();
            }

            @Override
            public ChunkOffsetStep withFileSize(long fileSize) {
                this.fileSize = fileSize;
                return this;
            }

            @Override
            public ChunkSizeStep withChunkOffset(long chunkOffset) {
                this.chunkOffset = chunkOffset;
                return this;
            }

            @Override
            public ChunkStep withChunkSize(long chunkSize) {
                this.chunkSize = chunkSize;
                return this;
            }

            @Override
            public BuildStep withChunk(byte[] chunk) {
                this.chunk = chunk;
                return this;
            }

            @Override
            public FileChunk build() {
                return new FileChunk(
                    this.fileSize,
                    this.chunkOffset,
                    this.chunkSize,
                    this.chunk
                );
            }
        }
    }
}
