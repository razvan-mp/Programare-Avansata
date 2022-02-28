public class Program {
    public static void main(String[] args) {
        Event C1 = new Event("C1", 100, 8, 10);
        Event C2 = new Event("C2", 100, 10, 12);
        Event L1 = new Event("L1", 30, 8, 10);
        Event L2 = new Event("L2", 30, 8, 10);
        Event L3 = new Event("L3", 30, 10, 12);

        Room R1 = new Room("401", RoomType.lab, 30);
        Room R2 = new Room("403", RoomType.lab, 30);
        Room R3 = new Room("405", RoomType.lab, 30);
        Room R4 = new Room("309", RoomType.lectureHall, 100);

        System.out.println(C1 + "\n" + C2 + "\n" + L1 + "\n" + L2 + "\n" + L3);
        System.out.println();
        System.out.println(R1 + "\n" + R2 + "\n" + R3 + "\n" + R4);
    }
}
