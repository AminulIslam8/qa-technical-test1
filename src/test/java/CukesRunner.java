

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions (
        monochrome = true,
        features = "src/test/java/features",
        plugin = {"pretty", "html:target/cucumber-html-report"},
        //glue = " ",
        tags = "@dailySongPost"
)

public class CukesRunner {
}
