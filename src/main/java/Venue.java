import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Venue {
  private String name;
  private int id;

  public Venue (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public static List<Venue> all() {
    String sql = "SELECT id, name FROM venues";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue) {
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue =  (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
             this.getId() == newVenue.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues WHERE id = :id";
      Venue venue = con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }

  public void addBand(Band newBand) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (venue_id, band_id) VALUES (:venue_id, :band_id)";
      con.createQuery(sql)
        .addParameter("venue_id", this.id)
        .addParameter("band_id",newBand.getId())
        .executeUpdate();
    }
  }

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT band_id FROM bands_venues WHERE venue_id = :venue_id";

      List<Integer> bandIds =  con.createQuery(sql)
        .addParameter("venue_id", this.id)
        .executeAndFetch(Integer.class);

      List<Band> bands = new ArrayList<Band>();

      for (Integer band_id : bandIds) {
        String bandQuery = "Select * FROM bands WHERE id = :band_id";
        Band tempBand = con.createQuery(bandQuery)
          .addParameter("band_id", band_id)
          .executeAndFetchFirst(Band.class);
        bands.add(tempBand);
      }
      return bands;
    }
  }
}
