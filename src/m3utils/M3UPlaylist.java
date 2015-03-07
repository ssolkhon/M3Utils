/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jamie
 */
public class M3UPlaylist {

    public List<M3UTrack> tracks;

    public M3UPlaylist() {
        this.tracks = new ArrayList<>();
    }

    public int savePlaylist(Path p, String filename) {
        int result = 0;

        try {
            File file = new File(p.toString() + File.separator + filename);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(M3UReader.M3U_HEADER + "\n");
            try {
                resetMetaData();
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(M3UPlaylist.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(M3UPlaylist.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (M3UTrack t : tracks) {
                bw.write(t.getTrackMetaData() + "\n");
                bw.write(t.getTrackFilename() + "\n\n");
            }

            bw.close();
            result = 1;
        } catch (IOException e) {
            //System.out.println("Sorry, I could not save a file in this location.");
            //e.printStackTrace();
        }
        return result;
    }

    

    public void remove(int thisIndex) {
        //Remove the track at thisIndex in the list.
        //System.out.println("Removing: " + tracks.get(thisIndex).getTrackTitle());
        this.tracks.remove(thisIndex);
    }

    private void removeTheseTracks(List<M3UTrack> tracksToRemove) {
        for (M3UTrack t : tracksToRemove) {
            //System.out.println("Removing Track: " + t.getTrackFilename());
            //System.out.println("Removing Track: " + t.getTrackMetaData());
            this.tracks.remove(t);
        }
    }

    public void removeAllFrom(int thisIndex) {
        //Remove all tracks in the list from thisIndex onwards
        List<M3UTrack> tracksToRemove = new ArrayList();

        int counter = tracks.size() - 1;
        for (int i = 0; i <= counter; i++) {

            if (i >= thisIndex) {
                tracksToRemove.add(tracks.get(i));
            }
        }
        removeTheseTracks(tracksToRemove);
    }

    public void removeAllUpTo(int thisIndex) {
        //Remove all tracks in the list from the start, up to thisIndex
        List<M3UTrack> tracksToRemove = new ArrayList();
        for (int i = 0; i <= tracks.size(); i++) {
            if (i <= thisIndex) {
                tracksToRemove.add(tracks.get(i));
            }
        }
        removeTheseTracks(tracksToRemove);
    }

    public void remove(String thisString) {
        //Remove all tracks containing the string ‘thisString’ in the metadata.
        List<M3UTrack> tracksToRemove = new ArrayList();
        for (M3UTrack t : this.tracks) {
            String metaData = t.getTrackMetaData();
            if (metaData.contains(thisString)) {
                tracksToRemove.add(t);
            }
        }
        removeTheseTracks(tracksToRemove);
    }

    private String createMetaData(int trackDuration, String trackArtist, String trackName) {
        //System.out.println( M3UReader.M3U_METADATA + ":" + trackDuration + "," + trackArtist + " - " + trackName);
        return M3UReader.M3U_METADATA + ":" + trackDuration + "," + trackArtist + " - " + trackName;
    }

    public void resetMetaData() throws IOException, UnsupportedTagException, InvalidDataException {
        for (M3UTrack t : this.tracks) {

            Mp3File mp3file;
            try {
                mp3file = new Mp3File(t.getTrackFilename());
                int trackDuration = 0;
                String trackName = null;
                String trackArtist = null;

                if (mp3file.hasId3v1Tag() == true) {
                    ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                    long trackLong = mp3file.getLengthInSeconds();
                    trackDuration = (int) trackLong;
                    trackName = id3v1Tag.getTitle();
                    trackArtist = id3v1Tag.getArtist();

                } else if (mp3file.hasId3v2Tag() == true) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    long trackLong = mp3file.getLengthInSeconds();
                    trackDuration = (int) trackLong;
                    trackName = id3v2Tag.getTitle();
                    trackArtist = id3v2Tag.getArtist();
                } 
                t.setTrackMetaData(createMetaData(trackDuration, trackArtist, trackName));
            } catch (IOException ex) {
                //Logger.getLogger(M3UPlaylist.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Could not reset MetaData for track: " + t.getTrackFilename());
            } catch (UnsupportedTagException ex) {
                //Logger.getLogger(M3UPlaylist.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                //Logger.getLogger(M3UPlaylist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addTrack(M3UTrack thisTrack) {
        this.tracks.add(thisTrack);
    }

    void addAllTracks(M3UPlaylist subList) {

        this.tracks.addAll(subList.tracks);

    }

    public int getSizeOfPlaylist() {
        return tracks.size();
    }
    
    public void shuffle() {
        Collections.shuffle(tracks);
    }
    
    public int saveSpotifyPlaylist(Path p, String filename) {
        int result = 0;
        
        
        try {
            File file = new File(p.toString() + File.separator + filename);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (M3UTrack t : tracks) {
                bw.write(t.getTrackSpotify() + "\n");
            }

            bw.close();
            result = 1;
        } catch (IOException e) {
            //System.out.println("Sorry, I could not save a file in this location.");
            //e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final M3UPlaylist other = (M3UPlaylist) obj;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tracks);
        return hash;
    }
}
