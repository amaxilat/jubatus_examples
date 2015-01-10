/**
 * Created by amaxilatis on 1/10/15.
 */
package eu.amaxilat.jubatus.jubaanomaly.test;

import eu.amaxilat.jubatus.jubaanomaly.util.FileUtils;
import eu.amaxilat.jubatus.jubaanomaly.util.Utils;
import us.jubat.anomaly.AnomalyClient;
import us.jubat.classifier.LabeledDatum;

public class TemperatureDataTest {

    private static LabeledDatum makeTrainDatum(String label, double reading) {
        return new LabeledDatum(label, Utils.makeNumberDatum("reading", reading));
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 9199;
        String name = "ML";

        AnomalyClient client = new AnomalyClient(host, port, name, 1);

        final String trainFileName = args[0];
        final String testFileName = args[1];
        System.out.println("Start Training(" + trainFileName + ")...");

        for (final String line : FileUtils.readLines(trainFileName)) {
            //generate train data
            client.add(Utils.makeNumberDatum("reading", Double.parseDouble(line)));
        }
        System.out.println("Trained!(" + trainFileName + ")");


        System.out.println("Testing(" + testFileName + ")...");

        for (final String line : FileUtils.readLines(trainFileName)) {
            //runTests
            float valueScore = client.calcScore(Utils.makeNumberDatum("reading", Double.parseDouble(line)));
            System.out.println(valueScore);
        }
        System.out.println("All tests passed!");

    }
}
