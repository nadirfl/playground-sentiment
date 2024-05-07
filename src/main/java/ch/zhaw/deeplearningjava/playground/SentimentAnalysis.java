package ch.zhaw.deeplearningjava.playground;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.djl.Application;
import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

public class SentimentAnalysis {
    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalysis.class);

	public static Classifications predict(String input) throws MalformedModelException, ModelNotFoundException, IOException, TranslateException {
		logger.info("input sentence: {}", input);

		Criteria<String, Classifications> criteria = 
			Criteria.builder()
			.optApplication(Application.NLP.SENTIMENT_ANALYSIS)
			.setTypes(String.class, Classifications.class)
			.optDevice(Device.cpu())
			.optProgress(new ProgressBar())
			.build();
	
		try (ZooModel<String, Classifications> model = criteria.loadModel();
			Predictor<String, Classifications> predictor = model.newPredictor()) {
				return predictor.predict(input);
			}

	}
}
