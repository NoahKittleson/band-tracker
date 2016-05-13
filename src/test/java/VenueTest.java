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
  //
  // @Test
  // public void equals_returnsTrueIfNamesAreTheSame_true() {
  //   Band firstBand = new Band("The Cheatles");
  //   Band secondBand = new Band("The Cheatles");
  //   assertTrue(firstBand.equals(secondBand));
  // }
  //
  // @Test
  // public void save_savesObjectIntoDatabase_true() {
  //   Band myBand = new Band("King Harkinian's Magical Minions");
  //   myBand.save();
  //   assertTrue(Band.all().get(0).equals(myBand));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Band myBand = new Band("Methro Skull");
  //   myBand.save();
  //   Band savedBand = Band.all().get(0);
  //   assertEquals(myBand.getId(), savedBand.getId());
  // }
  //
  // @Test
  // public void find_findsBandsInDatabase_True() {
  //   Band myBand = new Band("The Antarctic Monkeys");
  //   myBand.save();
  //   Band savedBand = Band.find(myBand.getId());
  //   assertTrue(myBand.equals(savedBand));
  // }
}
