package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieService movieObj = new MovieService();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Get Movie Information");
            System.out.println("2. Get Top 10 Rated Movies");
            System.out.println("3. Get Movies by Genre");
            System.out.println("4. Get Movies by Release Year");
            System.out.println("5. Get Movies by Director");
            System.out.println("6. Get Movies by Year Range");
            System.out.println("7. Add New Movie");
            System.out.println("8. Update Movie Rating");
            System.out.println("9. Delete Movie");
            System.out.println("10. Get Sorted Movies by Release Year");
            System.out.println("11. Get Top 5 Directors with Most Movies");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int usersChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (usersChoice) {
                case 1:
                    System.out.println("Enter 1 to get movie by ID, 2 to get movie by Title:");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 1) {
                        System.out.print("Enter Movie ID: ");
                        int id = sc.nextInt();
                        movieObj.getMovieInformation(id);
                    } else {
                        System.out.print("Enter Movie Title: ");
                        String title = sc.nextLine();
                        movieObj.getMovieInformation(title);
                    }
                    break;
                case 2:
                    movieObj.getTop10RatedMovies();
                    break;
                case 3:
                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();
                    movieObj.getMoviesByGenre(genre);
                    break;
                case 4:
                    System.out.print("Enter Release Year: ");
                    int year = sc.nextInt();
                    movieObj.getMoviesByReleaseYear(year);
                    break;
                case 5:
                    System.out.print("Enter Director's Name: ");
                    String directorName = sc.nextLine();
                    movieObj.getMoviesByDirector(directorName);
                    break;
                case 6:
                    System.out.print("Enter Start Year: ");
                    int startYear = sc.nextInt();
                    System.out.print("Enter End Year: ");
                    int endYear = sc.nextInt();
                    movieObj.getMoviesByYearRange(startYear, endYear);
                    break;
                case 7:
                    System.out.print("Enter Movie ID: ");
                    int movieId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String movieTitle = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int movieYear = sc.nextInt();
                    System.out.print("Enter Genre: ");
                    String movieGenre = sc.next();
                    System.out.print("Enter Rating: ");
                    double rating = sc.nextDouble();
                    System.out.print("Enter Duration: ");
                    String duration = sc.next();
                    System.out.print("Enter Director ID: ");
                    int directorId = sc.nextInt();
                    System.out.print("Enter Actor IDs (comma separated): ");
                    sc.nextLine();
                    String actorIdsString = sc.nextLine();
                    int[] actorIds = java.util.Arrays.stream(actorIdsString.split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    movieObj.addNewMovie(new Movie(movieId, movieTitle, movieYear, rating, movieGenre, duration, directorId, actorIds));
                    break;
                case 8:
                    System.out.print("Enter Movie ID: ");
                    int updateMovieId = sc.nextInt();
                    System.out.print("Enter New Rating: ");
                    double newRating = sc.nextDouble();
                    movieObj.updateMovieRating(updateMovieId, newRating);
                    break;
                case 9:
                    System.out.print("Enter Movie ID to Delete: ");
                    int deleteMovieId = sc.nextInt();
                    movieObj.deleteMovie(deleteMovieId);
                    break;
                case 10:
                    movieObj.getSortedMoviesByReleaseYear();
                    break;
                case 11:
                    movieObj.getTop5DirectorsWithMostMovies();
                    break;
                case 12:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
