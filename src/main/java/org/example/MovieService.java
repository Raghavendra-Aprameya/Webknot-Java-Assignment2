package org.example;

import java.io.*;
import java.util.*;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();
    private static final String FILE_PATH = "/Users/aprameyar/Desktop/Webknot/Week3/Java/JavaAssignment2/src/main/resources/movies_large.csv";
    private static final String DELIMITER = ",";

    // Constructor - Load movies from CSV
    public MovieService() {
        loadMoviesFromCSV();
        loadDirectorFromCSV();
    }
    private void loadDirectorFromCSV()
    {

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
            if (movie.getTitle().equalsIgnoreCase(title)) {
                System.out.println(movie);
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
}
