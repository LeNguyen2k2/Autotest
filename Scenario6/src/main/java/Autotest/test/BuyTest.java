package Autotest.test;


import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BuyTest extends BaseTest {
    private static final String CENTER_PAGE = "//div[@class='slider-bannertop owl-carousel owl-theme owl-loaded owl-drag']//div[@class='owl-stage-outer']";
    private static final String BOX_ATC ="//div[@class='bs_content']";

    @Test(description = "NC001: Nagivation to Tivi")
    public void NC001_Nagivation_to_Tivi() {
        objHome.getCategoryPage().mouse_to_Category();
        objHome.getCategoryPage().mouse_to_Electronics();
        objTivi = objHome.getCategoryPage().navigate_to_Tivi();
        String expectedUrl = "https://www.dienmayxanh.com/tivi";
        String actualUrl = webUI.getCurrentUrl();
        assertTrue(actualUrl.contains(expectedUrl), "Failed to navigate to the Tivi page. Current URL: " + actualUrl);
    }

    @Test(description = "NC002: Filter TV 43 to 55")
    public void NC002_Filter_TV_43_to_55() {
        objTivi.clickFilter();
        objTivi.filter_TV_By_Screen_Size(43,50,55);
        objTivi.ClickAndGetTotalResults();
    }
    @Test(description = "NC003: Add to Cart")
    public void NC003_Add_to_Cart() {
        webUI.scrollToElement(CENTER_PAGE);
        objTivi.clickFirstTV();
        objfirstTV.add_to_Cart();
        objfirstTV.checkCartItemCount();
        webUI.goBack();
    }
    @Test(description = "NC004: Filter TV 65 to 85")
    public void NC004_Filter_TV_65_to_85() {
        objTivi.clearResults();
        webUI.scrollToElement(CENTER_PAGE);
        objTivi.clickFilter();
        objTivi.filter_TV_By_Screen_Size(65,70);
        objTivi.ClickAndGetTotalResults();
    }
}
