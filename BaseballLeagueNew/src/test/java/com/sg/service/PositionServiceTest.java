package com.sg.service;

import com.sg.dao.PositionDao;
import com.sg.dto.Player;
import com.sg.dto.PlayerPosition;
import com.sg.dto.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PositionServiceTest {

    @Inject
    PositionService positionService;

    @Inject
    PlayerService playerService;

    @Inject
    PlayerPositionService playerPositionService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Position position = new Position();
        position.setName("P");

        //Act
        Position createdPosition = positionService.create(position);

        //Assert
        assert createdPosition.getId() != null;
        assert "P".equals(createdPosition.getName());
    }

    @Test
    public void read() {

        //Arrange
        Position position = new Position();
        position.setName("P");
        //Act
        Position createdPosition = positionService.create(position);

        //Act
        Position readPosition = positionService.read(createdPosition.getId());
        //Assert
        assert "P".equals(readPosition.getName());
    }

    @Test
    public void update() {

        //Arrange
        Position position = new Position();
        position.setName("P");

        Position createdPosition = positionService.create(position);

        Position readPosition = positionService.read(createdPosition.getId());

        readPosition.setName("2B");



        //Act
        positionService.update(readPosition);

        //Assert
        Position updatePosition = positionService.read(readPosition.getId());

        assert "2B".equals(updatePosition.getName());
    }

    @Test
    public void delete() {

        //Arrange
        Position position = new Position();

        position.setName("P");

        Position createdPosition = positionService.create(position);

        assert createdPosition.getId() != null;

        //Act
        positionService.delete(createdPosition);

        //Assert
        Position readPosition = positionService.read(createdPosition.getId());

        assert readPosition == null;
    }

    @Test
    public void getPositionsByPlayer() {

        //Arrange
        //create players
        Player createPlayer = createTestPlayer();
        //2 positions
        int numberOfPositions = 2;


        //Test Player has two positions
        createTestPositions(createPlayer, numberOfPositions);


        //Act
        List<Position> resultPositions = positionService.getPositionsByPlayer(createPlayer, Integer.MAX_VALUE, 0);


        //AssertJu
        assertPositionsByPlayer(numberOfPositions, resultPositions, createPlayer);

    }

    public void assertPositionsByPlayer(int numberOfPositions, List<Position> resultPositions, Player player){
        assert resultPositions.size() == numberOfPositions;
        for (Position position : resultPositions) {
            List<Player> playerList = playerService.getPlayerByPosition(position, Integer.MAX_VALUE, 0);

            boolean containsPlayer = false;
            for (Player play : playerList ) {
                if (play.getId().equals(player.getId())) containsPlayer = true;
            }

            assert containsPlayer == true;
        }
    }

    private Player createTestPlayer() {
        Player player = new Player();
        player.setFirstName("pat");
        player.setLastName("toner");
        return playerService.create(player);
    }

    //passing a player with two positions
    private void createTestPositions(Player createPlayer, int numberOfPositions) {
        for (int i=0; i < numberOfPositions; i++) {
            //create position
            Position position = createTestPosition(i);

            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(createPlayer);
            playerPosition.setPosition(position);
            playerPositionService.create(playerPosition);
        }
    }

    private Position createTestPosition(int positionIndex) {
        Position position = new Position();
        position.setName("P" + positionIndex);
        return positionService.create(position);
    }

}