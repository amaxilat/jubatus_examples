package eu.amaxilat.jubatus.jubaanomaly.util;

import us.jubat.common.Datum;

/**
 * Created by amaxilatis on 1/10/15.
 */
public class Utils {

    public static Datum makeNumberDatum(final String label, double reading) {
        return new Datum().addNumber(label, reading);
    }
}
