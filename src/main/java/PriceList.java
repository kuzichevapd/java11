import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;


public class PriceList {
    private Map<Integer, Product> productMap = new HashMap<>();

    public void addProduct(Integer id, String name, String price) {
        if (productMap.containsKey(id)) throw new IllegalArgumentException("Продукт с таким номером уже есть в списке");
        Product tryprice = new Product(name, price);
        if (!(tryprice.price == 0.0)) productMap.put(id, new Product(name, price));
        else throw new IllegalArgumentException("Неверный формат ввода");
    }

    public boolean containsProduct(Integer id) {
        return productMap.containsKey(id);
    }

    public String getProductPricebyId(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        if (productMap.get(id).getPrice() == null) throw new IllegalStateException("Цена "
                + productMap.get(id).getName() + " не указана");
        return productMap.get(id).getPrice();
    }

    public String getProductNamebyId(Integer id){
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        if (productMap.get(id).getName() == null) throw new IllegalStateException("Имя не указано");
        return productMap.get(id).getName();
    }

    public String getProduct(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        return productMap.get(id).toString();
    }

    public Integer getProductIdbyName(String name) {
        Integer id = 0;
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            Product prod = entry.getValue();
            if (prod.name.equals(name)) {
                id = entry.getKey();
            }
        }
        if (id.equals(0)) {
            throw new NoSuchElementException();
        } else return id;

    }

    public String getProductPricebyName(String name) {
        return getProductPricebyId(getProductIdbyName(name));
    }

    public void setProductName(Integer id, String name) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setName(name);
    }

    public void setProductPrice(Integer id, String price) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        (productMap.get(id)).setPrice(price);
    }

    public void removeProduct(Integer id) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        productMap.remove(id);
    }

    public String purchaseCost(Integer id, Integer amount) {
        if (!productMap.containsKey(id)) throw new NoSuchElementException();
        Double cost = Double.parseDouble(getProductPricebyId(id));
        Double totalAmount = amount * cost;
        return totalAmount.toString();
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

    private static class Product {
        private String name;
        private Double price;

        Product(String name, String price) {
            this.name = name;
            this.price = convert(price);
        }

        private Double convert(String price) {
            if (price.contains("-")) throw new IllegalArgumentException();
            String[] prices = price.split("\\.");
            if (prices.length == 2) {
                if (prices[1].length() > 2) {
                    throw new IllegalArgumentException("Копеечная составляющая цены не может содержать более двух символов");
                }
            } else if (prices.length > 2) throw new IllegalArgumentException("Неверный формат ввода");
            try {
                double v = Double.parseDouble(price);
                return v;
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }

        private String getName() {
            return this.name;
        }

        private String getPrice() {
            String[] prices = this.price.toString().split("\\.");
            String priceconv = this.price.toString();
            if (prices.length < 2) priceconv += ".00";
            if (prices[1].length() == 1) priceconv += "0";
            return priceconv;
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setPrice(String price) {
            this.price = convert(price);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Product) {
                if (this.name.equals(((Product) o).name) &&
                        this.price.equals(((Product) o).price)) return true;
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


