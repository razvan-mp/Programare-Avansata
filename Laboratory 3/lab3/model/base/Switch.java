/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

public class Switch extends Node {
    public Switch(String name, String address, String location) {
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

    @Override
    public String getLocation() {
        return super.location;
    }
}
