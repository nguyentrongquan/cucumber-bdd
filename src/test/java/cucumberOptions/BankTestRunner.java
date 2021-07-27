package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features ="src/test/java/bankguru/features",
	glue="bank.stepDefinitions",
	monochrome = true,
	plugin = {"pretty","html:target/site/cucumber-report-defaul","json:target/site/cucumber_bankGuru.json"},
	snippets = SnippetType.CAMELCASE,
	tags = {"@register_login"})

public class BankTestRunner {

}
