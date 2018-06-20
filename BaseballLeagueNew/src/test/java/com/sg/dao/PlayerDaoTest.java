package com.sg.dao;

import com.sg.dto.Player;
import com.sg.dto.PlayerPosition;
import com.sg.dto.Position;
import com.sg.dto.Team;
import com.sg.service.PlayerPositionService;
import com.sg.service.PositionService;
import com.sg.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PlayerDaoTest {

    @Inject
    PlayerDao playerDao;

    @Inject
    TeamService teamService;

    @Inject
    PositionService positionService;

    @Inject
    PlayerPositionService playerPositionService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Team createdTeam = createTestTeam();
        Player player = createTestPlayer(0, createdTeam);


        //Act
        Player createdPlayer = playerDao.create(player);


        //Assert
        assert createdPlayer.getId() != null;
        assert "Pat0".equals(createdPlayer.getFirstName());
        assert "Toner0".equals(createdPlayer.getLastName());
        assert "Australia0".equals(createdPlayer.getHomeTown());
        assert createdPlayer.getTeam().getId() == createdTeam.getId();

    }

    @Test
    public void read() {

        //Arrange
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        //Act
        Player readPlayer = playerDao.read(createdPlayer.getId());


        //Assert
        assert "Pat0".equals(readPlayer.getFirstName());
        assert "Toner0".equals(readPlayer.getLastName());
        assert "Australia0".equals(readPlayer.getHomeTown());
        assert createdPlayer.getTeam().getId() == readPlayer.getTeam().getId();


    }




    @Test
    public void update() {

        //Arrange
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        Player readPlayer = playerDao.read(createdPlayer.getId());

        //Update fields
        readPlayer.setFirstName("Tom");
        readPlayer.setLastName("Smith");
        readPlayer.setHomeTown("Denver");

        //Move to new team
        Team newTeam = new Team();
        newTeam.setCity("Jacksonville");
        newTeam.setNickname("Falcons");

        Team createdNewTeam = teamService.create(newTeam);

        readPlayer.setTeam(createdNewTeam);

        //Act
        playerDao.update(readPlayer);


        //Assert
        Player updatedPlayer = playerDao.read(readPlayer.getId());

        assert "Tom".equals(updatedPlayer.getFirstName());
        assert "Smith".equals(updatedPlayer.getLastName());
        assert "Denver".equals(updatedPlayer.getHomeTown());
        assert updatedPlayer.getTeam().getId() == createdNewTeam.getId();


    }

    @Test
    public void delete() {

        //Arrange
        Team createdTeam = createTestTeam();
        Player createdPlayer = createTestPlayer(0, createdTeam);

        //Act
        playerDao.delete(createdPlayer);


        //Assert
        Player readPlayer = playerDao.read(createdPlayer.getId());

        assert readPlayer == null;

    }

    @Test
    public void getPlayersByTeam() {

        //Arrange
        Team createTeam = createTestTeam();
        int numberOfPlayers = 15;


        createTestPlayers(createTeam, numberOfPlayers);


        //Act
        List<Player> resultPlayers = playerDao.getPlayersByTeam(createTeam, Integer.MAX_VALUE, 0);


        //Assert
        assertPlayersOnTeam(numberOfPlayers, resultPlayers, createTeam);

    }

    @Test
    public void getPlayersByTeamPage() {

        //Arrange
        Team createTeam = createTestTeam();
        int numberOfPlayers = 15;


        createTestPlayers(createTeam, numberOfPlayers);


        //Act
        List<Player> resultPlayers = playerDao.getPlayersByTeam(createTeam, 5, 0);


        //Assert
        assertPlayersOnTeam(5, resultPlayers, createTeam);

    }



    @Test
    public void getPlayersByPosition() {

        //Arrange
        Position createPosition = createTestPosition();
        int numberOfPlayers = 15;


        createTestPlayers(createPosition, numberOfPlayers);


        //Act
        List<Player> resultPlayers = playerDao.getPlayerByPosition(createPosition, Integer.MAX_VALUE, 0);


        //Assert
        assertPlayersByPosition(numberOfPlayers, resultPlayers, createPosition);

    }

    //HELP METHODS-------------------------------------
    private Position createTestPosition() {
        Position position = new Position();
        position.setName("P");
        return positionService.create(position);
    }

    private void assertPlayersByPosition(int numberOfPlayers, List<Player> resultPlayers, Position position) {
        assert resultPlayers.size() == numberOfPlayers;
        for (Player player : resultPlayers) {
            List<Position> playersPositions = positionService.getPositionsByPlayer(player, Integer.MAX_VALUE, 0);

            //Loop through player's positions and verify it contains the one we added.
            boolean containsPosition = false;
            for (Position pos : playersPositions) {
                if (pos.getId().equals(position.getId())) containsPosition = true;
            }

            assert containsPosition == true;
        }
    }

    private void assertPlayersOnTeam(int numberOfPlayers, List<Player> resultPlayers, Team createTeam) {
        assert resultPlayers.size() == numberOfPlayers;
        for (Player player : resultPlayers) {
            assert player.getTeam().getId() == createTeam.getId();
        }
    }

    private void createTestPlayers(Team createTeam, int numberOfPlayers) {
        for (int i=0; i < numberOfPlayers; i++) {
            createTestPlayer(i, createTeam);
        }
    }

    private void createTestPlayers(Position createPosition, int numberOfPlayers) {
        for (int i=0; i < numberOfPlayers; i++) {
            Player player = createTestPlayer(i, null);

            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(player);
            playerPosition.setPosition(createPosition);
            playerPositionService.create(playerPosition);

        }
    }

    private Player createTestPlayer(int playerIndex, Team team) {

        Player player = new Player();
        player.setFirstName("Pat" + playerIndex);
        player.setLastName("Toner" + playerIndex);
        player.setHomeTown("Australia" + playerIndex);
        player.setTeam(team);

        return playerDao.create(player);
    }

    private Team createTestTeam() {

        Team team = new Team();
        team.setCity("Pittsburgh");
        team.setNickname("Pirates");

        return teamService.create(team);
    }


}