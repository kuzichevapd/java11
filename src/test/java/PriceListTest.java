import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class PriceListTest {

    PriceList priceList = new PriceList();

    Integer id1 = 1;
    Integer id2 = 2;
    Integer id3 = 3;
    Integer id4 = 4;
    Integer id5 = 5;

    String prName1 = "Хлеб";
    String prName2 = "Молоко";
    String prName3 = "Яйцо";
    String prName4 = "Помидор";
    String prName5 = "Кефир";
    String prName6 = "Апельсин";

    String price1 = "249.99";
    String price2 = "1";
    String price3 = "555";
    String price4 = "290.00";
    String price5 = "1000.0";

    Integer amount1 = 9;
    Integer amount2 = 5;
    Integer amount3 = 900;
    Integer amount4 = 88;

    @Before
    public void setUp() {
        priceList.addProduct(id1, prName1, price1);
        priceList.addProduct(id2, prName2, price2);
        priceList.addProduct(id3, prName3, price3);
        priceList.addProduct(id4, prName4, price4);
    }

    @Test
    public void addProduct() {
        try {
            priceList.addProduct(id5, prName5, "7777-9");
        } catch (IllegalArgumentException e) {
        }
        try {
            priceList.addProduct(id5, prName5, "7777.8976");
        } catch (IllegalArgumentException e) {
        }
        try {
            priceList.addProduct(id4, prName5, "7777.89");
        } catch (IllegalArgumentException e) {
        }
        try {
            priceList.addProduct(id5, prName5, "777pp.89");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void containsProduct() {
        assertEquals(true, priceList.containsProduct(id1));
    }

    @Test
    public void getProductPricebyId() {
        assertEquals(price1, priceList.getProductPricebyId(id1));
        assertEquals(price2 + ".00", priceList.getProductPricebyId(id2));
        assertEquals(price3 + ".00", priceList.getProductPricebyId(id3));
        assertEquals(price4, priceList.getProductPricebyId(id4));
        try {
            priceList.getProductPricebyId(34);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductNamebyId() {
        assertEquals(prName1, priceList.getProductNamebyId(id1));
        assertEquals(prName2, priceList.getProductNamebyId(id2));
        assertEquals(prName3, priceList.getProductNamebyId(id3));
        assertEquals(prName4, priceList.getProductNamebyId(id4));
        try {
            priceList.getProductNamebyId(34);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProduct() {
        assertEquals(prName1 + " " + Double.parseDouble(price1), priceList.getProduct(id1));
        assertEquals(prName2 + " " + Double.parseDouble(price2), priceList.getProduct(id2));
        assertEquals(prName3 + " " + Double.parseDouble(price3), priceList.getProduct(id3));
        assertEquals(prName4 + " " + Double.parseDouble(price4), priceList.getProduct(id4));
        try {
            priceList.getProduct(id5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductIdbyName() {
        assertEquals(id1, priceList.getProductIdbyName(prName1));
        assertEquals(id2, priceList.getProductIdbyName(prName2));
        assertEquals(id3, priceList.getProductIdbyName(prName3));
        try {
            priceList.getProductIdbyName(prName5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductPricebyName() {
        assertEquals(price1, priceList.getProductPricebyName(prName1));
        assertEquals(price2 + ".00", priceList.getProductPricebyName(prName2));
        assertEquals(price3 + ".00", priceList.getProductPricebyName(prName3));

    }

    @Test
    public void setProductName() {
        priceList.setProductName(id1, prName5);
        assertEquals(prName5, priceList.getProductNamebyId(id1));
        try {
            priceList.setProductName(id5, prName5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void setProductPrice() {
        priceList.setProductPrice(id1, price3);
        assertEquals(price3 + ".00", priceList.getProductPricebyId(id1));
        try {
            priceList.setProductPrice(id5, prName5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void removeProduct() {
        priceList.removeProduct(id1);
        assertEquals(false, priceList.containsProduct(id1));
        priceList.removeProduct(id2);
        assertEquals(false, priceList.containsProduct(id2));
    }

    @Test
    public void purchaseCost() {
        assertEquals("2249.91", priceList.purchaseCost(id1, amount1));
        assertEquals("5.0", priceList.purchaseCost(id2, amount2));
        assertEquals("499500.0", priceList.purchaseCost(id3, amount3));
        assertEquals("25520.0", priceList.purchaseCost(id4, amount4));
        try {
            priceList.purchaseCost(id5, amount1);
        } catch (NoSuchElementException e) {
        }
    }


}
