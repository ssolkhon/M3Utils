/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.google.code.jspot.Results;
import com.google.code.jspot.Spotify;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott Solkhon
 */
public class M3UTrack {

    private String trackFilename;
    private String trackMetaData;
    private String trackSpotify;
    private String trackTitle;
    private String trackArtist;
    public static final String trackArtistPlaceholder = "Could not find track Artist.";
    public static final String trackTitlePlaceholder = "Could not find track Title.";
    

    public static String getTrackArtistPlaceholder() {
        return trackArtistPlaceholder;
    }

    public List getSuggestedArtists() {
        List<String> suggestedArtists = new ArrayList();
        String apiKey = "OWPFELCP2N7VNDT2B";
        String artist = this.getTrackArtist();
        if (artist.equals(trackArtistPlaceholder)) {
            return suggestedArtists; // return empty list
        } else {
            try {
                EchoNestAPI echoNest = new EchoNestAPI(apiKey);
                List<com.echonest.api.v4.Artist> artists = echoNest.searchArtists(artist);
                if (artists.size() > 0) {
                    com.echonest.api.v4.Artist suggestions = artists.get(0);
                    for (com.echonest.api.v4.Artist simArtist : suggestions.getSimilar(5)) {
                        suggestedArtists.add(simArtist.getName());
                    }
                }
            } catch (EchoNestException ex) {
                //System.out.println("Could not find any suggested artists.");
                return suggestedArtists; // return empty list
            }
        }
        return suggestedArtists;
    }

    public void setTrackSpotify(String trackSpotify) {
        this.trackSpotify = trackSpotify;
    }

    public boolean generateTrackSpotify() {
        boolean isSet = false;
        try {
            String artistName = this.getTrackArtist();
            String trackName = this.getTrackTitle();

            try {
                Spotify spotify = new Spotify();
                Results<com.google.code.jspot.Track> tracks = spotify.searchTrack(artistName, trackName);

                if (tracks.getTotalResults() <= 0) {
                    //System.out.println("Sorry, could not find track.");
                    return isSet;
                }
                for (com.google.code.jspot.Track track : tracks.getItems()) {
                    setTrackSpotify(track.getId());

                    isSet = true;
                    return isSet; // we only want the first result
                }
            } catch (Exception e) {
                // do nothing for now
                // System.out.println("Failed");
            }
        } catch (Exception e) {
            // System.out.println("Could not get track data");
        }
        return isSet;
    }

    public String getTrackSpotify() {
        return trackSpotify;
    }
    private int trackDuration;

    public M3UTrack() {
    }

    public String getTrackFilename() {
        return trackFilename;
    }

    public M3UTrack setTrackFilename(String trackFilename) {
        this.trackFilename = trackFilename;
        return this;
    }

    public String getTrackMetaData() {
        return trackMetaData;
    }

    public M3UTrack setTrackMetaData(String trackMetaData) {
        this.trackMetaData = trackMetaData;
        return this;
    }

    public int getTrackDuration() {
        return trackDuration;
    }

    public M3UTrack setTrackDuration(int trackDuration) {
        this.trackDuration = trackDuration;
        return this;
    }

    public boolean isValidTrack() {
        boolean result = false;
        Mp3File mp3file;
        try {
            mp3file = new Mp3File(this.trackFilename);
            result = true;
        } catch (IOException ex) {
            // method will return false 
        } catch (UnsupportedTagException ex) {
            // method will return false 
        } catch (InvalidDataException ex) {
            // method will return false 
        }
        return result;
    }

    public String getTrackTitle() {
        Mp3File mp3file;
        trackTitle = trackTitlePlaceholder;
        try {
            mp3file = new Mp3File(this.trackFilename);
            if (mp3file.hasId3v1Tag() == true) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                trackTitle = id3v1Tag.getTitle();
            } else if (mp3file.hasId3v2Tag() == true) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                trackTitle = id3v2Tag.getTitle();
            }
        } catch (IOException ex) {
            return trackTitle;
        } catch (UnsupportedTagException ex) {
            return trackTitle;
        } catch (InvalidDataException ex) {
            return trackTitle;
        }
        return trackTitle;
    }

    public String getTrackArtist() {
        Mp3File mp3file;
        trackArtist = trackArtistPlaceholder;
        try {
            mp3file = new Mp3File(this.trackFilename);
            if (mp3file.hasId3v1Tag() == true) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                trackArtist = id3v1Tag.getArtist();
            } else if (mp3file.hasId3v2Tag() == true) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                trackArtist = id3v2Tag.getArtist();
            }
        } catch (IOException ex) {
            //Logger.getLogger(M3UTrack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            //Logger.getLogger(M3UTrack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            //Logger.getLogger(M3UTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trackArtist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final M3UTrack other = (M3UTrack) obj;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.trackFilename);
        hash = 47 * hash + Objects.hashCode(this.trackMetaData);
        hash = 47 * hash + this.trackDuration;
        return hash;
    }
}
