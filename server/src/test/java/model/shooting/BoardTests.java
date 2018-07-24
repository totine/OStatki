package model.shooting;

import model.Coordinates;
import model.placement.fleet.FleetBuilder;
import model.shooting.field.states.FieldStateName;
import model.shooting.field.HitResult;
import model.shooting.board.ShootingBoard;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;
import org.testng.asserts.SoftAssert;

import static model.shooting.field.states.FieldStateName.DAMAGED;
import static model.shooting.field.states.FieldStateName.DESTROYED;
import static model.shooting.field.states.FieldStateName.SEEN;
import static model.shooting.field.states.FieldStateName.FLOATING;
import static model.shooting.field.HitResult.OUT_OF_BOARD;

import static model.placement.ship.ShipType.ONE_MAST;
import static model.placement.ship.ShipType.TWO_MAST;
import static model.placement.ship.ShipType.THREE_MAST;

import static model.placement.ship.Direction.SOUTH;

public class BoardTests {
    private int rows = 10;
    private int cols = 10;
    private ShootingBoard shootingBoard;

    @DataProvider
    public static Object[][] emptyBoardMisses() {
        return new Object[][]{{0, 0}, {9, 9}, {5, 5}, {1, 1}, {8, 7}};
    }

    @BeforeMethod
    private void setUp() {
        shootingBoard = ShootingBoard.createEmpty(rows, cols);
    }


    @Test(dataProvider = "emptyBoardMisses")
    public void givenNewBoard_whenShooting_thenMiss(int coordX, int coordY) {
        // given
        ShootingBoard shootingBoard = ShootingBoard.createEmpty(rows, cols);
        // when
        HitResult hitResult = shootingBoard.hit(Coordinates.create(coordX, coordY));
        // then
        Assert.assertEquals(hitResult, HitResult.MISS);
    }

    @Test(dataProvider = "emptyBoardMisses")
    public void givenNewBoard_whenShootingTwice_thenMissAgain(int coordX, int coordY) {
        // given
        ShootingBoard shootingBoard = ShootingBoard.createEmpty(rows, cols);
        // when
        shootingBoard.hit(Coordinates.create(coordX, coordY));
        HitResult hitResult = shootingBoard.hit(Coordinates.create(coordX, coordY));
        // then
        Assert.assertEquals(hitResult, HitResult.MISS);
    }

    @Test
    public void givenBoardWithOneMastShip_whenShootingMast_thenHit() {
        // given
        Coordinates shipHeadCoordinates = Coordinates.create(1, 1);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(shipHeadCoordinates, ONE_MAST, SOUTH)
                .build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when
        HitResult hitResult = shootingBoard.hit(shipHeadCoordinates);
        // then
        Assert.assertEquals(hitResult, HitResult.HIT);

    }

    @Test
    public void givenBoardWithTwoMastShip_whenShootingMast_thenHit() {
        // given
        Coordinates shipHeadCoordinates = Coordinates.create(1, 1);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(shipHeadCoordinates, TWO_MAST, SOUTH)
                .build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when
        HitResult hitResult = shootingBoard.hit(shipHeadCoordinates);
        // then
        Assert.assertEquals(hitResult, HitResult.HIT);
        Assert.assertEquals(shootingBoard.getStateName(shipHeadCoordinates), DAMAGED);
    }

    @Test
    public void givenBoardWithShip_whenShootingWithMastTwice_thenMiss() {
        // given
        Coordinates shipHeadCoordinates = Coordinates.create(1, 1);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(shipHeadCoordinates, ONE_MAST, SOUTH)
                .build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when
        shootingBoard.hit(shipHeadCoordinates);
        HitResult hitResult = shootingBoard.hit(shipHeadCoordinates);
        // then
        Assert.assertEquals(hitResult, HitResult.MISS);
    }

    @Test
    public void givenBoardWithShip_whenShootingFieldWithoutMast_thenMiss() {
        // given
        Coordinates shipHeadCoordinates = Coordinates.create(1, 1);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(shipHeadCoordinates, ONE_MAST, SOUTH).build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when
        HitResult hitResult = shootingBoard.hit(Coordinates.create(2, 2));
        // then
        Assert.assertEquals(hitResult, HitResult.MISS);
    }

    @Test
    public void givenBoardWithOneMastShip_whenShootingFieldWithMast_thenFieldHasDestroyedState() {
        // given
        Coordinates coordinates = Coordinates.create(1, 1);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(coordinates, ONE_MAST, SOUTH).build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when
        HitResult hitResult = shootingBoard.hit(Coordinates.create(1, 1));
        // then
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(1, 1)), DESTROYED);
    }

    @Test
    public void givenBoard_whenShootingOutOfBoard_thenHitResultIsOutOfBoard() {
        Coordinates coordinates = Coordinates.create(1, 1);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(coordinates, ONE_MAST, SOUTH).build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when
        HitResult hitResult = shootingBoard.hit(Coordinates.create(-1, -1));
        HitResult hitResult2 = shootingBoard.hit(Coordinates.create(-13, 5));
        HitResult hitResult3 = shootingBoard.hit(Coordinates.create(6, 14));
        // then
        Assert.assertEquals(hitResult, OUT_OF_BOARD);
        Assert.assertEquals(hitResult2, OUT_OF_BOARD);
        Assert.assertEquals(hitResult3, OUT_OF_BOARD);
    }

    @Test
    public void givenBoardWithTwoMastShip_whenShottingBothMasts_firstlyDamaged_thenDestroyed() {
        // given
        Coordinates coordinates = Coordinates.create(3, 3);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(coordinates, TWO_MAST, SOUTH)
                .build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when first hit #1
        shootingBoard.hit(Coordinates.create(3, 3));
        // then
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(3, 3)), DAMAGED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(3, 4)), FLOATING);
        // when second and LAST hit #2
        shootingBoard.hit(Coordinates.create(3, 4));
        // then
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(3, 3)), DESTROYED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(3, 4)), DESTROYED);
    }

    @Test
    public void givenBoardWithThreeMastsShip_whenShootingAllMasts_firstlyDamaged_thenDamaged_thenDestroyed() {
        // given
        Coordinates coordinates = Coordinates.create(4, 5);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(coordinates, THREE_MAST, SOUTH).build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when: first hit #1
        shootingBoard.hit(Coordinates.create(4, 6));
        // then:
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 6)), DAMAGED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 5)), FLOATING);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 7)), FLOATING);
        // when: second hit #2
        shootingBoard.hit(Coordinates.create(4, 5));
        // then:
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 6)), DAMAGED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 5)), DAMAGED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 7)), FLOATING);
        // when: third and LAST hit #3
        shootingBoard.hit(Coordinates.create(4, 7));
        // then:
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 6)), DESTROYED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 5)), DESTROYED);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(4, 7)), DESTROYED);
        int[][] coordinatesThatShouldBeSeen = new int[][]{{3, 4}, {3, 5}, {3, 6}, {3, 7}, {3, 8}, {4, 4}, {5, 4},
                {5, 5}, {5, 6}, {5, 7}, {5, 8}, {4, 8}};

        multipleAssertEqualsIsExpectedFieldState(SEEN, coordinatesThatShouldBeSeen);
        Assert.assertEquals(shootingBoard.getStateName(Coordinates.create(10, 4)),
                FieldStateName.OUT_OF_BOARD);
    }


    @Test
    public void givenBoardWithTwoMastsShipOnEdge_whenShootingAllMasts_firstlyDamaged_thenDamaged_thenDestroyed() {
        // given
        Coordinates shipHeadCoordinates = Coordinates.create(9, 5);
        Fleet<PlacedShip> fleet = FleetBuilder.create().appendShip(shipHeadCoordinates, THREE_MAST, SOUTH).build();
        shootingBoard = ShootingBoard.fromFleet(fleet);
        // when: first hit #1
        shootingBoard.hit(Coordinates.create(9, 6));
        // then:
        multipleAssertEqualsIsExpectedFieldState(DAMAGED, new int[][]{{9, 6}});
        multipleAssertEqualsIsExpectedFieldState(FLOATING, new int[][]{{9, 5}, {9, 7}});

        // when: second hit #2
        shootingBoard.hit(Coordinates.create(9, 5));
        // then:
        multipleAssertEqualsIsExpectedFieldState(DAMAGED, new int[][]{{9, 6}, {9, 5}});
        multipleAssertEqualsIsExpectedFieldState(FLOATING, new int[][]{{9, 7}});



        multipleAssertEqualsIsExpectedFieldState(FLOATING, new int[][]{{9, 5}, {9, 7}});

        // when: third and LAST hit #3
        shootingBoard.hit(Coordinates.create(9, 7));

        // then:
        int[][] coordinatesThatShouldBeDestroyed = new int[][]{{9, 6}, {9, 5}, {9, 7}};
        multipleAssertEqualsIsExpectedFieldState(DESTROYED, coordinatesThatShouldBeDestroyed);


        int[][] coordinatesThatShouldBeSeen = new int[][]{{8, 4}, {8, 5}, {8, 6}, {8, 7}, {8, 8}, {9, 8}, {9, 4}};
        multipleAssertEqualsIsExpectedFieldState(SEEN, coordinatesThatShouldBeSeen);
    }

    private void multipleAssertEqualsIsExpectedFieldState(FieldStateName expectedFieldStateName, int[][] coordinates) {
        SoftAssert softAssertion = new SoftAssert();
        for (int[] coord : coordinates) {
            softAssertion.assertEquals(shootingBoard.getStateName(Coordinates.create(coord[0], coord[1])),
                    expectedFieldStateName);
        }
    }

}
