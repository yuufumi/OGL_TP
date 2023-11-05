import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class MyStepdefs {
    private Matrix m;
    private double[][] matrix;
    private double result;
    @Given("i have a Matrix defined as :")
    public void iHaveAMatrixDefinedAs(DataTable dt) {
        List<List<Double>> rows = dt.asLists(Double.class);
        matrix = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = Double.parseDouble(dt.cell(i,j));
            }
        }
        m = new Matrix(matrix);
    }

    @When("I calculate  Determinant  of the matrix")
    public void iCalculateDeterminantOfTheMatrix() {
        try {
            result = MatrixMathematics.determinant(m);
        } catch (NoSquareException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(double d) {
        assertEquals(d, result);
    }
}
