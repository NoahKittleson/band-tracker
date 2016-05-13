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
//
//   @Test
//   public void save_savesObjectIntoDatabase_true() {
//     Category myCategory = new Category("Dinner");
//     myCategory.save();
//     assertTrue(Category.all().get(0).equals(myCategory));
//   }
//
//   @Test
//   public void save_assignsIdToObject() {
//     Category myCategory = new Category("Dinner");
//     myCategory.save();
//     Category savedCategory = Category.all().get(0);
//     assertEquals(myCategory.getId(), savedCategory.getId());
//   }
//
//   @Test
//   public void find_findsCategoriesInDatabase_True() {
//     Category myCategory = new Category("Dinner");
//     myCategory.save();
//     Category savedCategory = Category.find(myCategory.getId());
//     assertTrue(myCategory.equals(savedCategory));
//   }
//
//   @Test
//   public void update_updatesRecipes_true() {
//     Category myCategory = new Category("Dinner");
//     myCategory.save();
//     myCategory.update("Brunch");
//     assertEquals("Brunch", Category.find(myCategory.getId()).getName());
//   }
//
//   @Test
//   public void delete_deletesRecipe_true() {
//     Category myCategory = new Category("Dinner");
//     myCategory.save();
//     int myCategoryId = myCategory.getId();
//     myCategory.delete();
//     assertEquals(null, Category.find(myCategoryId));
//   }
//
//
// @Test
// public void addBook_addsBookToAuthor() {
//   Book myBook = new Book("Tom Sawyer");
//   myBook.save();
//   Author myAuthor = new Author("Dave", "Smith");
//   myAuthor.save();
//   myAuthor.addBook(myBook);
//   Book savedBook = myAuthor.getBooks().get(0);
//   assertTrue(myBook.equals(savedBook));
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
