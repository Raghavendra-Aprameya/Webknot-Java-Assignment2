package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();
    private List<Directors> directors = new ArrayList<>();
    private static final String FILE_PATH = "/Users/aprameyar/Desktop/Webknot/Week3/Java/JavaAssignment2/src/main/resources/movies_large.csv";
    private static final String DELIMITER = ",";

    // Constructor - Load movies from CSV
    public MovieService() {
        loadMoviesFromCSV();
        loadDirectorFromCSV();
    }
    private void loadDirectorFromCSV()
    {
        directors.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/aprameyar/Desktop/Webknot/Week3/Java/JavaAssignment2/src/main/resources/directors_large.csv"))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(DELIMITER);

//                if (data.length < ) continue;

                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();

                String dob = data[2].trim();
                String nationality = data[3].trim();

                directors.add(new Directors(id, name, dob, nationality));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    // Load Movies from CSV
    private void loadMoviesFromCSV() {
        movies.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(DELIMITER);
                if (data.length < 8) continue;

                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                int year = Integer.parseInt(data[2].trim());
                String genre = data[3].trim();
                double rating = Double.parseDouble(data[4].trim());
                String duration = data[5].trim();
                int directorId = Integer.parseInt(data[6].trim());


                String actorIdsString = data[7].trim().replaceAll("\"", "");
                String[] actorIdStrings = actorIdsString.split(",");
                int[] actorIds = Arrays.stream(actorIdStrings)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                movies.add(new Movie(id, title, year, rating, genre, duration, directorId, actorIds));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


    // Get Movie by ID
    public void getMovieInformation(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                System.out.println(movie);
                return;
            }
        }
        System.out.println("Movie not found.");
    }

    // Get Movie by Title
    public void getMovieInformation(String title) {
        for (Movie movie : movies) {
//
            if (movie.getTitle().equalsIgnoreCase(title)) {
                System.out.print(movie);
                for(Directors director: directors)
                {

                    if(movie.getDirectorId()==director.getId())
                        System.out.println(director.getName());
                }

                return;
            }
        }
        System.out.println("Movie not found.");
    }
    public void getTop10RatedMovies()
    {
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie p1, Movie p2) {
                return Integer.compare((int) p2.getRating(), (int)p1.getRating());
            }
        });

        for(int i=0;i<10;i++)
        {
            System.out.println(movies.get(i));
        }

    }
    public void getMoviesByGenre(String genre)
    {
        for(Movie movie:movies)
        {
            if(movie.getGenre().equalsIgnoreCase(genre))
            {
                System.out.println(movie);
            }
        }
    }
    public void getMoviesByReleaseYear(int year)
    {
        for(Movie movie:movies)
        {
            if(movie.getYear()==year)
            {
                System.out.println(movie);
            }
        }
    }
    public void getMoviesByDirector(String directorName) {
        int directorId = -1;

        // Find the director ID and exit early
        for (Directors director : directors) {
            if (director.getName().equalsIgnoreCase(directorName)) {
                directorId = director.getId();
                break;  // Exit loop once found
            }
        }

        // If director is not found, return early
        if (directorId == -1) {
            System.out.println("Director not found.");
            return;
        }

        // Print movies directed by the found director
        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getDirectorId() == directorId) {
                System.out.println(movie);
                found = true;
            }
        }

        // If no movies found for the director
        if (!found) {
            System.out.println("No movies found for director: " + directorName);
        }
    }
    public void getMoviesByYearRange(int startYear, int endYear) {
        movies.stream()
                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
                .forEach(System.out::println);
    }

    public void addNewMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Movie added successfully: " + movie);
    }
    public void updateMovieRating(int movieId, double newRating) {
        for (Movie movie : movies) {
            if (movie.getId() == movieId) {
                movie.setRating(newRating);
                System.out.println("Updated rating: " + movie);
                return;
            }
        }
        System.out.println("Movie not found.");
    }
    public void deleteMovie(int movieId) {
        movies.removeIf(movie -> movie.getId() == movieId);
        System.out.println("Movie deleted successfully.");
    }
    public void getSortedMoviesByReleaseYear() {
        movies.stream()
                .sorted(Comparator.comparingInt(Movie::getYear))
                .limit(15)
                .forEach(System.out::println);
    }
    public void getTop5DirectorsWithMostMovies() {
        movies.stream()
                .collect(Collectors.groupingBy(Movie::getDirectorId, Collectors.counting()))
                .entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .forEach(entry -> System.out.println(directors.get(entry.getKey()).getName() + " - " + entry.getValue()));
    }

}
