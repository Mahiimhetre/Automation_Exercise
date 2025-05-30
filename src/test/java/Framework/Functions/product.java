package Framework.Functions;

//ProductPage	- Search for a product - Open product detail page - Verify product details - verify product price according to quantity


import Framework.Elements.HomePage;
import Framework.Elements.ProductPage;

import static Framework.Functions.common.driver;
import static Framework.Functions.common.log;

public class product {
    HomePage hp = new HomePage(driver);
    ProductPage prodp = new ProductPage(driver);

    public void searchForProduct(String productName) throws Exception {
        hp.prodPage.click();
        prodp.SearchResults.clear();// Clear the search input before entering a new product name
        prodp.SearchProd.sendKeys(productName);
        prodp.searchBtn.click();
        log().info("Searching Product for your search: '" + productName + "'..."); 
        if (prodp.SearchResults.isEmpty()) {
            log().warning("No products found for the search: '" + productName + "'");
        } else {
            log().info("Found " + prodp.SearchResults.size() + " products for the search: '" + productName + "'");
        }
    }
    public void openProductDetailPage(int productIndex) {
        if (productIndex < prodp.SearchResults.size()) {
            prodp.SearchResults.get(productIndex).click();
        } else {
            throw new IndexOutOfBoundsException("Product index out of bounds");
        }
    }
}
