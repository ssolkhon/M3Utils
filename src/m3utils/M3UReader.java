/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ku14009
 */
public class M3UReader {

    public static final String M3U_HEADER = "#EXTM3U";
    public static final String M3U_METADATA = "#EXTINF";
    public static final String MP3_EXTENSION = "mp3";

    public static boolean isValidHeader(String filename) {
        boolean returnValue = true;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String firstLine = br.readLine();
            //Check for the correct header - If there is no header reassign returnValue
            if (firstLine.startsWith(M3U_HEADER)) {
                //System.out.println("isValidHeader:: " + filename + " starts correctly.");
            } else {
                returnValue = false;
            }
            br.close();
        } catch (Exception e) {
            //If an exception is caught reassign returnValue
            //System.err.println("isValidHeader:: error with file " + filename + ": " + e.getMessage());
            returnValue = false;
        }

        return returnValue;

    }

    public static int getNumberOfTracks(String filename) {
        int trackCounter = 0;

        if (isValidHeader(filename)) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(new File(filename)));
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    //Check that we can find .mp3 and that the line is not metadata
                    //How could we handle multiple file extensions?
                    if (!nextLine.startsWith(M3U_METADATA) && nextLine.endsWith(MP3_EXTENSION)) {
                        trackCounter++;
                    }
                }
            } catch (Exception e) {
                //Exception caught - set trackCounter to 0
                System.err.println("getNumberOfTracks:: error with file "
                        + filename + ": " + e.getMessage());
                trackCounter = 0;
            }
        }

        return trackCounter;
    }

    public static int getTotalSeconds(String filename) {
        int totalSeconds = 0;

        if (isValidHeader(filename)) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(new File(filename)));
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    //If the next line is metadata it should be possible to extract the length of the song
                    if (nextLine.startsWith(M3U_METADATA)) {
                        int i1 = nextLine.indexOf(":");
                        int i2 = nextLine.indexOf(",");
                        String substr = nextLine.substring(i1 + 1, i2);
                        totalSeconds += Integer.parseInt(substr);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                //Exception caught - set totalSeconds to 0
                System.err.println("getTotalSeconds:: error with file "
                        + filename + ": " + e.getMessage());
                totalSeconds = 0;
            }
        }

        return totalSeconds;
    }

    public static boolean copy(String inputPlayList, String outputPlayList, int numberOfSeconds) {
        boolean isWritten = false;
        int minutesWritten = 0;
        int minutesToWrite = 0;

        if (isValidHeader(inputPlayList)) {
            BufferedReader br;
            BufferedWriter bw;
            try {
                br = new BufferedReader(new FileReader(new File(inputPlayList)));
                bw = new BufferedWriter(new FileWriter(new File(outputPlayList)));
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    if (nextLine.startsWith(M3U_METADATA)) {
                        int start = nextLine.indexOf(":");
                        int end = nextLine.indexOf(",");
                        String subString = nextLine.substring(start + 1, end);
                        //Assign minutesToWrite the length of the current track
                        minutesToWrite = Integer.parseInt(subString);
                    }
                    /*
                     Check to see that the minutes written so far plus the minutes
                     that will be written this time does not exceed the specified
                     length of the playlist
                     */
                    if (minutesWritten + minutesToWrite <= numberOfSeconds) {
                        bw.write(nextLine);
                        bw.newLine();
                        minutesWritten += minutesToWrite;
                        minutesToWrite = 0;
                    }
                }
                //Assuming the loop ran - something should have been written to the output file
                //even if it's only the file header
                isWritten = true;
                br.close();
                bw.close();
            } catch (IOException | NumberFormatException e) {
                System.err.println("getTotalSeconds:: error with file "
                        + inputPlayList + ": " + e.getMessage());

            }
        }
        return isWritten;
    }

    public static M3UPlaylist getPlaylist(File plFilename) {
        M3UPlaylist playlist = new M3UPlaylist();

        if (plFilename.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(plFilename))) {
                if (M3UReader.isValidHeader(plFilename.toString())) {
                    String nextLine;
                    String trackFilename = "";
                    String trackMetaData = "";
                    while ((nextLine = br.readLine()) != null) {
                        if (nextLine.startsWith(M3U_METADATA)) {
                            trackMetaData = nextLine;
                        } else if (!nextLine.equals(M3U_HEADER) && !nextLine.isEmpty()) {
                            trackFilename = nextLine;
                            playlist.addTrack(getTrack(trackFilename, trackMetaData));
                        }
                    }
                } else {
                    playlist = null;
                }
            } catch (Exception e) {
                System.err.println("getPlaylist:: error with file "
                        + plFilename + ": " + e.getMessage());
                playlist = null;
            }

        } else {
            playlist = null;
        }

        return playlist;
    }

    public static M3UTrack getTrack(String trackFilename, String trackMetaData) {
        //Set some default values just in case
        int seconds = 0;
        String metaData = "Not Set";

        int start = trackMetaData.indexOf(":");
        int end = trackMetaData.indexOf(",");
        if (start != -1 && end != -1) {
            seconds = Integer.parseInt(trackMetaData.substring(start + 1, end));
            metaData = trackMetaData.substring(end + 1);
        }

        return new M3UTrack().setTrackFilename(trackFilename).setTrackMetaData(metaData).setTrackDuration(seconds);
    }
}
