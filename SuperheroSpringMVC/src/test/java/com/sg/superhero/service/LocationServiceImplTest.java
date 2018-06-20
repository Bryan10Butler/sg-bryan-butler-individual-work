package com.sg.superhero.service;

import com.sg.dto.Location;
import com.sg.service.LocationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class LocationServiceImplTest {

    @Inject
    LocationService locationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addLocation() {

        //Arrange and Act
        Location createdLocation = createLocation();

        //Assert
        assertNotNull(createdLocation.getLocationId());
        assertEquals("Heroes", createdLocation.getName());
        assertEquals("Tall Building", createdLocation.getDescription());
        assertEquals("Boston", createdLocation.getCity());
        assertEquals("MA", createdLocation.getState());
        assertEquals("100 Hero Ave", createdLocation.getStreet());
        assertEquals("01234", createdLocation.getZip());
        assertEquals("23.10", createdLocation.getLatitude());
        assertEquals("45.50", createdLocation.getLongitude());
    }

    @Test
    public void removeLocation() {

        //Arrange
        Location createdLocation = createLocation();

        //Act
        locationService.removeLocation(createdLocation);

        //Assert
        Location readLocation = locationService.retrieveLocation(createdLocation.getLocationId());
        assert readLocation == null;
    }

    @Test
    public void updateLocation() {

        //Arrange
        Location createdLocation = createLocation();

        Location readLocation = locationService.retrieveLocation(createdLocation.getLocationId());

        //Update Fields
        readLocation.setName("School");
        readLocation.setDescription("Short Building");
        readLocation.setCity("Portsmouth");
        readLocation.setState("NH");
        readLocation.setStreet("100 Deer Street");
        readLocation.setZip("67891");
        readLocation.setLatitude("10.20");
        readLocation.setLongitude("15.40");

        //Act
        locationService.updateLocation(readLocation);

        //Assert
        Location updatedLocation = locationService.retrieveLocation(readLocation.getLocationId());

        assertEquals("School", updatedLocation.getName());
        assertEquals("Short Building", updatedLocation.getDescription());
        assertEquals("Portsmouth", updatedLocation.getCity());
        assertEquals("NH", updatedLocation.getState());
        assertEquals("100 Deer Street", updatedLocation.getStreet());
        assertEquals("67891", updatedLocation.getZip());
        assertEquals("10.20", updatedLocation.getLatitude());
        assertEquals("15.40", updatedLocation.getLongitude());
        assertEquals(createdLocation.getLocationId(), updatedLocation.getLocationId());
    }

    @Test
    public void retrieveAllLocations() {

        //Arrange
        Location createdLocation = createLocation();

        //Second Location
        Location createdLocationTwo = createLocation();

        Location readLocation = locationService.retrieveLocation(createdLocationTwo.getLocationId());

        //Update Fields
        readLocation.setName("School");
        readLocation.setDescription("Short Building");
        readLocation.setCity("Portsmouth");
        readLocation.setState("NH");
        readLocation.setStreet("100 Deer Street");
        readLocation.setZip("67891");
        readLocation.setLatitude("10.20");
        readLocation.setLongitude("15.40");

        locationService.updateLocation(readLocation);

        //Act and Assert
        assertNotEquals(createdLocation.getLocationId(), readLocation.getLocationId());
        assertEquals(2, locationService.retrieveAllLocations(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveLocation() {

        //Arrange
        Location createdLocation = createLocation();

        //Assert
        Location readLocation = locationService.retrieveLocation(createdLocation.getLocationId());

        //Act
        assertEquals(createdLocation.getLocationId(), readLocation.getLocationId());
    }

    //Helper Methods
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
        return locationService.addLocation(location);
    }
}