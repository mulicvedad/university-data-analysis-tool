package ba.unsa.etf.bp.udat.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ba.unsa.etf.bp.udat.Predictions.PredictionModel;
import ba.unsa.etf.bp.udat.services.EnrollmentFactService;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@EnableAutoConfiguration

public class PredictionController
{
    @Autowired
    EnrollmentFactService service;

    @ResponseBody
    @GetMapping("/prediction/enrollment")
    public int predictionEnrollment(@RequestParam("budget_students") Integer budgetStudents) {
        PredictionModel modelGenerator = new PredictionModel();
        int enrolled_prediction = 0;
        try {
             modelGenerator.initializeDataSet(service);
             modelGenerator.initializeInputValue(budgetStudents);
             modelGenerator.createClassifier();
             modelGenerator.evaluateClassifier();
             enrolled_prediction = modelGenerator.predictEnrollment();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return enrolled_prediction;
    }
    
}