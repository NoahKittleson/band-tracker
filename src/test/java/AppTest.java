import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Track a Band");
  }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#band").with("Big Burger");
    submit("#save-band");
    assertThat(pageSource()).contains("Big Burger");
  }

  @Test
  public void venueIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#venue").with("The Ike Box");
    submit("#save-venue");
    assertEquals(Venue.all().size(), 1);
  }

  @Test
  public void venueAddedToBandPage() {
    Band newBand = new Band("The Pie Bakers");
    newBand.save();
    Venue newVenue = new Venue("Flour Power");
    newVenue.save();
    newBand.addVenue(newVenue);
    String url = String.format("http://localhost:4567/bands/%d", newBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("Flour Power");
  }

  @Test
  public void updateBand() {
    Band newBand = new Band("The Pie Bakers");
    newBand.save();
    String url = String.format("http://localhost:4567/bands/%d/edit", newBand.getId());
    goTo(url);
    fill("#band").with("The Cake Bakers");
    submit("#update-band");
    assertThat(pageSource()).contains("The Cake Bakers");
  }


}
