import java.io.IOException;
import java.io.RandomAccessFile;

public class Idx3Reader {
    private RandomAccessFile images;
    private RandomAccessFile labels;
    private int magicNumber;
    private int numImages;
    private int numRows;
    private int numCols;

    public Idx3Reader(String imagesPath, String labelsPath) throws IOException {
        images = new RandomAccessFile(imagesPath, "r");
        labels = new RandomAccessFile(labelsPath, "r");

        magicNumber = images.readInt();
        if (magicNumber != 2051) {
            throw new IOException("Invalid magic number: " + magicNumber);
        }

        numImages = images.readInt();
        numRows = images.readInt();
        numCols = images.readInt();
    }

    public int[][][] getImage(int index) throws IOException {
        int[][][] image = new int[numRows][numCols][1];
        long position = 16 + index * numRows * numCols;

        images.seek(position);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                image[i][j][0] = images.readUnsignedByte();
            }
        }

        return image;
    }

    public int getLabel(int index) throws IOException {
        long position = 8 + index;

        labels.seek(position);
        return labels.readUnsignedByte();
    }

    public void close() throws IOException {
        images.close();
        labels.close();
    }

    public int getImagesLength() {
        return numImages;
    }
}
