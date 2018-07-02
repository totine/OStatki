package placement.model;

public class Main {
    public static void main(String[] args) {
        Fleet fleet = new Fleet();
        Board board = new Board();
        RandomBoardCreator randomBoardCreator = new RandomBoardCreator(fleet, board);
        Fleet fleetToSend = randomBoardCreator.generateFleet();
        System.out.println(board);
    }
}
