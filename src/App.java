import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Network network = new Network(784, 100, 10);

        /*
         * training
         */
        /*
         * String training_images = "training_data\\train-images.idx3-ubyte";
         * String training_labels = "training_data\\train-labels.idx1-ubyte";
         * Idx3Reader training_reader = new Idx3Reader(training_images,
         * training_labels);
         * 
         * for (int i = 0; i < 20000; i++) {
         * int[][][] image = training_reader.getImage(i);
         * int label = training_reader.getLabel(i);
         * double learningRate = 0.1;
         * 
         * if (i > 19990) {
         * System.out.println("Before training");
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
         * System.out.println("label: " + label);
         * System.out.println("prediction: " + network.predict(image));
         * }
         * 
         * network.train(image, label, learningRate);
         * 
         * if (i > 19990) {
         * System.out.println();
         * System.out.println("After training");
         * System.out.println("label: " + label);
         * System.out.println("prediction: " + network.predict(image));
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
         * }
         * }
         * training_reader.close();
         * 
         * network.saveWeightsAndBiases("models\\v3.ser");
         */

        /*
         * testing
         */

        network.loadWeightsAndBiases("models\\v3.ser");
        String images = "test_data\\t10k-images.idx3-ubyte";
        String labels = "test_data\\t10k-labels.idx1-ubyte";
        Idx3Reader reader = new Idx3Reader(images, labels);

        for (int i = 0; i < 20000; i++) {
            int[][][] image = reader.getImage(i);
            int label = reader.getLabel(i + 1);

            for (int[][] row : image) {
                for (int[] pixel : row) {
                    if (pixel[0] < 128) {
                        System.out.print(".");
                    } else {
                        System.out.print("#");
                    }
                }
                System.out.println();
            }
            System.out.println("label: " + label);
            System.out.println("prediction: " + network.predict(image));
        }
        reader.close();
    }
}
