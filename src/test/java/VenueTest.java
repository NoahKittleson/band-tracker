import org.junit.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("Canterbury Ales");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_instantiatesWithName_string() {
    Venue myVenue = new Venue("The Scarlet Gazelle");
    assertEquals("The Scarlet Gazelle", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame_true() {
    Venue firstVenue = new Venue("D Bar");
    Venue secondVenue = new Venue("D Bar");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Venue myVenue = new Venue("The Watering Hole");
    myVenue.save();
    assertTrue(Venue.all().get(0).equals(myVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue myVenue = new Venue("Wizard Palace");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findsVenuesInDatabase_True() {
    Venue myVenue = new Venue("Old Pokey's Tavern");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void addBand_addsBandToVenue() {
    Venue myVenue = new Venue("Tom Sawyer");
    myVenue.save();
    Band myBand = new Band("Dave's Dive Bar");
    myBand.save();
    myVenue.addBand(myBand);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Venue myVenue = new Venue("Tom Sawyer");
    myVenue.save();
    Band myBand = new Band("Huckleberry Inn");
    myBand.save();
    myVenue.addBand(myBand);
    List savedBands = myVenue.getBands();
    assertEquals(1, savedBands.size());
  }
}
