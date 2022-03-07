/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import lab3.model.interfaces.Identifiable;
import lab3.model.interfaces.Storage;

public class Computer extends Node implements Storage, Identifiable {
    private int storage;
    private String IP;

    public Computer(String name, String address, String location) {
        this.name = name;
        this.address = address;
        this.location = location;
    }

    public Computer(String name, String address, String location, int storage) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.storage = storage;
    }

    public Computer(String name, String address, String location, int storage, String IP) {
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

    @Override
    public String getLocation() {
        return super.location;
    }
}
