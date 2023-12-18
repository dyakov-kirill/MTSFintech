public class Main {
    public static void main(String[] args) {
        Store storeOne = new Store(100, 20, 0.75f);
        printCalculations(storeOne);
        Store storeTwo = new Store(32, 64, 42.575f);
        printCalculations(storeTwo);
        Store storeThree = new Store(420, 15, 59.1f);
        printCalculations(storeThree);
    }

    public static void printCalculations(Store store) {
        if (store != null) {
            float totalSum = store.getProductsNum() * store.getProductsSum();
            float discountSum = totalSum * (1 - store.getDiscount() / 100);
            System.out.printf("Общая сумма: %.2f\nСумма со скидкой: %.2f\n", totalSum, discountSum);
        }
    }
}
