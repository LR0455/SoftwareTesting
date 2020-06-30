public class Divider {
    public double divide(double dividend, double divisor) {
        if (divisor == 0)
            throw new IllegalArgumentException("divisor is zero");
        return dividend / divisor;
    }

}
