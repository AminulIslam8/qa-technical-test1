import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Assert;


public class DailySongsTest {

    private String jsonBody;
    private HttpResponse response;
    private Content content;

    @Given("^I have an artist \"([^\"]*)\", and a song\"([^\"]*)\", and a publish date\"([^\"]*)\"$")
    public void i_have_an_artist_and_a_song_and_a_publish_date(String artist, String song, String publish_date) throws Throwable {

        String uriTemplate = " {\"artist\": \"ReplaceArtist\", \"song\": \"ReplaceSong\", \"publishDate\": \"ReplaceDate\" }";

        jsonBody = uriTemplate.replaceAll("ReplaceArtist", artist).replaceAll("ReplaceSong", song).replaceAll("ReplaceDate", publish_date);
    }

    @When("^I make a post request to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_make_a_post_request_to_and(String host, String port) throws Throwable {

        response = Request.Post(host + port)
                .useExpectContinue()
                .version(HttpVersion.HTTP_1_1)
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .execute().returnResponse();

    }

    @Then("^I should have the correct status code \"([^\"]*)\"$")
    public void i_should_have_the_correct_status_code(String expectedStatusCode) throws Throwable {
        final String actualStatusCode = String.valueOf(response.getStatusLine().getStatusCode());
        Assert.assertEquals("Status code does not match", expectedStatusCode, actualStatusCode);
    }

    @When("^I make a get request to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_make_a_get_request_to_and(String host, String port) throws Throwable {

        content = Request.Get(host + port)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent();
    }

    @Then("^I should have a list of videos$")
    public void i_should_have_a_list_of_videos() throws Throwable {
        Assert.assertTrue(!content.asString().isEmpty());
    }


    @When("^I make a get request to \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_make_a_get_request_to_and_and(String host, String port, String id) throws Throwable {
        content = Request.Get(host + port +id)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent();
    }


    @Then("^I should have a matching video of artist \"([^\"]*)\"$")
    public void i_should_have_a_matching_video_of_artist(String artist) throws Throwable {

        Assert.assertTrue(content.asString().contains(artist));
    }
}
