/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import lab3.model.interfaces.Identifiable;
import lab3.model.interfaces.Storage;

public class Computer extends Node {
    private final int storage;
    private final String IP;

    protected Computer() {
        this.storage = 0;
        this.IP = null;
    }

    public Computer(String name, String address, String location, int storage, String IP, String[] nodeList, Integer[] timeCost) {
        createMap(nodeList, timeCost);
        this.name = name;
        this.address = address;
        this.location = location;
        this.storage = storage;
        this.IP = IP;
    }

    public int getStorage() {
        return this.storage;
    }

    public String getIP() {
        if (this.IP == null)
            return "no IP";
        return this.IP;
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
