package com.sg.superhero.dao;

import com.sg.dao.PowerDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroPower;
import com.sg.dto.Organization;
import com.sg.dto.Power;
import com.sg.service.HeroPowerService;
import com.sg.service.HeroService;
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
public class PowerDaoImplTest {

    @Inject
    PowerDao powerDao;

    @Inject
    HeroService heroService;

    @Inject
    HeroPowerService heroPowerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPower() {

        //Arrange and Act
        Power power = createPower();

        //Assert
        assertNotNull(power.getPowerId());
        assertEquals("Fly", power.getName());
        assertEquals("The hero is able to fly", power.getDescription());
    }

    @Test
    public void removePower() {

        //Arrange
        Power createdPower = createPower();

        //Act
        powerDao.removePower(createdPower);
        Power readPower = powerDao.retrievePower(createdPower.getPowerId());

        //Assert
        assertNull(readPower);
    }

    @Test
    public void updatePower() {

        //Arrange
        Power createdPower = createPower();

        //Update Fields
        createdPower.setName("Laser Eyes");
        createdPower.setDescription("Shoot laser beams out of eyes");

        //Act
        powerDao.updatePower(createdPower);

        //Assert
        Power updatedPower = powerDao.retrievePower(createdPower.getPowerId());

        assertEquals(createdPower.getPowerId(), updatedPower.getPowerId());
        assertEquals("Laser Eyes", updatedPower.getName());
        assertEquals("Shoot laser beams out of eyes", updatedPower.getDescription());
    }

    @Test
    public void retrieveAllPowers() {

        //Arrange
        Power createdPower = createPower();

        Power createdPowerTwo = createPower();

        //Update Fields
        createdPowerTwo.setName("Laser Eyes");
        createdPowerTwo.setDescription("Shoot laser beams out of eyes");

        powerDao.updatePower(createdPowerTwo);

        //Act and Assert
        assertNotEquals(createdPower.getPowerId(), createdPowerTwo.getPowerId());
        assertEquals(2, powerDao.retrieveAllPowers(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrievePower() {

        //Arrange
        Power createdPower = createPower();

        //Act
        Power readPower = powerDao.retrievePower(createdPower.getPowerId());

        //Assert
        assertEquals(createdPower.getPowerId(), readPower.getPowerId());
    }

    @Test
    public void retrievePowerByHero() {

        //Retrieve all powers by the same hero

        //Arrange
        //number of heroes
        int numberOfPowers = 2;

        //create hero
        Hero createdHero = createHero();

        //create two powers
        Power createdPower = createPower();
        Power createdPowerTwo = createPower();

        //create hero power
        createHeroPower(createdHero, createdPower);
        createHeroPower(createdHero, createdPowerTwo);

        //Act
        List<Power> powerList = powerDao.retrievePowerByHero(createdHero, Integer.MAX_VALUE, 0);

        //Assert
        assertPowersByHero(numberOfPowers, powerList, createdHero);
    }

    @Test
    public void retrievePowerByHeroPage() {

        //Retrieve all powers by the same hero

        //Arrange
        //number of heroes
        int numberOfPowers = 1;

        //create hero
        Hero createdHero = createHero();

        //create two powers
        Power createdPower = createPower();
        Power createdPowerTwo = createPower();

        //create hero power
        createHeroPower(createdHero, createdPower);
        createHeroPower(createdHero, createdPowerTwo);

        //Act
        List<Power> powerList = powerDao.retrievePowerByHero(createdHero, 1, 0);

        //Assert
        assertPowersByHero(numberOfPowers, powerList, createdHero);
    }

    private Power createPower() {

        //Arrange
        Power power = new Power();
        power.setName("Fly");
        power.setDescription("The hero is able to fly");

        //Act
        powerDao.addPower(power);
        return power;
    }

    private Hero createHero() {

        //Arrange and Act
        Hero hero = new Hero();
        hero.setName("Wolverine");
        hero.setDescription("A beast with extremely sharp claws");

        //Act
        return heroService.addHero(hero);
    }

    private HeroPower createHeroPower(Hero createdHero, Power createdPower) {
        HeroPower heroPower = new HeroPower();

        heroPower.setHero(createdHero);
        heroPower.setPower(createdPower);

        heroPowerService.addHeroPower(heroPower);

        return heroPower;
    }

    private void assertPowersByHero(int numberOfPowers, List<Power> powerList, Hero hero) {

        assert powerList.size() == numberOfPowers;
        //for each power in powerList
        for (Power power : powerList) {
            //retrieve heroes
            List<Hero> heroList = heroService.retrieveHeroByPower(power, Integer.MAX_VALUE, 0);

            boolean containsHero = false;
            //for each hero in heroOrganization
            for (Hero h : heroList) {
                //make sure the organization we added is contained in the list
                if (h.getHeroId().equals(hero.getHeroId())) containsHero = true;
            }
            assert containsHero == true;
        }
    }
}