package ba.unsa.etf.bp.udat.Predictions;

import ba.unsa.etf.bp.udat.services.EnrollmentFactService;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import weka.classifiers.trees.M5P;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

public class PredictionModel
{
    
    Instances dataset;
    Instances inputValue;
    M5P cls;
    static String DATASET_NAME ="dataset.arff";
    static String INPUT_VALUE_NAME ="inputValue.arff";

    ArrayList<String> write_header()
    {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("@relation data");
        lines.add("@attribute enrolled numeric");
        lines.add("@attribute budget numeric");
        lines.add("@data");

        return lines;
    }

    void createDataSet(EnrollmentFactService service)
    {
        ArrayList<String> lines = write_header();
        for(int i = 2017; i > 2005; i--)
        {
            Integer enrolled = service.filterByAcademicYear(i);
            Integer onBudget = service.filterByAcademicYearBudget(i,Boolean.FALSE);
            lines.add(enrolled.toString() + "," + onBudget.toString());
        }
        Path file = Paths.get(DATASET_NAME);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void insertInputValue(Integer value)
    {
        ArrayList<String> lines = write_header();
        lines.add("?,"+value.toString());
        Path file  = Paths.get(INPUT_VALUE_NAME);
        try
        {
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void initializeDataSet(EnrollmentFactService service) throws IOException
    {
        this.createDataSet(service);
        BufferedReader reader;
		reader = new BufferedReader(new FileReader(DATASET_NAME));
        dataset = new Instances(reader);
        reader.close();
    }

    public void initializeInputValue(Integer value) throws IOException
    {
        this.insertInputValue(value);
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(INPUT_VALUE_NAME));
        inputValue = new Instances(reader);
        reader.close();
    }

    public void createClassifier() throws Exception
    {
        if(dataset != null)
        {
            // setting class attribute
            dataset.setClassIndex(0); // Prediction for enrollment 
            cls = new M5P();
            // building classifier
            cls.buildClassifier(dataset);
        }
    }
    public void evaluateClassifier() throws Exception
    {
        // Evaluate classifier
        if(cls != null)
        {
            Evaluation eval = new Evaluation(dataset);
            eval.evaluateModel(cls, dataset);
            //System.out.println(eval.toSummaryString("\nResults\n===============\n", true));    
        }
    }

    public int predictEnrollment() throws Exception
    {
        int rounded_result = -1;
        if(cls != null)
        {
            double result_prediction = cls.classifyInstance(inputValue.instance(0));
            rounded_result = (int) Math.round(result_prediction);    
        }
        return rounded_result;
    }

    public PredictionModel() {}

}