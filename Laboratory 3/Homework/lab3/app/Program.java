/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.app;

import lab3.model.base.*;

public class Program {
    public static void main(String[] args) {
        Network myNetwork = new Network();

        // creating nodes
        Computer v1 = new Computer("v1", "AB12.7809.0003 ", "Home Office", 200, "165.72.012.2", new String[]{"v2", "v3"}, new Integer[]{10, 50});
        Node v2 = new Router("v2", "AC00.8900.1323", "Home Office", "192.168.0.1:500", new String[]{"v3", "v4", "v5"}, new Integer[]{20, 20, 10});
        Node v3 = new Switch("v3", "00F0.187C.ABFF", "Main Office", new String[]{"v4"}, new Integer[]{20});
        Node v4 = new Switch("v4", "DE3E.3122.0391", "Main Office", new String[]{"v5", "v6"}, new Integer[]{30, 10});
        Node v5 = new Router("v5", "DE4F.DAC0.0126", "Main Office", "127.0.0.2", new String[]{"v6"}, new Integer[]{20});
        Node v6 = new Computer("v6", "85A8.3189.8596", "Main Office", 400, "127.0.0.4" , new String[]{}, new Integer[]{});

        // inserting nodes
        System.out.println(myNetwork.addNode(v1));
        System.out.println(myNetwork.addNode(v2));
        System.out.println(myNetwork.addNode(v3));
        System.out.println(myNetwork.addNode(v4));
        System.out.println(myNetwork.addNode(v5));
        System.out.println(myNetwork.addNode(v6));

        System.out.print("Storage in GB for v1: ");
        System.out.println(v1.getStorage());

        System.out.print("Storage in MB for v1: ");
        System.out.println(v1.getStorage(Unit.MB, v1.getStorage()));

        System.out.println(myNetwork);

//        System.out.println("Identifiable nodes sorted by address: ");
//        myNetwork.displayIdentifiable();

        myNetwork.dijkstra();
    }

}
