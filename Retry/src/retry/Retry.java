/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retry;

/**
 *
 * @author leo
 */
public class Retry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /* LambdaFunction lambdaFunction = () -> {
            ht();
        };*/
 /* retry(() -> {
            // ht();

        }, 3, 1000);*/
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.retry((a) -> {
            System.out.println(a);
            Object obj = null;
            obj.hashCode();
        }, 4, 1000);

    }

    public static void retry(LambdaFunction lambdaFunction, int maxTries, int delay) {

        int count = 0;

        while (true) {
            sleep(delay);
            try {
                /* String[] test = new String[1];
                test[2] = "4";*/
 /*Object obj = null;
                obj.hashCode();
                System.out.println("sdsdsd");*/

                lambdaFunction.call();

                // Some Code
                // break out of loop, or return, on success
                break;
            } catch (Exception ex) {
                // handle exception          
                if (++count == maxTries) {
                    System.out.println(ex);
                    break;
                }
                System.out.println("intento # " + count);
            }

        }

    }

    private interface LambdaFunction {

        void call();
    }

    private static void ht() {

        System.out.println("Hello world");
        Object obj = null;
        obj.hashCode();

    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}
