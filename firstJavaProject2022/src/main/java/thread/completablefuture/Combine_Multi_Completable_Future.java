package thread.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Combine_Multi_Completable_Future {


    static void main() {


        CompletableFuture<String> userDataFuture = CompletableFuture.supplyAsync(() -> getUserData());
        CompletableFuture<String> customerDataFuture = CompletableFuture.supplyAsync(() -> getCustomerData());
        CompletableFuture<String> managerDataFuture = CompletableFuture.supplyAsync(() -> getManagerData());

        //Once all the future are completed, then we can combine them by passing them inside a method
        CompletableFuture.allOf(userDataFuture, customerDataFuture, managerDataFuture)
                .thenRun(() -> {
                    try {
                        combineAllData(userDataFuture.get(), customerDataFuture.get(), managerDataFuture.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).join();
    }

    public static void combineAllData( String userData, String customerData, String managerData) {
        System.out.println("Combined Data :: " + userData + " " + customerData + " " + managerData);
    }

    private static String getManagerData() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Manager Data";
    }

    private static String getCustomerData() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Customer Data";
    }

    private static String getUserData() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "User Data";
    }
}
