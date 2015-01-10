/**
 * Created by amaxilatis on 1/10/15.
 */
package eu.amaxilat.jubatus.jubaanomaly.test;

import eu.amaxilat.jubatus.jubaanomaly.util.Utils;
import junit.framework.Assert;
import us.jubat.anomaly.AnomalyClient;
import us.jubat.classifier.LabeledDatum;

public class RandomDataTest {

    private static LabeledDatum makeTrainDatum(String label, double reading) {
        return new LabeledDatum(label, Utils.makeNumberDatum("reading", reading));
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 9199;
        String name = "ML";

        AnomalyClient client = new AnomalyClient(host, port, name, 1);

        int trainDataSize = 1000;
        System.out.println("Start Training(" + trainDataSize + ")...");

        for (int i = 0; i < trainDataSize; i++) {
            //generate train data
            client.add(Utils.makeNumberDatum("reading", Math.random()));
        }
        System.out.println("Trained!(" + trainDataSize + ")");


        int testDataSize = 1000;
        System.out.println("Testing(" + testDataSize + ")...");

        int offset = 1000;
        for (int i = 0; i < testDataSize; i++) {
            //runTests
            float normalValueScore = client.calcScore(Utils.makeNumberDatum("reading", Math.random()));
            float abnormalValueScore = client.calcScore(Utils.makeNumberDatum("reading", offset + Math.random()));
            Assert.assertFalse(Float.isInfinite(normalValueScore));
            Assert.assertTrue(Float.isInfinite(abnormalValueScore));
        }
        System.out.println("All tests passed!");

    }
}
