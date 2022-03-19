package test;


import org.junit.Before;
import org.junit.Test;
import pricelist.PriceList;


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
        priceList.addProductWithStringPrice(id1, prName1, price1);
        priceList.addProductWithStringPrice(id2, prName2, price2);
        priceList.addProductWithStringPrice(id3, prName3, price3);
        priceList.addProductWithStringPrice(id4, prName4, price4);

    }

    @Test
    public void addProductWithStringPrice() {
        assertTrue(priceList.addProductWithStringPrice(id5, prName5, price1));
        assertFalse(priceList.addProductWithStringPrice(id1, prName5, price1));

    }

    @Test
    public void addProductWithDoublePrice() {
        assertTrue(priceList.addProductWithDoublePrice(id5, prName5, price1d));
        assertFalse(priceList.addProductWithDoublePrice(id1, prName5, price1d));

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
    }

    @Test
    public void getProductNameById() {
        assertEquals(prName1, priceList.getProductNameById(id1));
        assertEquals(prName2, priceList.getProductNameById(id2));
        assertEquals(prName3, priceList.getProductNameById(id3));
        assertEquals(prName4, priceList.getProductNameById(id4));

    }

    @Test
    public void getProduct() {
        assertEquals(new PriceList.Product(prName1, price1), priceList.getProduct(id1));
        assertEquals(new PriceList.Product(prName2, price2), priceList.getProduct(id2));
        assertEquals(new PriceList.Product(prName3, price3), priceList.getProduct(id3));
        assertEquals(new PriceList.Product(prName4, price4), priceList.getProduct(id4));
    }

    @Test
    public void getProductIdByName() {
        assertEquals(id1, priceList.getProductIdByName(prName1));
        assertEquals(id2, priceList.getProductIdByName(prName2));
        assertEquals(id3, priceList.getProductIdByName(prName3));

    }

    @Test
    public void getProductPriceByName() {
        assertEquals(price1d, priceList.getProductPriceByName(prName1));
        assertEquals(price2d, priceList.getProductPriceByName(prName2));
        assertEquals(price3d, priceList.getProductPriceByName(prName3));

    }

    @Test
    public void setProductName() {
        assertTrue(priceList.setProductName(id4, prName5));
        assertFalse(priceList.setProductName(id5, prName5));
        assertFalse(priceList.setProductName(id4, null));

    }

    @Test
    public void setProductStringPrice() {
        assertTrue((priceList.setProductStringPrice(id2, price1)));
        assertFalse((priceList.setProductStringPrice(id2, "333/00")));
        assertFalse((priceList.setProductStringPrice(id2, "333.99999")));
        assertFalse((priceList.setProductStringPrice(id5, "55.01")));
    }

    @Test
    public void setProductDoublePrice() {
        assertTrue((priceList.setProductDoublePrice(id2, price1d)));
        assertFalse((priceList.setProductDoublePrice(id2, 33.0111)));
        assertFalse((priceList.setProductDoublePrice(id5, 22.22)));
    }

    @Test
    public void removeProduct() {
        assertTrue(priceList.removeProduct(id1));
        assertFalse(priceList.removeProduct(id5));
    }

    @Test
    public void purchaseCost() {
        assertEquals(cost1, priceList.purchaseCost(id1, amount1));
        assertEquals(cost2, priceList.purchaseCost(id2, amount2));
        assertEquals(cost3, priceList.purchaseCost(id3, amount3));
        assertEquals(cost4, priceList.purchaseCost(id4, amount4));
    }


}
