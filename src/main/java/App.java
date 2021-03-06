import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands", (request, response) -> {
      String name = request.queryParams("band");
      if (name.equals("")) {
        response.redirect("http://localhost:4567/");
        return null;
      }
      Band band = new Band(name);
      band.save();
      response.redirect("http://localhost:4567/bands");
      return null;
    });

    get("/bands", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues", (request, response) -> {
      String name = request.queryParams("venue");
      if (name.equals("")) {
        response.redirect("http://localhost:4567/");
        return null;
      }
      Venue venue = new Venue(name);
      venue.save();
      response.redirect("/");
      return null;
    });

    get("/bands/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      model.put("band", band);
      model.put("allVenues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id/edit", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band savedBand = Band.find(bandId);
      String newName = request.queryParams("band");
      savedBand.update(newName);
      String url = String.format("http://localhost:4567/bands/%d", savedBand.getId());
      response.redirect(url);
      return null;
    });

    get("/bands/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      model.put("band", band);
      model.put("template", "templates/band-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id/delete", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band savedBand = Band.find(bandId);
      savedBand.delete();
      response.redirect("http://localhost:4567/bands");
      return null;
    });

    post("bands/:id/add_venue", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      int venueId = Integer.parseInt(request.queryParams("venue"));
      Band band = Band.find(bandId);
      Venue venue = Venue.find(venueId);
      band.addVenue(venue);

      String url = String.format("http://localhost:4567/bands/%d", bandId);
      response.redirect(url);
      return null;
    });

    get("/venues/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params("id")));
      model.put("venue", venue);
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
