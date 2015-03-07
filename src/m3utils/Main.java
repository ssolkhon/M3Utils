/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import static com.echonest.api.v4.examples.SimlarArtists.API_KEY;
import com.google.code.jspot.Artist;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scott
 */
public class Main {

    public static void main(String args[]) {
        System.out.println("\nHello! Welcome to M3Utils.");
        String input = printMainMenu();

        do {
            if (input.equals("1")) {
                goToOption1();
                waitForUser();
                input = printMainMenu();

            } else if (input.equals("2")) {
                goToOption2();
                waitForUser();
                input = printMainMenu();
            } else if (input.equals("3")) {
                goToOption3();
                waitForUser();
                input = printMainMenu();
            } else if (input.equals("4")) {
                goToOption4();
                waitForUser();
                input = printMainMenu();
            } else {
                System.out.println("Sorry I do not recogise your input: " + input + "\nPlease Try Again.");
                input = printMainMenu();
            }
        } while (!input.equals("exit"));

        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    private static String printMainMenu() {
        System.out.println("\n############ Main Menu ############\n");

        System.out.println("Please select a task from the list below, or type exit to close this program.\n");

        System.out.println("1) Create M3U Playlist From Collection");
        System.out.println("2) Edit M3U Playlist");
        System.out.println("3) Convert M3U to Spotify");
        System.out.println("4) Find Suggested Artists From Track");

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter your choice: ");
        String input = sc.next();
        System.out.println("\n###################################\n");
        return input;
    }

    private static void waitForUser() {
        System.out.print("\nPress enter to return to the main menu.");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void goToOption1() {
        System.out.println("You Selected: Create M3U Playlist From Collection\n");

        Scanner sc = new Scanner(System.in);
        String save = "";
        M3UPlaylist myCollection = new M3UPlaylist();
        int sizeOfPlaylist = 0;
        Path p = null;

        while (sizeOfPlaylist <= 0) {
            System.out.println("Please browse your file system and locate your music collection...");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            p = Paths.get(input);
            myCollection = M3UFinder.makePlayList(p);
            sizeOfPlaylist = myCollection.getSizeOfPlaylist();

            if (sizeOfPlaylist <= 0) {
                System.out.println("\nI could not find any valid tracks in this path. Please try another.\n");
            }
            if (sizeOfPlaylist >= 1) {
                break;
            }
        }

        System.out.println("\nLoading Tracks...");

        try {
            System.out.println("Creating MetaData...");
            myCollection.resetMetaData();
            System.out.println("MetaData Successfuly Created");
        } catch (IOException ex) {
            System.out.println("MetaData could not be re-generated (M3U Playlist will still be created)");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            System.out.println("MetaData could not be re-generated (M3U Playlist will still be created)");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            System.out.println("MetaData could not be re-generated (M3U Playlist will still be created)");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n############# Tracks ##############\n");
        for (M3UTrack t : myCollection.tracks) {
            System.out.println("\nFilename: " + t.getTrackFilename());
            System.out.println("Artist/Track: " + t.getTrackArtist() + " - " + t.getTrackTitle());
        }
        System.out.println("\n###################################\n");

        do {
            System.out.print("Would you like to save this playlist? (type: 'yes' or 'no'): ");
            save = sc.next();
            if (save.equals("no")) {
                System.out.println("\nOkay - not saving playlist.");
                break;
            }
        } while (!save.equals("yes"));

        if (save.equals("yes")) {

            int saved = 0;

            while (saved != 1) {
                String filename = "myCollection.m3u";

                System.out.println("Please enter the path for where you would like to save your new playlist? "
                        + "(filename will be " + filename + ") or type 'cancel' to return to the main menu.");
                Scanner saveLocationInput = new Scanner(System.in);
                String saveLocationStr = saveLocationInput.nextLine();

                if (saveLocationStr.equals("cancel")) {
                    break;
                }

                Path path = Paths.get(saveLocationStr);

                saved = myCollection.savePlaylist(path, filename);

                if (saved == 0) {
                    System.out.println("Sorry, I could not save to the path specified.");

                } else if (saved == 1) {
                    System.out.println(p + "/" + filename + " saved successfully.");
                }
            }
        }
    }

    private static void goToOption2() {
        System.out.println("You Selected: Edit M3U Playlist\n");

        Scanner sc = new Scanner(System.in);
        boolean fileIsValid;
        boolean userIsReady = false;
        M3UPlaylist myPlaylist = null;

        while (!userIsReady) {
            System.out.println("Please enter the location of your M3U file or 'cancel' to go back to the main menu.");
            String file = sc.nextLine();

            fileIsValid = M3UReader.isValidHeader(file);

            if (fileIsValid) {
                File f = new File(file);
                System.out.println("\nLoading Tracks...");
                myPlaylist = M3UReader.getPlaylist(f);
                userIsReady = true;
            } else if (file.equals("cancel")) {
                break;
            } else {
                System.out.println("\nSorry '" + file + "' is not a vaild M3U file or does not exist.");
            }
        }

        if (userIsReady) {
            String input;

            // Print Tracks
            printPlaylisttArtistAndTitles(myPlaylist);
            printOption2Menu();

            do {

                System.out.print("Enter your choice: ");
                input = sc.next();

                if (input.equals("1")) { // Add track to playlist
                    System.out.println("Please enter the full path to the '.mp3' file that you wish to add or 'cancel' to return to the previous menu:");
                    String mp3fileStr;
                    Scanner mp3Input = new Scanner(System.in);
                    mp3fileStr = mp3Input.nextLine();

                    M3UTrack track = new M3UTrack();
                    track.setTrackFilename(mp3fileStr);

                    if (track.isValidTrack()) {
                        myPlaylist.addTrack(track);
                        System.out.println("\nTrack: " + track.getTrackFilename() + " added successfully.");

                        try {
                            System.out.println("Creating MetaData...");
                            myPlaylist.resetMetaData();
                            System.out.println("MetaData Successfuly Created");
                        } catch (IOException ex) {
                            System.out.println("MetaData could not be re-generated.");
                            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedTagException ex) {
                            System.out.println("MetaData could not be re-generated.");
                            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidDataException ex) {
                            System.out.println("MetaData could not be re-generated.");
                            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (input.equals("cancel")) {
                        // print nothing
                    } else {
                        System.out.println("Sorry ' " + mp3fileStr + "' is not a valid mp3 file.");
                    }

                    printPlaylisttArtistAndTitles(myPlaylist);
                    printOption2Menu();
                } else if (input.equals("2")) { // Remove track from playlist

                    System.out.println("Please specify the track you would like to remove identified by the list number or 'cancel' to return to the previous menu:");
                    Scanner trackToRemoveInput = new Scanner(System.in);
                    int trackToRemove;

                    try {
                        trackToRemove = trackToRemoveInput.nextInt();

                        if (trackToRemove <= myPlaylist.getSizeOfPlaylist()) {
                            System.out.println("\nRemoving Track: " + trackToRemove);
                            trackToRemove--;
                            myPlaylist.remove(trackToRemove);
                        } else {
                            System.out.println("Track index: " + trackToRemove + " does not exist.");
                        }
                    } catch (Exception e) {
                        System.out.println("\nInput not valid.");
                    }
                    printPlaylisttArtistAndTitles(myPlaylist);
                    printOption2Menu();
                } else if (input.equals("3")) { // Shuffle playlist order
                    System.out.println("Shuffling Playlist...");
                    myPlaylist.shuffle();
                    printPlaylisttArtistAndTitles(myPlaylist);
                    printOption2Menu();
                }

            } while (!input.equals("4"));

            if (input.equals("4")) {

                while (!input.equals("yes")) {
                    System.out.println("Would you like to save this playlist? ('Yes' or 'No')");
                    input = sc.next();

                    if (input.equals("no")) {
                        break;
                    }
                }

                if (input.equals("yes")) {

                    int saved = 0;

                    while (saved != 1) {
                        System.out.println("Please enter the path where you would like to save your playlist, or 'cancel' to return to the main menu. (filename will be 'myPlaylist.m3u')");
                        Scanner saveLocationInput = new Scanner(System.in);
                        String saveLocationStr = saveLocationInput.nextLine();

                        if (saveLocationStr.equals("cancel")) {
                            break;
                        }

                        Path p = Paths.get(saveLocationStr);
                        String filename = "myPlaylist.m3u";
                        saved = myPlaylist.savePlaylist(p, filename);

                        if (saved == 0) {
                            System.out.println("Sorry, I could not save to the path specified.");

                        } else if (saved == 1) {
                            System.out.println(p + filename + "/" + " saved successfully.");
                        }
                    }
                }
            }
        }
    }
    
        private static void goToOption3() {
        System.out.println("You Selected: Convert M3U to Spotify\n");

        Scanner sc = new Scanner(System.in);
        boolean fileIsValid;
        boolean userIsReady = false;
        M3UPlaylist myPlaylist = null;

        while (!userIsReady) {
            System.out.println("Please enter the location of your M3U file or 'cancel' to go back to the main menu.");
            String file = sc.nextLine();

            fileIsValid = M3UReader.isValidHeader(file);

            if (fileIsValid) {
                File f = new File(file);
                System.out.println("\nLooking Up Tracks...");
                myPlaylist = M3UReader.getPlaylist(f);
                userIsReady = true;
            } else if (file.equals("cancel")) {
                break;
            } else {
                System.out.println("\nSorry '" + file + "' is not a vaild M3U file or does not exist.");
            }
        }

        if (userIsReady) {

            String webService = "ws.spotify.com";
            boolean canConnect = testInternetConnection(webService, 80);

            if (canConnect) {
                List<M3UTrack> notFound = new ArrayList<>();

                // tracks found
                System.out.println("\n########## Tracks Found ###########\n");
                int counter = 1;

                for (M3UTrack t : myPlaylist.tracks) {
                    boolean generateSpotify = t.generateTrackSpotify();

                    if (generateSpotify) {
                        System.out.println(counter + ") " + t.getTrackArtist() + " - " + t.getTrackTitle());
                        counter++;
                    } else {
                        notFound.add(t);
                    }
                }
                System.out.println("\n###################################\n");

                if (notFound.size() > 1) {
                    counter = 1;
                    System.out.println("\n######### Tracks Not Found ########\n");
                    for (M3UTrack t : notFound) {
                        System.out.println(counter + ") " + t.getTrackFilename());
                        counter++;
                    }
                    System.out.println("\n###################################\n");
                }
            } else {
                System.out.println("\nFailed to make internet connection.\n");
            }

            String input = "";
            while (!input.equals("yes")) {
                System.out.println("Would you like to save this playlist? ('Yes' or 'No')");
                input = sc.next();

                if (input.equals("no")) {
                    break;
                }
            }

            if (input.equals("yes")) {

                int saved = 0;

                while (saved != 1) {
                    System.out.println("Please enter the path where you would like to save your playlist, or 'cancel' to return to the main menu. (filename will be 'myPlaylit.txt')");
                    Scanner saveLocationInput = new Scanner(System.in);
                    String saveLocationStr = saveLocationInput.nextLine();

                    if (saveLocationStr.equals("cancel")) {
                        break;
                    }

                    Path p = Paths.get(saveLocationStr);
                    String filename = "myPlaylist.txt";
                    saved = myPlaylist.saveSpotifyPlaylist(p, filename);

                    if (saved == 0) {
                        System.out.println("Sorry, I could not save to the path specified.");

                    } else if (saved == 1) {
                        System.out.println(p + filename + " saved successfully.");
                    }
                }
            }
        }
    }
        
        private static void goToOption4() {
        System.out.println("You Selected: Find Suggested Artists From Track\n");

        Scanner sc = new Scanner(System.in);
        boolean fileIsValid;
        boolean userIsReady = false;
        boolean containsArtist = false;
        M3UTrack track = new M3UTrack();

        while (!userIsReady) {
            System.out.println("Please enter the location of your MP3 file or 'cancel' to go back to the main menu.");
            String file = sc.nextLine();

            track.setTrackFilename(file);
            fileIsValid = track.isValidTrack();

            if (fileIsValid) {
                String artist = track.getTrackArtist();
                if (artist.equals(M3UTrack.getTrackArtistPlaceholder())) {
                    System.out.println("Sorry - Could not find track Artist. Please correct the tags and try again.");
                } else {
                    System.out.println("\nArtist = " + artist + "\n");
                    containsArtist = true;
                }
                userIsReady = true;
            } else if (file.equals("cancel")) {
                break;
            } else {
                System.out.println("\nSorry '" + file + "' is not a vaild MP3 file or does not exist.");
            }
        }

        if (containsArtist) {
            String webService = "developer.echonest.com";
            boolean canConnect = testInternetConnection(webService, 80);

            if (canConnect) {
                System.out.println("\nGetting Suggested Artists...\n");

                List<String> suggestedArtists = track.getSuggestedArtists();

                if (suggestedArtists.size() < 1) {
                    System.out.println("Sorry, could not find any suggested artists.");
                } else {
                    System.out.println("\n######## Suggested Artists ########\n");
                    int counter = 1;
                    for (String s : suggestedArtists) {
                        System.out.println(counter + ") " + s);
                        counter++;
                    }
                    System.out.println("\n###################################\n");
                }
            } else {
                System.out.println("Could not make internet conection.");
            }
        }
    }
    
    private static void printPlaylisttArtistAndTitles(M3UPlaylist myPlaylist) {
        System.out.println("\n############# Tracks ##############\n");
        int counter = 1;
        for (M3UTrack t : myPlaylist.tracks) {
            System.out.println(counter + ") " + t.getTrackArtist() + " - " + t.getTrackTitle());
            counter++;
        }
        System.out.println("\n###################################\n");

    }

    private static void printOption2Menu() {
        System.out.println("\nPlease choose from the available options:");
        System.out.println("1) Add track to playlist");
        System.out.println("2) Remove track from playlist");
        System.out.println("3) Shuffle playlist order");
        System.out.println("4) Finish editing playlist\n");
    }



    private static boolean testInternetConnection(String webService, int port) {
        // I found this and modified it slightly:
        // http://stackoverflow.com/questions/3584210/preferred-java-way-to-ping-a-http-url-for-availability

        Socket socket = null;
        boolean reachable = false;
        try {
            try {
                socket = new Socket(webService, port);
                reachable = true;
            } catch (IOException ex) {
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                //reachable = false; // failed to make internet connection
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        return reachable;
    }

    
}
