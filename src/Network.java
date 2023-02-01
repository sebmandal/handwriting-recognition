public class Network {
    private int inputSize;
    private int hiddenSize;
    private int outputSize;
    private double[][] inputWeights;
    private double[][] hiddenWeights;
    private double[] hiddenBiases;
    private double[] outputBiases;

    public Network(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;

        inputWeights = new double[inputSize][hiddenSize];
        hiddenWeights = new double[hiddenSize][outputSize];
        hiddenBiases = new double[hiddenSize];
        outputBiases = new double[outputSize];
    }

    public void train(int[][][] image, int label, double learningRate) {
        // Perform forward propagation
        double[] hiddenLayer = calculateHiddenLayer(image);
        double[] outputLayer = calculateOutputLayer(hiddenLayer);

        // Perform backpropagation
        double[] outputErrors = calculateOutputErrors(outputLayer, label);
        double[] hiddenErrors = calculateHiddenErrors(outputErrors, hiddenLayer);

        // Update weights and biases
        updateWeights(hiddenLayer, outputErrors, hiddenErrors, learningRate, image);
        updateBiases(outputErrors, hiddenErrors, learningRate);
    }

    public int predict(int[][][] image) {
        double[] hiddenLayer = calculateHiddenLayer(image);
        double[] outputLayer = calculateOutputLayer(hiddenLayer);

        int prediction = 0;
        double maxValue = Double.NEGATIVE_INFINITY;
        System.out.println(outputLayer);

        for (int i = 0; i < outputSize; i++) {
            if (outputLayer[i] > maxValue) {
                maxValue = outputLayer[i];
                prediction = i;
            }
        }

        return prediction;
    }

    private double[] calculateHiddenLayer(int[][][] image) {
        double[] hiddenLayer = new double[hiddenSize];
        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < inputSize - 1; j++) {
                // System.out.println(image.length + " " + image[j / 28].length + " and " + j /
                // 28 + " " + i % 28);
                hiddenLayer[i] += image[j / 28][j % 28][0] * inputWeights[j][i];
            }
            hiddenLayer[i] = sigmoid(hiddenLayer[i] + hiddenBiases[i]);
        }
        return hiddenLayer;
    }

    private double[] calculateOutputLayer(double[] hiddenLayer) {
        double[] outputLayer = new double[outputSize];
        for (int i = 0; i < outputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                outputLayer[i] += hiddenLayer[j] * hiddenWeights[j][i];
            }
            outputLayer[i] = sigmoid(outputLayer[i] + outputBiases[i]);
        }
        return outputLayer;
    }

    private double[] calculateOutputErrors(double[] outputLayer, int label) {
        double[] outputErrors = new double[outputSize];
        for (int i = 0; i < outputSize; i++) {
            outputErrors[i] = (i == label ? 1.0 : 0.0) - outputLayer[i];
        }
        return outputErrors;
    }

    private double[] calculateHiddenErrors(double[] outputErrors, double[] hiddenLayer) {
        double[] hiddenErrors = new double[hiddenSize];
        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                hiddenErrors[i] += outputErrors[j] * hiddenWeights[i][j];
            }
            hiddenErrors[i] *= sigmoidDerivative(hiddenLayer[i]);
        }
        return hiddenErrors;
    }

    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private double sigmoidDerivative(double x) {
        return x * (1.0 - x);
    }

    private void updateWeights(double[] hiddenLayer, double[] outputErrors, double[] hiddenErrors, double learningRate,
            int[][][] image) {
        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                hiddenWeights[i][j] += learningRate * outputErrors[j] * hiddenLayer[i];
            }
        }

        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                inputWeights[i][j] += learningRate * hiddenErrors[j] * image[i / 28][i % 28][0];
            }
        }
    }

    private void updateBiases(double[] outputErrors, double[] hiddenErrors, double learningRate) {
        for (int i = 0; i < outputSize; i++) {
            outputBiases[i] += learningRate * outputErrors[i];
        }

        for (int i = 0; i < hiddenSize; i++) {
            hiddenBiases[i] += learningRate * hiddenErrors[i];
        }
    }

}
