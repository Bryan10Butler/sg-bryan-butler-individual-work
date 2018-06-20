package com.sg.superhero.webservice;

import com.sg.dto.Location;
import com.sg.model.commandmodel.location.editlocation.LocationEditCommandModel;
import com.sg.model.commandmodel.location.locationpage.LocationPageCreateCommandModel;
import com.sg.model.viewmodel.location.detailslocation.LocationDetailsViewModel;
import com.sg.model.viewmodel.location.editlocation.LocationEditViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationPageViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationViewModel;
import com.sg.superhero.webservice.util.TestDataHelperWebService;
import com.sg.webservice.LocationWebService;
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
public class LocationWebServiceImplTest {

    @Inject
    LocationWebService locationWebService;

    @Inject
    TestDataHelperWebService testData;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveLocationPageViewModel() {
        //Testing we can retrieve the Location Page View Model

        //Arrange
        //Create a list of locations
        List<Location> locationList = testData.createTestLocations(12);

        //Act
        LocationPageViewModel locationPageViewModel = locationWebService.retrieveLocationPageViewModel(Integer.MAX_VALUE, 0, 1);

        //Assert
        assertEquals(locationList.size(), locationPageViewModel.getLocations().size());
        //Object is there, but fields for command model are blank
        assertNotNull(locationPageViewModel.getLocationPageCreateCommandModel());

        //for each lvm in locationPageViewModel assert ID and Name not null
        for(LocationViewModel lvm : locationPageViewModel.getLocations()) {
            assertNotNull(lvm.getId());
            assertNotNull(lvm.getName());
        }
    }

    @Test
    public void retrieveLocationPageViewModelPaging() {
        //Testing we can retrieve the Location Page View Model

        //number of locations is 3, but we are creating 12. Test we get only 3 with paging
        int numberOfLocations = 3;
        //Arrange
        //Create a list of locations
        List<Location> locationList = testData.createTestLocations(12);

        //Act
        LocationPageViewModel locationPageViewModel = locationWebService.retrieveLocationPageViewModel(numberOfLocations, 0, 1);

        //Assert
        assertEquals(numberOfLocations, locationPageViewModel.getLocations().size());
        //Object is there, but fields for command model are blank
        assertNotNull(locationPageViewModel.getLocationPageCreateCommandModel());

        //for each lvm in locationPageViewModel assert ID and Name not null
        for(LocationViewModel lvm : locationPageViewModel.getLocations()) {
            assertNotNull(lvm.getId());
            assertNotNull(lvm.getName());
        }
    }

    @Test
    public void saveLocationPageCreateCommandModel() {
        //Testing we can save the Location Page Create Command Model (User clicks save)

        //Arrange
        //Instantiate command model
        LocationPageCreateCommandModel commandModel = new LocationPageCreateCommandModel();
        commandModel.setName("Heroes Inc");
        commandModel.setDescription("Tall Building");
        commandModel.setCity("Boston");
        commandModel.setState("MA");
        commandModel.setStreet("100 Hero Ave");
        commandModel.setZip("01234");
        commandModel.setLatitude("23.10");
        commandModel.setLongitude("45.50");

        //Act
        Location createdLocation = locationWebService.saveLocationPageCreateCommandModel(commandModel);

        //Assert
        assertNotNull(createdLocation.getLocationId());
        assertEquals("Heroes Inc", createdLocation.getName());
        assertEquals("Tall Building", createdLocation.getDescription());
        assertEquals("Boston", createdLocation.getCity());
        assertEquals("MA", createdLocation.getState());
        assertEquals("100 Hero Ave", createdLocation.getStreet());
        assertEquals("01234", createdLocation.getZip());
        assertEquals("23.10", createdLocation.getLatitude());
        assertEquals("45.50", createdLocation.getLongitude());
    }

    @Test
    public void retrieveLocationEditViewModel() {

        //Arrange
        Location location = testData.createTestLocation();

        //Act
        LocationEditViewModel locationEditViewModel = locationWebService.retrieveLocationEditViewModel(location.getLocationId());

        //Assert
        assertNotNull(locationEditViewModel.getLocationEditCommandModel().getLocationId());
        assertEquals("Heroes Inc", locationEditViewModel.getLocationEditCommandModel().getName());
        assertEquals("Tall Building", locationEditViewModel.getLocationEditCommandModel().getDescription());
        assertEquals("Boston", locationEditViewModel.getLocationEditCommandModel().getCity());
        assertEquals("MA", locationEditViewModel.getLocationEditCommandModel().getState());
        assertEquals("100 Hero Ave", locationEditViewModel.getLocationEditCommandModel().getStreet());
        assertEquals("01234", locationEditViewModel.getLocationEditCommandModel().getZip());
        assertEquals("23.10", locationEditViewModel.getLocationEditCommandModel().getLatitude());
        assertEquals("45.50", locationEditViewModel.getLocationEditCommandModel().getLongitude());
    }

    @Test
    public void saveLocationEditCommandModel() {

        //Arrange
        Location location = testData.createTestLocation();

        LocationEditCommandModel locationEditCommandModel = new LocationEditCommandModel();
        locationEditCommandModel.setLocationId(location.getLocationId());
        locationEditCommandModel.setName("Avengers Inc");
        locationEditCommandModel.setDescription("Where the Avengers live");
        locationEditCommandModel.setCity("Portsmouth");
        locationEditCommandModel.setState("NH");
        locationEditCommandModel.setStreet("100 Hero Ave.");
        locationEditCommandModel.setZip("02127");
        locationEditCommandModel.setLatitude("20.30");
        locationEditCommandModel.setLongitude("45.87");

        //Act
        Location updatedLocation = locationWebService.saveLocationEditCommandModel(locationEditCommandModel);

        //Assert
        assertEquals(location.getLocationId(), updatedLocation.getLocationId());
        assertEquals("Avengers Inc", updatedLocation.getName());
        assertEquals("Where the Avengers live", updatedLocation.getDescription());
        assertEquals("Portsmouth", updatedLocation.getCity());
        assertEquals("NH", updatedLocation.getState());
        assertEquals("100 Hero Ave.", updatedLocation.getStreet());
        assertEquals("02127", updatedLocation.getZip());
        assertEquals("20.30", updatedLocation.getLatitude());
        assertEquals("45.87", updatedLocation.getLongitude());
    }

    @Test
    public void retrieveLocationDetailsViewModel() {

        //Arrange
        Location location = testData.createTestLocation();

        //Act
        LocationDetailsViewModel locationDetailsViewModel = locationWebService.retrieveLocationDetailsViewModel(location.getLocationId());

        //Assert
        assertNotNull(locationDetailsViewModel.getId());
        assertEquals("Heroes Inc", locationDetailsViewModel.getName());
        assertEquals("Tall Building", locationDetailsViewModel.getDescription());
        assertEquals("Boston", locationDetailsViewModel.getCity());
        assertEquals("MA", locationDetailsViewModel.getState());
        assertEquals("100 Hero Ave", locationDetailsViewModel.getStreet());
        assertEquals("01234", locationDetailsViewModel.getZip());
        assertEquals("23.10", locationDetailsViewModel.getLatitude());
        assertEquals("45.50", locationDetailsViewModel.getLongitude());
    }
}