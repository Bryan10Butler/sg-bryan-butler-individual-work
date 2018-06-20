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
public class OrganizationServiceImplTest {

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    @Inject
    HeroService heroService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addOrganization() {

        //Arrange and Act
        Location location = createLocation();
        Organization organization = createOrganization(location);

        //Assert
        assertNotNull(organization.getOrganizationId());
        assertEquals("Avengers", organization.getName());
        assertEquals("League of extraordinary people", organization.getDescription());
        assertEquals("Tall Building", organization.getLocation().getDescription());
        assertEquals("Boston", organization.getLocation().getCity());
        assertEquals("MA", organization.getLocation().getState());
        assertEquals("100 Hero Ave", organization.getLocation().getStreet());
        assertEquals("01234", organization.getLocation().getZip());
        assertEquals("23.10", organization.getLocation().getLatitude());
        assertEquals("45.50", organization.getLocation().getLongitude());
    }

    @Test
    public void removeOrganization() {

        //Arrange
        Location location = createLocation();
        Organization organization = createOrganization(location);

        //Act
        organizationService.removeOrganization(organization);
        Organization readOrganization = organizationService.retrieveOrganization(organization.getOrganizationId());

        //Assert
        assertNull(readOrganization);
    }

    @Test
    public void updateOrganization() {

        //Arrange
        Location location = createLocation();
        Organization organization = createOrganization(location);

        //Update Information
        organization.setName("Suicide Squad");
        organization.setDescription("Crazy People");

        //Act
        organizationService.updateOrganization(organization);

        //Assert
        Organization updatedOrganization = organizationService.retrieveOrganization(organization.getOrganizationId());
        assertEquals("Suicide Squad", updatedOrganization.getName());
        assertEquals("Crazy People", updatedOrganization.getDescription());
        assertEquals(organization.getOrganizationId(), updatedOrganization.getOrganizationId());
    }

    @Test
    public void retrieveAllOrganizations() {

        //Arrange
        Location location = createLocation();
        Organization organization = createOrganization(location);

        Location locationTwo = createLocation();
        Organization organizationTwo = createOrganization(locationTwo);

        Organization readOrganization = organizationService.retrieveOrganization(organizationTwo.getOrganizationId());

        //Update Fields
        readOrganization.setName("Suicide Squad");
        readOrganization.setDescription("Crazy People");

        organizationService.updateOrganization(readOrganization);

        //Act and Assert
        assertNotEquals(organization.getOrganizationId(), organizationTwo.getOrganizationId());
        assertEquals(2, organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveOrganization() {

        //Arrange
        Location location = createLocation();
        Organization organization = createOrganization(location);

        //Act
        Organization readOrganization = organizationService.retrieveOrganization(organization.getOrganizationId());

        //Assert
        assertEquals(organization.getOrganizationId(),readOrganization.getOrganizationId());
    }

    @Test
    public void retrieveOrganizationByHero() {

        //Retrieve all organizations by hero

        //Arrange
        //number of organizations
        int numberOfOrganizations = 2;

        Hero createdHero = createHero();

        //create two organizations
        Location location = createLocation();
        Organization organization = createOrganization(location);

        //Arrange and Act
        Location locationTwo = createLocation();
        Organization organizationTwo = createOrganization(locationTwo);

        //create two organizations by hero
        createdHeroOrganization(createdHero, organization);
        createdHeroOrganization(createdHero, organizationTwo);

        //Act
        List<Organization> organizationList = organizationService.retrieveOrganizationByHero(createdHero, Integer.MAX_VALUE, 0);

        //Assert
        assertOrganizationByHero(numberOfOrganizations, organizationList, createdHero);
    }

    @Test
    public void retrieveOrganizationByHeroPage() {

        //Retrieve all organizations by hero

        //Arrange
        //number of organizations
        int numberOfOrganizations = 1;

        Hero createdHero = createHero();

        //create two organizations
        Location location = createLocation();
        Organization organization = createOrganization(location);

        //Arrange and Act
        Location locationTwo = createLocation();
        Organization organizationTwo = createOrganization(locationTwo);

        //create two organizations by hero
        createdHeroOrganization(createdHero, organization);
        createdHeroOrganization(createdHero, organizationTwo);

        //Act
        List<Organization> organizationList = organizationService.retrieveOrganizationByHero(createdHero, 1, 0);

        //Assert
        assertOrganizationByHero(numberOfOrganizations, organizationList, createdHero);
    }

    //Helper Methods
    private Organization createOrganization(Location location) {

        //Arrange
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("League of extraordinary people");
        //Pass in Location
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

    private Hero createHero() {

        //Arrange and Act
        Hero hero = new Hero();
        hero.setName("Wolverine");
        hero.setDescription("A beast with extremely sharp claws");

        //Act
        return heroService.addHero(hero);
    }

    private HeroOrganization createdHeroOrganization(Hero createdHero, Organization createdOrganization) {
        HeroOrganization heroOrganization = new HeroOrganization();
        heroOrganization.setHero(createdHero);
        heroOrganization.setOrganization(createdOrganization);

        heroOrganizationService.addHeroOrganization(heroOrganization);
        return heroOrganization;
    }

    private void assertOrganizationByHero(int numberOfOrganizations, List<Organization> organizationList, Hero hero) {

        assert organizationList.size() == numberOfOrganizations;
        //for each organization in organizationList
        for (Organization organization : organizationList) {
            //retrieve heroes
            List<Hero> heroList = heroService.retrieveHeroByOrganization(organization, Integer.MAX_VALUE, 0);

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