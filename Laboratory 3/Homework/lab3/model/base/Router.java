/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

public class Router extends Node {
    private final String IP;

    protected Router() {
        this.IP = null;
    }

    public Router(String name, String address, String location, String IP, String[] nodeList, Integer[] timeCost) {
        createMap(nodeList, timeCost);
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
}
