Author: Tim Lindquist (Tim.Lindquist@asu.edu), ASU Polytechnic, CIDSE, SE
Version: January 2020

See http://pooh.poly.asu.edu/Ser321

Purpose: provide basis for assignment 2 solution for Ser321 Spring 2020.
This sample contains open source source code that you may use in generating
student solutions. It also contains in compiled form code that is not
open source, but is freely availble through download in the US. This includes
the classes to manipulate JSON, Gluon's JavaFX, and the instructor's view
user-interface for the assignment. You will need to register with
Last.fm web services to obtain an api key to get this example to run properly.
See: https://www.last.fm/api/

This project is designed to run on either Debian Buster Linux or MacOS. It
requires jdk13.

Building and running the sample is done with Ant.
This example depends on the following frameworks:
1. Ant
   see: http://ant.apache.org/
2. Json for Java as implemented (reference) by Doug Crockford.
   See: https://github.com/stleary/JSON-java
3. Gluon's JavaFX for Java13 on Linux.
   See: https://gluonhq.com/products/javafx/

To build and run the example, you will need to have Ant installed on
your system, with the antlib target extensions.
(see the CppFraction example from unit1 material for installing these in your home directory).

Run the sample with the command:
ant sample -Dlastfmkey=place-your-last.fm-key-here -DuserId=Tim.Lindquist

//Change
ant sample -Dlastfmkey=f4836f334270ab7f2746c5d8927d89a7 -DuserId=Stephon.Patton

To clean the project directory:
ant clean

The project directory also includes the html file for the assignment (assign2.html)
and a docs directory containing the javadocs for the instructor provided software
for the user-interface view, which you will use in creating your solution.
load .../Assign2/docs/index.html in a browser.

end
