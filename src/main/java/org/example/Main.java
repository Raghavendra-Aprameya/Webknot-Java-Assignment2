package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieService movieObj = new MovieService();

        System.out.println("Enter 1. To get Movie Information\n2.To Get top 10 rated movies\n3.To get movie by genre\n4.To get movies based on  release year\n5.Get movie by director name");
        int usersChoice = sc.nextInt();

        if (usersChoice == 1) {
            System.out.println("Enter 1. to get movie by ID\n2. to get movie by Title");
            int userChoiceOfId = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (userChoiceOfId == 1) {
                System.out.println("Enter the Movie ID:");
                int id = sc.nextInt();
                movieObj.getMovieInformation(id);
            } else {
                System.out.println("Enter the Movie Title:");
                String title = sc.nextLine();
                movieObj.getMovieInformation(title);
            }
        }
        else if(usersChoice==2)
        {

            movieObj.getTop10RatedMovies();
        }
        else if(usersChoice==3)
        {
            System.out.println("Enter Genre");
            String genre=sc.next();
                movieObj.getMoviesByGenre(genre);
        }
        else if(usersChoice==4)
        {
            System.out.println("Enter release year");
            int year=sc.nextInt();
            movieObj.getMoviesByReleaseYear(year);
        }
        else if(usersChoice==5)
        {
            System.out.println("Enter director's name");
            String directorName=sc.next();
            movieObj.getMoviesByDirector(directorName);
        }
    }
}
