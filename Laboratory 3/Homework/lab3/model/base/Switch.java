/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import java.util.List;

public class Switch extends Node {
    public Switch(String name, String address, String location, String[] nodeList, Integer[] timeCost) {
        createMap(nodeList, timeCost);
        super.name = name;
        super.address = address;
        super.location = location;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public String getAddress() {
        return super.address;
    }
}
