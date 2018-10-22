package Assignment4;

public class Chunk {
    private long chunkSequenceNo;
    private byte[] chunkBuffer;

    public Chunk(long chunkSequenceNo, byte[] chunkBuffer) {
        this.chunkSequenceNo = chunkSequenceNo;
        this.chunkBuffer = chunkBuffer;
    }

    public long getChunkSequenceNo() {
        return chunkSequenceNo;
    }

    public void setChunkSequenceNo(long chunkSequenceNo) {
        this.chunkSequenceNo = chunkSequenceNo;
    }

    public byte[] getChunkBuffer() {
        return chunkBuffer;
    }

    public void setChunkBuffer(byte[] chunkBuffer) {
        this.chunkBuffer = chunkBuffer;
    }

    public static interface ChunkSequenceNoStep {
        ChunkBufferStep withChunkSequenceNo(long chunkSequenceNo);
    }

    public static interface ChunkBufferStep {
        BuildStep withChunkBuffer(byte[] chunkBuffer);
    }

    public static interface BuildStep {
        Chunk build();
    }

    public static class Builder implements ChunkSequenceNoStep, ChunkBufferStep, BuildStep {
        private long chunkSequenceNo;
        private byte[] chunkBuffer;

        private Builder() {
        }

        public static ChunkSequenceNoStep chunk() {
            return new Builder();
        }

        @Override
        public ChunkBufferStep withChunkSequenceNo(long chunkSequenceNo) {
            this.chunkSequenceNo = chunkSequenceNo;
            return this;
        }

        @Override
        public BuildStep withChunkBuffer(byte[] chunkBuffer) {
            this.chunkBuffer = chunkBuffer;
            return this;
        }

        @Override
        public Chunk build() {
            return new Chunk(
                this.chunkSequenceNo,
                this.chunkBuffer
            );
        }
    }
}
