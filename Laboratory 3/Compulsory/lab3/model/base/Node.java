/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import lab3.model.interfaces.*;

public abstract class Node implements Identifiable, Storage {
    protected String name;
    protected String address;
    protected String location;

    public abstract String getName();
    public abstract String getAddress();
    public abstract String getLocation();
}
