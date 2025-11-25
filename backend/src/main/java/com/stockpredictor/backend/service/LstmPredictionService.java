// package com.stockpredictor.backend.service;

// import org.tensorflow.SavedModelBundle;
// import org.tensorflow.Tensor;
// import org.tensorflow.ndarray.FloatNdArray;
// import org.tensorflow.ndarray.NdArrays;
// import org.tensorflow.types.TFloat32;
// import org.springframework.stereotype.Service;

// import java.io.FileInputStream;
// import java.io.ObjectInputStream;
// import java.util.List;

// @Service
// public class LstmPredictionService {

//     private final SavedModelBundle model;
//     private final MinMaxScaler scaler;

//     public LstmPredictionService() throws Exception {
//         // Load model
//         this.model = SavedModelBundle.load("src/main/resources/model/stock_model");

//         // Load scaler
//         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/model/scaler.save"));
//         this.scaler = (MinMaxScaler) ois.readObject();
//         ois.close();
//     }

//     public float predictNextPrice(List<Float> last60Prices) {

//         // Convert list → scaled 2D array shape (1, 60, 1)
//         float[] arr = new float[last60Prices.size()];
//         for (int i = 0; i < last60Prices.size(); i++) {
//             arr[i] = scaler.scale(last60Prices.get(i));
//         }

//         // Create tensor
//         FloatNdArray inputData = NdArrays.ofFloats(new long[]{1, 60, 1});
//         for (int i = 0; i < 60; i++) {
//             inputData.setFloat(arr[i], 0, i, 0);
//         }

//         try (Tensor<TFloat32> inputTensor = TFloat32.tensorOf(inputData)) {

//             // Run prediction
//             Tensor<TFloat32> outputTensor = model.session()
//                     .runner()
//                     .feed("serving_default_input_1", inputTensor)
//                     .fetch("StatefulPartitionedCall")
//                     .run()
//                     .get(0)
//                     .expect(TFloat32.DTYPE);

//             float scaledPredicted = outputTensor.rawData().asFloats().getFloat(0);

//             // inverse transform
//             return scaler.inverseScale(scaledPredicted);
//         }
//     }
// }
