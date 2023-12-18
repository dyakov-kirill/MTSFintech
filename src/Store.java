/**
 *  Класс магазина, хранящий информацию о количестве товаров, сумме товаров и скидке
 */
public class Store {
    public Store(int productsNum, float productsSum, float discount) { // Конструктор класс
        // Проверка переданных в конструктор пармаетров на неотрицательность
        this.productsNum = Math.max(productsNum, 0);
        this.productsSum = Math.max(productsSum, 0);
        this.discount = Math.max(discount, 0);
    }
    private int productsNum; // Количество товаров
    private float productsSum; // Сумма товара
    private float discount; // Скидка на товары

    public int getProductsNum() { // Геттер productsNum
        return productsNum;
    }

    public void setProductsNum(int productsNum) { // Сеттер productsNum
        if (productsNum >= 0) {
            this.productsNum = productsNum;
        }
    }

    public float getProductsSum() { // Геттер productsЫum
        return productsSum;
    }

    public void setProductsSum(float productsSum) { // Сеттер productsSum
        if (productsSum >= 0) {
            this.productsSum = productsSum;
        }
    }

    public float getDiscount() { // Геттер discount
        return discount;
    }

    public void setDiscount(float discount) { // Сеттер discount
        if (discount >= 0) {
            this.discount = discount;
        }
    }
}
