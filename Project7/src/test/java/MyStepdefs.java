import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    Divider d = new Divider();
    double dividend, divisor, res;
    double dividend_err, divisor_err;
    Exception actualException;

    @Given("input is 100 and 20")
    public void input() {
        dividend = 100;
        divisor = 20;
    }
    @When("I use divider")
    public void useDivider() {
        res = d.divide(dividend, divisor);
    }
    @Then("I should get {double}")
    public void getAns(double autual_res) {
        assertEquals(autual_res, res);
    }

    @Given("divisor is zero")
    public void inputZero() {
        dividend_err = 100;
        divisor_err = 0;
    }
    @When("I want to use divider")
    public void useDividerErr() throws Throwable {
        try {
            d.divide(dividend_err, divisor_err);
        } catch (Exception e) {
            actualException = e;
        }
    }

    @Then("I should get IllegalArgumentException")
    public void iShouldGetIllegalArgumentException() {
        assertEquals("IllegalArgumentException", actualException.getClass().getSimpleName());
    }
}