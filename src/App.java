import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        String images = "C:\\Users\\Sebastian\\Desktop\\handwriting-recognition\\training_data\\train-images.idx3-ubyte";
        String labels = "C:\\Users\\Sebastian\\Desktop\\handwriting-recognition\\training_data\\train-labels.idx1-ubyte";
        Idx3Reader reader = new Idx3Reader(images, labels);
        Network network = new Network(784, 100, 10);

        for (int i = 0; i < reader.getImagesLength(); i++) {
            int[][][] image = reader.getImage(i);
            int label = reader.getLabel(i);
            double learningRate = 0.1;

            /*
             * VISUALIZING
             * 
             * System.out.println("Label: " + label);
             * for (int[][] row : image) {
             * for (int[] pixel : row) {
             * if (pixel[0] < 128) {
             * System.out.print(".");
             * } else {
             * System.out.print("#");
             * }
             * }
             * System.out.println();
             * }
             */

            network.train(image, label, learningRate);
        }

        reader.close();
    }
}
