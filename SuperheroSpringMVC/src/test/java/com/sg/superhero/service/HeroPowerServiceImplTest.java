package com.sg.superhero.service;

import com.sg.dao.HeroPowerDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroPower;
import com.sg.dto.Power;
import com.sg.service.HeroPowerService;
import com.sg.service.HeroService;
import com.sg.service.PowerService;
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

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class HeroPowerServiceImplTest {

    @Inject
    HeroPowerService heroPowerService;

    @Inject
    HeroService heroService;

    @Inject
    PowerService powerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHeroPower() {

        //Arrange and Act
        Hero createdHero = createHero();
        Power createdPower = createPower();

        HeroPower heroPower = createHeroPower(createdHero, createdPower);

        //Assert - Use IDs as the bridge only has access to IDs
        assertNotNull(heroPower.getHeroPowerId());
        assertEquals(createdHero.getHeroId(), heroPower.getHero().getHeroId());
        assertEquals(createdPower.getPowerId(), heroPower.getPower().getPowerId());
    }

    @Test
    public void removeHeroPower() {

        //Arrange
        //Arrange and Act
        Hero createdHero = createHero();
        Power createdPower = createPower();

        HeroPower heroPower = createHeroPower(createdHero, createdPower);

        //Act
        heroPowerService.removeHeroPower(heroPower);
        HeroPower readPower = heroPowerService.retrieveHeroPowerById(heroPower.getHeroPowerId());

        //Assert
        assertNull(readPower);
    }

    @Test
    public void updateHeroPower() {

        //Arrange
        Hero createdHero = createHero();
        Power createdPower = createPower();
        HeroPower heroPower = createHeroPower(createdHero, createdPower);

        //Update Fields
        Hero createdHeroTwo = createHero();
        Power createdPowerTwo = createPower();

        //for fun -- bridge tables only use IDs
//        createdHeroTwo.setName("Batman");
//        createdHeroTwo.setDescription("Batman lives in Gotham City");
//
//        createdPowerTwo.setName("Super Strength");
//        createdPowerTwo.setDescription("Hero is very strong");

        heroService.updateHero(createdHeroTwo);
        powerService.updatePower(createdPowerTwo);

        heroPower.setHero(createdHeroTwo);
        heroPower.setPower(createdPowerTwo);

        //Act
        heroPowerService.updateHeroPower(heroPower);

        //Assert
        HeroPower readHeroPower = heroPowerService.retrieveHeroPowerById(heroPower.getHeroPowerId());

        assertEquals(createdHeroTwo.getHeroId(), readHeroPower.getHero().getHeroId());
        assertEquals(createdPowerTwo.getPowerId(), readHeroPower.getPower().getPowerId());
        assertEquals(heroPower.getHeroPowerId(), readHeroPower.getHeroPowerId());
    }

    @Test
    public void retrieveHeroPowerById() {

        //Arrange
        Hero createdHero = createHero();
        Power createdPower = createPower();

        HeroPower heroPower = createHeroPower(createdHero, createdPower);

        //Act
        HeroPower readHeroPower = heroPowerService.retrieveHeroPowerById(heroPower.getHeroPowerId());

        //Assert
        assertEquals(heroPower.getHeroPowerId(), readHeroPower.getHeroPowerId());
    }

    @Test
    public void retrieveHeroPowerByPower() {

        //Arrange
        Hero createdHero = createHero();
        Power createdPower = createPower();

        HeroPower heroPower = createHeroPower(createdHero, createdPower);

        //Act
        List<HeroPower> readHeroPowers = heroPowerService.retrieveHeroPowerByPower(createdPower.getPowerId());

        //Assert
        assertEquals(createdPower.getPowerId(), readHeroPowers.get(0).getPower().getPowerId());
        assertEquals(createdHero.getHeroId(), readHeroPowers.get(0).getHero().getHeroId());
        assertEquals(1, readHeroPowers.size());
    }

    @Test
    public void retrieveHeroPowerByHero() {

        //Arrange
        Hero createdHero = createHero();
        Power createdPower = createPower();

        HeroPower heroPower = createHeroPower(createdHero, createdPower);

        //Act
        List<HeroPower> readHeroPowers = heroPowerService.retrieveHeroPowerByHero(createdHero.getHeroId());

        //Assert
        assertEquals(createdPower.getPowerId(), readHeroPowers.get(0).getPower().getPowerId());
        assertEquals(createdHero.getHeroId(), readHeroPowers.get(0).getHero().getHeroId());
        assertEquals(1, readHeroPowers.size());

    }

    //Helper Methods
    private Hero createHero() {

        //Arrange and Act
        Hero hero = new Hero();
        hero.setName("Wolverine");
        hero.setDescription("A beast with extremely sharp claws");

        //Act
        heroService.addHero(hero);
        return hero;
    }

    private Power createPower() {

        //Arrange
        Power power = new Power();
        power.setName("Fly");
        power.setDescription("The hero is able to fly");

        //Act
        powerService.addPower(power);
        return power;
    }

    private HeroPower createHeroPower(Hero createdHero, Power createdPower) {
        HeroPower heroPower = new HeroPower();

        heroPower.setHero(createdHero);
        heroPower.setPower(createdPower);

        heroPowerService.addHeroPower(heroPower);

        return heroPower;
    }

}