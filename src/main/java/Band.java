import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Band {
  private int id;
  private String name;

  public Band(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Band> all() {
    String sql = "SELECT id, name FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand) {
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName()) &&
              this.getId() == newBand.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands WHERE id = :id";
      Band band = con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public void update(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("name", newName)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM bands WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();

      String joinTableDelete = "DELETE FROM bands_venues WHERE band_id = :band_id";
      con.createQuery(joinTableDelete)
        .addParameter("band_id", this.id)
        .executeUpdate();
    }
  }

  public void addVenue(Venue newVenue) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", this.id)
        .addParameter("venue_id",newVenue.getId())
        .executeUpdate();
    }
  }

  public List<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT venue_id FROM bands_venues WHERE band_id = :band_id";

      List<Integer> venueIds =  con.createQuery(sql)
        .addParameter("band_id", this.id)
        .executeAndFetch(Integer.class);

      List<Venue> venues = new ArrayList<Venue>();

      for (Integer venue_id : venueIds) {
        String venueQuery = "Select * FROM venues WHERE id = :venue_id";
        Venue tempVenue = con.createQuery(venueQuery)
          .addParameter("venue_id", venue_id)
          .executeAndFetchFirst(Venue.class);
        venues.add(tempVenue);
      }
      return venues;
    }
  }

}
