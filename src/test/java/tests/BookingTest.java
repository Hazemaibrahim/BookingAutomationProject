package tests;

import Utilities.DateUtilities;
import Utilities.ExcelDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.HotelDetailsPage;
import pages.ReservationPage;

public class BookingTest extends BaseTest {

    @Test(dataProvider = "bookingData", dataProviderClass = ExcelDataProvider.class)
    public void verifyCheckInAndCheckOutDates(String location, String checkIn, String checkOut) {
        HomePage homePage = new HomePage(driver);
        homePage.enterLocation(location);


        String checkInDay = DateUtilities.extractDay(checkIn);
        String checkOutDay = DateUtilities.extractDay(checkOut);

        homePage.selectDates(checkInDay, checkOutDay);

        SearchResultsPage searchResultsPage = homePage.clickOnSearchButton();
        HotelDetailsPage hotelDetailsPage = searchResultsPage.clickSeeAvailabilityButton();

        String displayedCheckIn = hotelDetailsPage.getCheckInDate().getText().trim();
        String displayedCheckOut = hotelDetailsPage.getCheckOutDate().getText().trim();

        Assert.assertEquals(displayedCheckIn, checkIn, "Check-in date is incorrect!");
        Assert.assertEquals(displayedCheckOut, checkOut, "Check-out date is incorrect!");
    }

    @Test(dataProvider = "bookingData", dataProviderClass = ExcelDataProvider.class)
    public void verifyHotelNameInReservationPage(String location, String checkIn, String checkOut) {
        HomePage homePage = new HomePage(driver);
        homePage.enterLocation(location);

        String checkInDay = DateUtilities.extractDay(checkIn);
        String checkOutDay = DateUtilities.extractDay(checkOut);

        homePage.selectDates(checkInDay, checkOutDay);

        SearchResultsPage searchResultsPage = homePage.clickOnSearchButton();
        HotelDetailsPage hotelDetailsPage = searchResultsPage.clickSeeAvailabilityButton();

        hotelDetailsPage.selectBed();
        hotelDetailsPage.selectAmount();

        ReservationPage reservationPage = hotelDetailsPage.clickOnReserveButton();

        String expectedHotelName = reservationPage.getHotelName().getText().trim();
        String actualHotelName = reservationPage.getHotelName().getText().trim();

        Assert.assertTrue(actualHotelName.contains(expectedHotelName),
                "Hotel name is not displayed correctly in reservation box!");
    }

}