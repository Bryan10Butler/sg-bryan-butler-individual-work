package com.sg.superhero.webservice;

import com.sg.dto.*;
import com.sg.model.viewmodel.homepage.HomePageHeroViewModel;
import com.sg.model.viewmodel.homepage.HomePageSightingViewModel;
import com.sg.model.viewmodel.homepage.HomePageViewModel;
import com.sg.superhero.webservice.util.TestDataHelperWebService;
import com.sg.webservice.HomePageWebService;
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
public class HomePageWebServiceImplTest {

    @Inject
    TestDataHelperWebService testData;

    @Inject
    HomePageWebService homePageWebService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveHomePageViewModel() {

        //Arrange - main display
        Location location = testData.createTestLocation();
        List<Sighting> sightingList = testData.createTestSightings(3, location);
        List<Hero> heroList = testData.createTestHeroes(3);

        //Act
        HomePageViewModel homePageViewModel = homePageWebService.retrieveHomePageViewModel(10);

        //Assert sightingList Size
        assertEquals(sightingList.size(), homePageViewModel.getSightings().size());

        //Assert heroes ID and Name
        for (HomePageSightingViewModel hpsvm : homePageViewModel.getSightings()) {
            assertNotNull(hpsvm.getDate());
            assertNotNull(hpsvm.getDescription());
            assertNotNull(hpsvm.getHeroes());
            assertNotNull(hpsvm.getId());
            assertNotNull(hpsvm.getLocation());
            for (HomePageHeroViewModel hphv : hpsvm.getHeroes()) {
                assertNotNull(hphv.getId());
                assertNotNull(hphv.getName());
            }
        }
    }

    @Test
    public void retrieveHomePageViewModelPaging() {

        int numberOfHomePageViewModels = 3;

        //Arrange - main display
        Location location = testData.createTestLocation();
        List<Sighting> sightingList = testData.createTestSightings(3, location);
        List<Hero> heroList = testData.createTestHeroes(3);

        //Act
        HomePageViewModel homePageViewModel = homePageWebService.retrieveHomePageViewModel(numberOfHomePageViewModels);

        //Assert sightingList Size
        assertEquals(numberOfHomePageViewModels, homePageViewModel.getSightings().size());

        //Assert heroes ID and Name
        for (HomePageSightingViewModel hpsvm : homePageViewModel.getSightings()) {
            assertNotNull(hpsvm.getDate());
            assertNotNull(hpsvm.getDescription());
            assertNotNull(hpsvm.getHeroes());
            assertNotNull(hpsvm.getId());
            assertNotNull(hpsvm.getLocation());
            for (HomePageHeroViewModel hphv : hpsvm.getHeroes()) {
                assertNotNull(hphv.getId());
                assertNotNull(hphv.getName());
            }
        }
    }

}
