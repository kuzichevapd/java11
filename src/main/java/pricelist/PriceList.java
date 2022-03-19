package pricelist;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;


public class PriceList {
    private final Map<Integer, Product> productMap = new HashMap<>();

    public boolean addProductWithStringPrice(Integer id, String name, String price) {
        if (!productMap.containsKey(id)) {
            productMap.put(id, new Product(name, price));
            return true;
        } else return false;
    }

    public boolean addProductWithDoublePrice(Integer id, String name, Double price) {
        if (!productMap.containsKey(id)) {
            productMap.put(id, new Product(name, price));
            return true;
        } else return false;
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
            if (prod.name != null && !name.isEmpty() && prod.name.equals(name)) {
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

    public boolean setProductName(Integer id, String name) {
        if (productMap.containsKey(id)) {
            return (productMap.get(id)).setName(name);
        }
        return false;
    }

    public boolean setProductStringPrice(Integer id, String price) {
        if (productMap.containsKey(id)) {
            return (productMap.get(id)).setStringPrice(price);
        }
        return false;
    }

    public boolean setProductDoublePrice(Integer id, Double price) {
        if (productMap.containsKey(id)) {
            return (productMap.get(id)).setDoublePrice(price);
        }
        return false;
    }

    public boolean removeProduct(Integer id) {
        if (productMap.containsKey(id)) {
            productMap.remove(id);
            return true;
        } else return false;
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
            if (check(price)) this.price = price;
            else throw
                    new IllegalArgumentException("Копеечная составляющая цены не может превышать два знака после запятой");
        }

        private Double convert(String price) {
            if (!price.matches("[0-9]+[.][0-9]{2}$")) return 0.0;
            else return Double.parseDouble(price);
        }

        private boolean check(Double price) {
            if (price == null) return false;
            String[] splitter = String.valueOf(price).split("\\.");
            return (splitter[1].length() == 2);
        }

        private String getName() {
            if (this.name == null) throw new IllegalStateException("Имя не указано");
            return this.name;
        }

        private Double getPrice() {
            if (this.price == null) throw new IllegalStateException("Цена не указана");
            return this.price;
        }

        private boolean setName(String name) {
            if (name != null && !name.isEmpty()) {
                this.name = name;
                return true;
            } else return false;
        }

        private boolean setStringPrice(String price) {
            if (convert(price) != 0.0) {
                this.price = convert(price);
                return true;
            } else return false;
        }

        private boolean setDoublePrice(Double price) {
            if (check(price)) {
                this.price = price;
                return true;
            } else return false;
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
            if (price == null || name.isEmpty()) throw new IllegalStateException();
            return name + " " + price;
        }
    }

}


