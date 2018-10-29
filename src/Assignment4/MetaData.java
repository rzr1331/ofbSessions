package Assignment4;

public class MetaData {
    private int chunkSize;
    private int noOfChunks;
    private long sourceFileSize;

    public MetaData(int chunkSize, int noOfChunks, long sourceFileSize) {
        this.chunkSize = chunkSize;
        this.noOfChunks = noOfChunks;
        this.sourceFileSize = sourceFileSize;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public int getNoOfChunks() {
        return noOfChunks;
    }

    public void setNoOfChunks(int noOfChunks) {
        this.noOfChunks = noOfChunks;
    }

    public long getSourceFileSize() {
        return sourceFileSize;
    }

    public void setSourceFileSize(long sourceFileSize) {
        this.sourceFileSize = sourceFileSize;
    }

    public static interface ChunkSizeStep {
        NoOfChunksStep withChunkSize(int chunkSize);
    }

    public static interface NoOfChunksStep {
        SourceFileSizeStep withNoOfChunks(int noOfChunks);
    }

    public static interface SourceFileSizeStep {
        BuildStep withSourceFileSize(long sourceFileSize);
    }

    public static interface BuildStep {
        MetaData build();
    }

    public static class Builder implements ChunkSizeStep, NoOfChunksStep, SourceFileSizeStep, BuildStep {
        private int chunkSize;
        private int noOfChunks;
        private long sourceFileSize;

        private Builder() {
        }

        public static ChunkSizeStep metaData() {
            return new Builder();
        }

        @Override
        public NoOfChunksStep withChunkSize(int chunkSize) {
            this.chunkSize = chunkSize;
            return this;
        }

        @Override
        public SourceFileSizeStep withNoOfChunks(int noOfChunks) {
            this.noOfChunks = noOfChunks;
            return this;
        }

        @Override
        public BuildStep withSourceFileSize(long sourceFileSize) {
            this.sourceFileSize = sourceFileSize;
            return this;
        }

        @Override
        public MetaData build() {
            return new MetaData(
                this.chunkSize,
                this.noOfChunks,
                this.sourceFileSize
            );
        }
    }
}
