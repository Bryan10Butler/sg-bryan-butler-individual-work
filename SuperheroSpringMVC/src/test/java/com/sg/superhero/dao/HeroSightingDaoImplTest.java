package com.sg.superhero.dao;

import com.sg.dao.HeroSightingDao;
import com.sg.dao.SightingDao;
import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.service.HeroService;
import com.sg.service.LocationService;
import com.sg.service.SightingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class HeroSightingDaoImplTest {

    @Inject
    HeroSightingDao heroSightingDao;

    @Inject
    HeroService heroService;

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHeroSighting() {

        //Arrange and Act
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Sighting createdSighting = createSighting(createdLocation);

        HeroSighting heroSighting = createHeroSighting(createdHero, createdSighting);

        //Assert
        assertNotNull(heroSighting.getHeroSightingId());
        assertEquals(createdHero.getHeroId(), heroSighting.getHero().getHeroId());
        assertEquals(createdSighting.getSightingId(), heroSighting.getSighting().getSightingId());
    }

    @Test
    public void removeHeroSighting() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Sighting createdSighting = createSighting(createdLocation);

        HeroSighting heroSighting = createHeroSighting(createdHero, createdSighting);

        //Act
        heroSightingDao.removeHeroSighting(heroSighting);
        HeroSighting readHeroSighting = heroSightingDao.retrieveHeroSightingById(heroSighting.getHeroSightingId());

        //Assert
        assertNull(readHeroSighting);
    }

    @Test
    public void updateHeroSighting() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Sighting createdSighting = createSighting(createdLocation);

        HeroSighting heroSighting = createHeroSighting(createdHero, createdSighting);

        //Update Fields
        Hero createdHeroTwo = createHero();
        Location createdLocationTwo = createLocation();
        Sighting createdSightingTwo = createSighting(createdLocationTwo);

        heroService.updateHero(createdHeroTwo);
        sightingService.updateSighting(createdSightingTwo);

        heroSighting.setHero(createdHeroTwo);
        heroSighting.setSighting(createdSightingTwo);

        //Act
        heroSightingDao.updateHeroSighting(heroSighting);

        //Assert
        HeroSighting readHeroSighting = heroSightingDao.retrieveHeroSightingById(heroSighting.getHeroSightingId());

        assertEquals(createdHeroTwo.getHeroId(), readHeroSighting.getHero().getHeroId());
        assertEquals(createdSightingTwo.getSightingId(), readHeroSighting.getSighting().getSightingId());
        assertEquals(heroSighting.getHeroSightingId(), readHeroSighting.getHeroSightingId());
    }

    @Test
    public void retrieveHeroSightingById() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Sighting createdSighting = createSighting(createdLocation);

        HeroSighting heroSighting = createHeroSighting(createdHero, createdSighting);

        //Act
        HeroSighting readHeroSighting = heroSightingDao.retrieveHeroSightingById(heroSighting.getHeroSightingId());

        //Assert
        assertEquals(heroSighting.getHeroSightingId(), readHeroSighting.getHeroSightingId());
    }

    @Test
    public void retrieveHeroSightingBySighting() {
        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Sighting createdSighting = createSighting(createdLocation);

        HeroSighting heroSighting = createHeroSighting(createdHero, createdSighting);

        //Act
        List<HeroSighting> readHeroSighting = heroSightingDao.retrieveHeroSightingBySighting(createdSighting.getSightingId());

        //Assert
        assertEquals(createdHero.getHeroId(), readHeroSighting.get(0).getHero().getHeroId());
        assertEquals(createdSighting.getSightingId(), readHeroSighting.get(0).getSighting().getSightingId());
        assertEquals(1, readHeroSighting.size());
    }

    @Test
    public void retrieveHeroSightingByHero() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Sighting createdSighting = createSighting(createdLocation);

        HeroSighting heroSighting = createHeroSighting(createdHero, createdSighting);

        //Act
        List<HeroSighting> readHeroSighting = heroSightingDao.retrieveHeroSightingByHero(createdHero.getHeroId());

        //Assert
        assertEquals(createdHero.getHeroId(), readHeroSighting.get(0).getHero().getHeroId());
        assertEquals(createdSighting.getSightingId(), readHeroSighting.get(0).getSighting().getSightingId());
        assertEquals(1, readHeroSighting.size());
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

    private Sighting createSighting(Location location) {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sighting.setDescription("It's a bird, it's a plane, it's just Bryan");
        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        return sighting;
    }

    private Location createLocation() {
        Location location = new Location();
        location.setName("Heroes");
        location.setDescription("Tall Building");
        location.setCity("Boston");
        location.setState("MA");
        location.setStreet("100 Hero Ave");
        location.setZip("01234");
        location.setLatitude("23.10");
        location.setLongitude("45.50");
        locationService.addLocation(location);
        return location;
    }

    private HeroSighting createHeroSighting(Hero createdHero, Sighting createdSighting) {
        HeroSighting heroSighting = new HeroSighting();

        heroSighting.setHero(createdHero);
        heroSighting.setSighting(createdSighting);

        heroSightingDao.addHeroSighting(heroSighting);
        return heroSighting;
    }

}