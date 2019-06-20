package lambdasinaction.chap8;

abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);


    // dummy Customer class
    private static class Customer {
    }

    // dummy Datbase class
    private static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
