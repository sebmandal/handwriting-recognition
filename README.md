# MNIST Digit Recognition with Neural Network

This project uses a neural network to recognize digits from the MNIST dataset. The network is trained on a set of images of handwritten digits and can predict the digit in a new image.

## Requirements

- Java 1.8 or later

## Setup

- Clone the repository
- Navigate to the project directory in your terminal
- Rename the paths to the training/test sets in App.java
- Compile the Java files:

```javac *.java```

- Run the program:

```java App```

## MNIST Dataset

The MNIST dataset consists of 60,000 training images and 10,000 test images of handwritten digits. The images are 28x28 grayscale images, and the labels are the digits (0-9) that the images represent.

## Neural Network

The neural network consists of an input layer, hidden layer, and output layer. The input layer has 784 nodes (28x28 image size), the hidden layer has 128 nodes, and the output layer has 10 nodes (representing the digits 0-9). The network is trained using backpropagation.

## App

The `App` class uses the `Idx3Reader` class to read in the images and labels from the MNIST dataset. It then trains the neural network on the training data and evaluates its performance on the test data.

## Limitations

The current implementation has a hardcoded learning rate and number of epochs, and these may not be optimal for all datasets. Additionally, the network architecture and hyperparameters can be improved for better performance.
