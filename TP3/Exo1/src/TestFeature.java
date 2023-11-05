import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features/DET.feature",plugin ={"pretty", "html:reports.html"})
public class TestFeature {
    // classe vide
}
