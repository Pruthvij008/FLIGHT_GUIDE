package org.example;
import java.util.ArrayList;
import java.util.*;

public class Main {
    static class Flight {
        String source;
        String destination;
        double price;
        double distance;

        public Flight(String source, String destination, double price, double distance) {
            this.source = source;
            this.destination = destination;
            this.price = price;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        ArrayList<Flight> flights = createFlightList();
        String source ; // Provide the source
        String destination; // Provide the destination

        String result = findFlights("London", "new york");
        System.out.println(result.replace("<br>", "<br>"));
    }

    public static String findFlights(String source, String destination) {
        StringBuilder output = new StringBuilder();
        ArrayList<Flight> flights = createFlightList();
        // Check if a direct flight is available
        boolean directFlightAvailable = false;
        double directFlightPrice = 0;
        double directFlightDistance = 0;

        for (Flight flight : flights) {
            if (flight.source.equalsIgnoreCase(source) && flight.destination.equalsIgnoreCase(destination)) {
                directFlightAvailable = true;
                directFlightPrice = flight.price;
                directFlightDistance = flight.distance;
                break;
            }
        }

        // Display flight details
        if (directFlightAvailable) {
          output.append("Direct flight available from ").append(source).append(" to ").append(destination).append("<br>");
//            System.out.println("Direct flight available from "+source+"to"+destination);
            output.append("Price: ").append(directFlightPrice).append("<br>");
            System.out.println();
            output.append("Distance: ").append(directFlightDistance).append("<br>");
            System.out.println();
        } else {
            output.append("No direct flight available from ").append(source).append(" to ").append(destination).append("<br>");
            output.append("Possible routes:").append("<br>");

            for (Flight flight : flights) {
                if (flight.source.equalsIgnoreCase(source)) {
                    ArrayList<Flight> route = findRoute(flight.destination, destination, flights);

                    if (route != null) {
                        double totalPrice = flight.price;
                        double totalDistance = flight.distance;

                        output.append("Route via ").append(flight.destination).append(":").append("<br>");
                        output.append("Price: ").append(totalPrice).append("<br>");
                        output.append("Distance: ").append(totalDistance).append("<br>");

                        for (Flight routeFlight : route) {
                            output.append(" -> ").append(routeFlight.destination).append("<br>");
                            totalPrice += routeFlight.price;
                            totalDistance += routeFlight.distance;
                        }

                        output.append("Total Price: ").append(totalPrice).append("<br>");
                        output.append("Total Distance: ").append(totalDistance).append("<br>");
                    }
                }
            }
        }

        return output.toString();
    }

    public static ArrayList<Flight> findRoute(String source, String destination, ArrayList<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.source.equalsIgnoreCase(source) && flight.destination.equalsIgnoreCase(destination)) {
                ArrayList<Flight> route = new ArrayList<>();
                route.add(flight);
                return route;
            } else if (flight.source.equalsIgnoreCase(source)) {
                ArrayList<Flight> route = findRoute(flight.destination, destination, flights);
                if (route != null) {
                    route.add(0, flight);
                    return route;
                }
            }
        }
        return null;
    }

    public static ArrayList<Flight> createFlightList() {
        ArrayList<Flight> flights = new ArrayList<>();
        // Add flight information for different routes
        flights.add(new Flight("MUMBAI", "PUNE", 500, 5500));
        flights.add(new Flight("MUMBAI", "NASHIK", 800, 6500));
        flights.add(new Flight("PUNE", "BANGALORE", 200, 3500));
        flights.add(new Flight("BANGALORE", "CHENNAI", 150, 2500));
        flights.add(new Flight("PUNE", "CHENNAI", 300, 4000));
        flights.add(new Flight("BANGALORE", "MUMBAI", 700, 6000));
        flights.add(new Flight("CHENNAI", "NASHIK", 900, 7000));
        flights.add(new Flight("DELHI", "ROME", 400, 5000));
        flights.add(new Flight("NASHIK", "DELHI", 100, 700));
        flights.add(new Flight("MUMBAI", "NASHIK", 900, 7000));
        return flights;
    }
}
