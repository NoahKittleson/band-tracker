import org.sql2o.*;
import java.util.List;

public class Band {
  private String name;

  public Band(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
