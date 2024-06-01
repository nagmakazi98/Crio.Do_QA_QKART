package QKART_SANITY_LOGIN.Module1;

import java.sql.Driver;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
       // WebElement title = driver.findElements(ByClassName("MuiTypography-root MuiTypography-body1 css-yg30e6"));
        //titleOfSearchResult= title.titleOfSearchResult;
        parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/div/div[1]")).getText();
        titleOfSearchResult = this.parentElement.getText();
       return titleOfSearchResult;
    }

    private Object ByClassName(String string) {
        return null;
       // return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            //parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div[1]/div/div[1]/button"));
            WebElement element = parentElement.findElement(By.tagName("Button"));
            Thread.sleep(3000);
            element.click();
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            WebElement element = parentElement.findElement(By.tagName("Button"));
            Thread.sleep(3000);
            status=element.getText().equals("SIZE CHART");
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */
            WebElement sizeChartParent = driver.findElement(By.className("MuiDialog-paperScrollPaper"));
            WebElement tableElement = sizeChartParent.findElement(By.tagName("table"));
            List<WebElement> tableHeader = tableElement.findElement(By.tagName("thead")).findElements(By.tagName("th"));
            // System.out.println("value of status "+status);
            String tempHeaderValue;
            for(int i=0; i<expectedTableHeaders.size();i++){
                tempHeaderValue = tableHeader.get(i).getText();

                if(!expectedTableHeaders.get(i).equals(tempHeaderValue)){
                    System.out.println("Failure in Header Comparison: Expected: "+expectedTableHeaders.get(i) + "Actual:" +tempHeaderValue);
                    status = false;
                }
            }
            System.out.println("value of status-1 "+status);
            //List<WebElement> tableBodytr = driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/div/table/tbody/tr"));
            List<WebElement> tableBodyRows = tableElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            System.out.println("value of status-2 "+status);
            List<WebElement> tableBodyrow;
            for(int i=0;i<expectedTableBody.size();i++){
                tableBodyrow = tableBodyRows.get(i).findElements(By.tagName("td"));
                for(int j=0;j<expectedTableBody.get(i).size();j++){
                    tempHeaderValue = tableBodyrow.get(j).getText();
                    if(!expectedTableBody.get(i).get(j).equals(tempHeaderValue)){
                        return false;
                    }
                }
            }
            //return status;
            System.out.println("value of status"+status);
            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            status = parentElement.findElement(By.xpath("//*[@class='MuiCardContent-root css-1qw96cp']/../div[2]/div")).isDisplayed();
            Thread.sleep(3000);
            return status;
        } catch (Exception e) {
            return status;
        }
    }
    public Boolean WindowHandle(WebDriver driver) {
        Boolean status = false;
        try {
            
            String parent = driver.getWindowHandle();
            Set<String> s = driver.getWindowHandles();

            // Now iterate using Iterator
            Iterator<String> I1 = s.iterator();

            while (I1.hasNext()) {
            String child_window = I1.next();
        
              if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);

                System.out.println(driver.switchTo().window(child_window).getPageSource());

                String page = driver.getPageSource();
                if(page.contains("Privacy Policy")){
                    return true;
                }
                driver.close();
            }
        }
            driver.switchTo().window(parent);
            
            return status;
        } catch (Exception e) {
            return status;
        }
    }


}