/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import lab3.model.interfaces.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Identifiable, Storage {
    protected String name;
    protected String address;
    protected String location;
    protected Map<String, Integer> timeCost;

    public abstract String getName();

    public abstract String getAddress();

    public void createMap(String[] nodeList, Integer[] connTimeCost) {
        if (nodeList.length != connTimeCost.length) {
            throw new IllegalArgumentException(
                    "The number of nodes doesn't match the number of time costs.");
        }

        timeCost = new HashMap<>();
        if (nodeList.length != 0)
            for (int index = 0; index < nodeList.length; index++) {
                timeCost.put(nodeList[index], connTimeCost[index]);
            }
    }
}
