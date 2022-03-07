/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import lab3.model.interfaces.Identifiable;

public class Router extends Node implements Identifiable {
    private String IP;

    public Router(String name, String address, String location) {
        super.name = name;
        super.address = address;
        super.location = location;
    }

    public Router(String name, String address, String location, String IP) {
        super.name = name;
        super.address = address;
        super.location = location;
        this.IP = IP;
    }

    public String getIP() {
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
