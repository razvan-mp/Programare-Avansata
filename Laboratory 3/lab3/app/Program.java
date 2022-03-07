/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.app;
import lab3.model.base.*;

public class Program {
    public static void main(String[] args) {
        Network myNetwork = new Network();

        Node v1 = new Computer("v1", "AB12.7809.0003 ", "Home Office", 200);
        Node v2 = new Router("v2", "AC00.8900.1323", "Home Office", "192.168.0.1:500");
        Node v3 = new Switch("v3", "00F0.187C.ABFF", "Main Office");
        Node v4 = new Computer("v4", "DE3E.3122.0391", "Main Office", 400, "127.0.0.1:255");
        Node v5 = new Router("v5", "DE4F.DAC0.0126", "Main Office");
        Node v6 = new Switch("v6", "85A8.3189.8596", "Main Office");
        Node v7 = new Switch("v6", "85A8.3189.8596", "Main Office");
        Node v8 = new Switch("v7", "85A8.3189.8596", "Main Office");
        Node v9 = new Computer("v8", "ASDA.3122.0391", "Main Office", 400, "127.0.0.1:255");

        System.out.println(myNetwork.addNode(v1));
        System.out.println(myNetwork.addNode(v2));
        System.out.println(myNetwork.addNode(v3));
        System.out.println(myNetwork.addNode(v4));
        System.out.println(myNetwork.addNode(v5));
        System.out.println(myNetwork.addNode(v6));
        System.out.println(myNetwork.addNode(v7));
        System.out.println(myNetwork.addNode(v8));
        System.out.println(myNetwork.addNode(v9));

        System.out.println(myNetwork);
    }
}
