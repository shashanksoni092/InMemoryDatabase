package common;
import java.io.File;
import java.util.List;
import common.StringToInteger;
import javax.swing.JOptionPane;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.model.PredictRequest;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Prediction;

public class PredictAdminViaClarifaiAPI {
	
	public double getResponse(String path) {
		
		try {
			ClarifaiClient client = new ClarifaiBuilder("36dd0cffd3764b5998e9b59a383a38c3").buildSync();

			// Predicts the model
			PredictRequest<Prediction> request = client.predict("adminPrediction")
				    .withInputs(ClarifaiInput.forImage(new File(path)));
			
			// Stores the response
			List <ClarifaiOutput<Prediction>> result = request.executeSync().get();
			
			String resultString = result.toString();
			
			// getting the predicted value of Admin - shashank soni   
			int startIndex = resultString.indexOf("Concept{id=shashank soni, name=shashank soni,", 0);
			startIndex = resultString.indexOf(" value=", startIndex);
			
			// value=0.21268915 need to extract this value 
			startIndex = startIndex + 7;
			
			char getValue [] = new char[20];
			int j=0;

			for(int i=startIndex; i<=startIndex+9; i++) {
				getValue[j++] = resultString.charAt(i);
			}
			
			StringToInteger obj1 = new StringToInteger();
			Double value = obj1.parseInt(getValue);
			
			return value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "API Call was not Successful!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return 0L;
	}

}	
