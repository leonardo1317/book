package retry;

public class RetryTemplate {
    //https://dzone.com/articles/java-8-lambda-functions-usage-examples

    public void retry(LambdaFunction lambdaFunction, int maxTries, int delay) {

        int count = 0;
        Exception exception = null;

        if (maxTries == 0) {
            lambdaFunction.call(exception);
            return;
        }

        while (true) {
            sleep(delay);
            try {
                lambdaFunction.call(exception);
                break;
            } catch (Exception ex) {

                if (++count == maxTries) {
                    break;
                }
                exception = ex;
                System.out.println("Reintento # " + count);
            }

        }

    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    public interface LambdaFunction {

        public void call(Exception ex);
    }

}
