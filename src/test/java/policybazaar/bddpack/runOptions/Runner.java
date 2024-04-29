package policybazaar.bddpack.runOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/policybazaar/bddpack/features",
        glue = "policybazaar.bddpack.definitions"
)
public class Runner {
}
