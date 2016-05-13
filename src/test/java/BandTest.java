import org.junit.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("CD Romp");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_instantiatesWithName_string() {
    Band myBand = new Band("MGM Tea");
    assertEquals("MGM Tea", myBand.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame_true() {
    Band firstBand = new Band("The Cheatles");
    Band secondBand = new Band("The Cheatles");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Band myBand = new Band("King Harkinian's Magical Minions");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void save_assignsIdToObject() {
    Band myBand = new Band("Methro Skull");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(myBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findsBandsInDatabase_True() {
    Band myBand = new Band("The Antarctic Monkeys");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void update_updatesRecipes_true() {
    Band myBand = new Band("Game of Thorns");
    myBand.save();
    myBand.update("Dinner is Coming");
    assertEquals("Dinner is Coming", Band.find(myBand.getId()).getName());
  }

  @Test
  public void delete_deletesRecipe_true() {
    Band myBand = new Band("The Venture Mothers");
    myBand.save();
    int myBandId = myBand.getId();
    myBand.delete();
    assertEquals(null, Band.find(myBandId));
  }


  // @Test
  // public void addBand_addsBandToVenue() {
  //   Band myBand = new Band("Tom Sawyer");
  //   myBand.save();
  //   Venue myVenue = new Venue("Dave's Dive Bar");
  //   myVenue.save();
  //   myVenue.addBand(myBand);
  //   Band savedBand = myVenue.getBands().get(0);
  //   assertTrue(myBand.equals(savedBand));
  // }
//
// @Test
// public void getBooks_returnsAllBooks_List() {
//   Book myBook = new Book("Tom Sawyer");
//   myBook.save();
//   Author myAuthor = new Author("Dave", "Smith");
//   myAuthor.save();
//   myAuthor.addBook(myBook);
//   List savedBooks = myAuthor.getBooks();
//   assertEquals(1, savedBooks.size());
// }
//
// @Test
// public void delete_deletesAllAuthorAndBookAssociations() {
//   Book myBook = new Book("Tom Sawyer");
//   myBook.save();
//   Author myAuthor = new Author("Dave", "Smith");
//   myAuthor.save();
//   myAuthor.addBook(myBook);
//   myAuthor.delete();
//   assertEquals(0, myBook.getAuthors().size());
// }
}
