package PriceList;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;


public class PriceList {
    private final Map<Integer, Product> productMap = new HashMap<>();

    public void addProduct(Integer id, String name, String price) {
        productMap.put(id, new Product(name, price));
    }

    public boolean containsProduct(Integer id) {
        return productMap.containsKey(id);
    }

    public Double getProductPriceById(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).getPrice();
    }

    public String getProductNameById(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).getName();
    }

    public Product getProduct(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id);
    }

    public Integer getProductIdByName(String name) {
        Integer id = 0;
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            Product prod = entry.getValue();
            if (prod.name.equals(name) && name != null && prod.name != null) {
                id = entry.getKey();
                break;
            }
        }
        if (id.equals(0)) throw new NoSuchElementException();
        else return id;

    }

    public Double getProductPriceByName(String name) {
        return getProductPriceById(getProductIdByName(name));
    }

    public void setProductName(Integer id, String name) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setName(name);
    }

    public void setProductPriceStr(Integer id, String price) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setPriceStr(price);
    }

    public void setProductPriceDo(Integer id, Double price) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setPriceDo(price);
    }

    public void removeProduct(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        productMap.remove(id);
    }

    public Double purchaseCost(Integer id, Integer amount) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        if (amount < 0) throw new IllegalArgumentException();
        Double cost = getProductPriceById(id);
        return amount * cost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList = (PriceList) o;
        return Objects.equals(productMap, priceList.productMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productMap);
    }

    @Override
    public String toString() {
        return productMap.toString();
    }

    public static class Product {
        private String name;
        private Double price;

        public Product(String name, String price) {
            this.name = name;
            this.price = convert(price);
        }

        public Product(String name, Double price) {
            this.name = name;
            this.price = convert(price.toString());
        }

        private Double convert(String price) {
            if (price.contains("-")) throw new IllegalArgumentException();
            String regex = "[0-9]+";
            String[] prices = price.split("\\.");
            if (prices.length == 2) {
                if (!prices[0].matches(regex) || !prices[1].matches(regex))
                    throw new IllegalArgumentException("Неверный формат ввода");
                    if (prices[1].length() > 2) {
                        throw new IllegalArgumentException("Копеечная составляющая цены не может содержать более двух символов");
                    }
            } else if (prices.length > 2) throw new IllegalArgumentException("Неверный формат ввода");
            try {
                return Double.parseDouble(price);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Неверный формат ввода");
            }
        }

        private String getName() {
            if (this.name == null) throw new IllegalStateException("Имя не указано");
            return this.name;
        }

        private Double getPrice() {
            if (this.price == null) throw new IllegalStateException("Цена не указана");
            return this.price;
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setPriceStr(String price) {
            this.price = convert(price);
        }

        private void setPriceDo(Double price) {
            this.price = convert(price.toString());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Product) {
                return this.name.equals(((Product) o).name) &&
                        this.price.equals(((Product) o).price);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }

        @Override
        public String toString() {
            if (price == null) return name + " " + "цена не указана";
            return name + " " + price;
        }
    }

}


