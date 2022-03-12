package PriceListTest;

import org.junit.Before;
import org.junit.Test;
import PriceList.PriceList;

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

    String price1 = "249.99";
    String price2 = "1";
    String price3 = "555";
    String price4 = "290.00";

    Double price1d = 249.99;
    Double price2d = 1.0;
    Double price3d = 555.0;
    Double price4d = 290.0;

    Integer amount1 = 9;
    Integer amount2 = 5;
    Integer amount3 = 900;
    Integer amount4 = 88;

    Double cost1 = 2249.91;
    Double cost2 = 5.0;
    Double cost3 = 499500.0;
    Double cost4 = 25520.0;

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
        assertTrue(priceList.containsProduct(id1));
    }

    @Test
    public void getProductPriceById() {
        assertEquals(price1d, priceList.getProductPriceById(id1));
        assertEquals(price2d, priceList.getProductPriceById(id2));
        assertEquals(price3d, priceList.getProductPriceById(id3));
        assertEquals(price4d, priceList.getProductPriceById(id4));
        try {
            priceList.getProductPriceById(34);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductNameById() {
        assertEquals(prName1, priceList.getProductNameById(id1));
        assertEquals(prName2, priceList.getProductNameById(id2));
        assertEquals(prName3, priceList.getProductNameById(id3));
        assertEquals(prName4, priceList.getProductNameById(id4));
        try {
            priceList.getProductNameById(34);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProduct() {
        assertEquals(new PriceList.Product(prName1, price1), priceList.getProduct(id1));
        assertEquals(new PriceList.Product(prName2, price2), priceList.getProduct(id2));
        assertEquals(new PriceList.Product(prName3, price3), priceList.getProduct(id3));
        assertEquals(new PriceList.Product(prName4, price4), priceList.getProduct(id4));
        try {
            priceList.getProduct(id5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductIdByName() {
        assertEquals(id1, priceList.getProductIdByName(prName1));
        assertEquals(id2, priceList.getProductIdByName(prName2));
        assertEquals(id3, priceList.getProductIdByName(prName3));
        try {
            priceList.getProductIdByName(prName5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getProductPriceByName() {
        assertEquals(price1d, priceList.getProductPriceByName(prName1));
        assertEquals(price2d, priceList.getProductPriceByName(prName2));
        assertEquals(price3d, priceList.getProductPriceByName(prName3));

    }

    @Test
    public void setProductName() {
        priceList.setProductName(id1, prName5);
        assertEquals(prName5, priceList.getProductNameById(id1));
        try {
            priceList.setProductName(id5, prName5);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void setProductPriceStr() {
        priceList.setProductPriceStr(id1, price3);
        assertEquals(price3d, priceList.getProductPriceById(id1));
        try {
            priceList.setProductPriceStr(id5, price3);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void setProductPriceDo() {
        priceList.setProductPriceDo(id1, price3d);
        assertEquals(price3d, priceList.getProductPriceById(id1));
        try {
            priceList.setProductPriceDo(id5, price3d);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void removeProduct() {
        priceList.removeProduct(id1);
        assertFalse(priceList.containsProduct(id1));
        priceList.removeProduct(id2);
        assertFalse(priceList.containsProduct(id2));
    }

    @Test
    public void purchaseCost() {
        assertEquals(cost1, priceList.purchaseCost(id1, amount1));
        assertEquals(cost2, priceList.purchaseCost(id2, amount2));
        assertEquals(cost3, priceList.purchaseCost(id3, amount3));
        assertEquals(cost4, priceList.purchaseCost(id4, amount4));
        try {
            priceList.purchaseCost(id5, amount1);
        } catch (NoSuchElementException e) {
        }
    }


}
