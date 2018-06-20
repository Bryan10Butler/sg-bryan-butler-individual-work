package com.sg.superhero.service;

import com.sg.dto.Hero;
import com.sg.dto.HeroOrganization;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.service.HeroOrganizationService;
import com.sg.service.HeroService;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
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
public class HeroOrganizationServiceImplTest {

    @Inject
    HeroService heroService;

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHeroOrganization() {

        //Arrange and Act
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        HeroOrganization heroOrganization = createdHeroOrganization(createdHero, createdOrganization);

        //Assert
        assertNotNull(heroOrganization.getHeroOrganizationId());
        assertEquals(createdHero.getHeroId(), heroOrganization.getHero().getHeroId());
        assertEquals(createdOrganization.getOrganizationId(), heroOrganization.getOrganization().getOrganizationId());
    }

    @Test
    public void removeHeroOrganization() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        HeroOrganization heroOrganization = createdHeroOrganization(createdHero, createdOrganization);

        //Act
        heroOrganizationService.removeHeroOrganization(heroOrganization);
        HeroOrganization readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationById(heroOrganization.getHeroOrganizationId());

        //Assert
        assertNull(readHeroOrganization);
    }

    @Test
    public void updateHeroOrganization() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        HeroOrganization heroOrganization = createdHeroOrganization(createdHero, createdOrganization);

        //Update Fields
        Hero createdHeroTwo = createHero();
        Location createdLocationTwo = createLocation();
        Organization createdOrganizationTwo = createOrganization(createdLocationTwo);

        heroService.updateHero(createdHeroTwo);
        organizationService.updateOrganization(createdOrganizationTwo);

        heroOrganization.setHero(createdHeroTwo);
        heroOrganization.setOrganization(createdOrganizationTwo);

        //Act
        heroOrganizationService.updateHeroOrganization(heroOrganization);

        //Assert
        HeroOrganization readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationById(heroOrganization.getHeroOrganizationId());

        assertEquals(createdHeroTwo.getHeroId(), readHeroOrganization.getHero().getHeroId());
        assertEquals(createdOrganizationTwo.getOrganizationId(), readHeroOrganization.getOrganization().getOrganizationId());
        assertEquals(heroOrganization.getHeroOrganizationId(), readHeroOrganization.getHeroOrganizationId());
    }

    @Test
    public void retrieveHeroOrganizationById() {

        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        HeroOrganization heroOrganization = createdHeroOrganization(createdHero, createdOrganization);

        //Act
        HeroOrganization readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationById(heroOrganization.getHeroOrganizationId());

        //Assert
        assertEquals(heroOrganization.getHeroOrganizationId(), readHeroOrganization.getHeroOrganizationId());
    }

    @Test
    public void retrieveHeroOrganizationByOrganization() {
        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        HeroOrganization heroOrganization = createdHeroOrganization(createdHero, createdOrganization);

        //Act
        List<HeroOrganization> readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationByOrganization(createdOrganization.getOrganizationId());

        //Assert
        assertEquals(createdHero.getHeroId(), readHeroOrganization.get(0).getHero().getHeroId());
        assertEquals(1, readHeroOrganization.size());
        assertEquals(createdOrganization.getOrganizationId(),readHeroOrganization.get(0).getOrganization().getOrganizationId());
    }

    @Test
    public void retrieveHeroOrganizationByHero() {
        //Arrange
        Hero createdHero = createHero();
        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        HeroOrganization heroOrganization = createdHeroOrganization(createdHero, createdOrganization);

        //Act
        List<HeroOrganization> readHeroOrganization = heroOrganizationService.retrieveHeroOrganizationByHero(createdHero.getHeroId());

        //Assert
        assertEquals(createdHero.getHeroId(), readHeroOrganization.get(0).getHero().getHeroId());
        assertEquals(1, readHeroOrganization.size());
        assertEquals(createdOrganization.getOrganizationId(),readHeroOrganization.get(0).getOrganization().getOrganizationId());
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

    private Organization createOrganization(Location location) {

        //Arrange
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("League of extraordinary people");
        organization.setLocation(location);

        //Act
        organizationService.addOrganization(organization);
        return organization;
    }

    private Location createLocation() {

        //Arrange
        Location location = new Location();
        location.setName("Heroes");
        location.setDescription("Tall Building");
        location.setCity("Boston");
        location.setState("MA");
        location.setStreet("100 Hero Ave");
        location.setZip("01234");
        location.setLatitude("23.10");
        location.setLongitude("45.50");

        //Act
        locationService.addLocation(location);
        return location;
    }

    private HeroOrganization createdHeroOrganization(Hero createdHero, Organization createdOrganization) {
        HeroOrganization heroOrganization = new HeroOrganization();
        heroOrganization.setHero(createdHero);
        heroOrganization.setOrganization(createdOrganization);

        heroOrganizationService.addHeroOrganization(heroOrganization);
        return heroOrganization;
    }

}