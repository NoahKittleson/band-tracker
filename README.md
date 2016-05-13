#_README for Band Tracker_#
###Created by Noah Kittleson###

##**Description**##
This is a webpage that allows the user to enter bands and venues.  Users may then add venues to each band, update, delete, or view the list of bands.

##**Setup**##
Clone the repository or otherwise download the source files to your computer.  Install Postgres and start it from the command line.  In PSQL, input the following commands:

CREATE DATABASE band_tracker;
/c band_tracker;
CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
CREATE TABLE venues (id serial PRIMARY KEY, name varchar);
CREATE DATABASE band_tracker_test WITH TEMPLATE band_tracker;

Then, within the command line navigate to the Band-Tracker file and run gradle.  This should set up a local server so that you can view the webpage on any modern browser at localhost:4567.

##**Technologies Used**
* Java
* Spark
* Velocity
* FluentLenium
* SQL
* CSS
* BootStrap
* HTML

##**Licensing**##
This is protected under the [MIT](https://en.wikipedia.org/wiki/MIT_License) license.
