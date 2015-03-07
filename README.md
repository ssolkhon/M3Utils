# M3Utils

M3Utils is a console based application for managing your music collection built in Java.

# User Stories

Create M3U Playlist From Collection – 
The user locates their music collection by browsing their file system. All mp3 music files  found in the selected directory, and its child folders are printed onto the screen. The user confirms that the wish to save the M3U playlist. The user browses their file system to choose a suitable location to save the modified M3U playlist and the playlist is saved.

Edit M3U Playlist – 
User browses their filesystem and locates an M3U playlist file. The user is presented with a list of tracks contained within the M3U Playlist on screen. The user can:

- Select to add a track to a playlist by again browsing the file system and selecting an MP3 file.
- Select to remove a track from the playlist identified by the index (i.e 1 for the first track in the playlist).
-Select to shuffle the playlist order. This will then shuffle the items in the playlist into a random order.

Once the user is happy with the playlist, they finish editing, and save the playlist to a suitable location on their filesystem. 

Convert M3U to Spotify – 
User selects an M3U playlist from their file system. The user is presented with a list of tracks which could be found on Spotify, and a list of tracks that could not be found on Spotify. The user then browses their file system to select a suitable location to save the Spotify playlist. The outputted file will be a .txt file containing Spotify links to the playlist tracks.

Suggested Artists From MP3 Track – 
User browses their file system to select an MP3 track. The MP3 track is the tested and the artist for the MP3 track is extracted from the file and displayed on the screen. The user is then presented with a list of 5 (or less) artists that are similar to the inputted track.

# How to run
MP3Utils is a console based application. To run this open up your terminal and change your working directory to the dist folder. You will then need to run the following command:

java -jar M3Utils.jar

Alternatively, open the M3Utils project in NetBeans, and ensure that all jars contained within the lib folder are reachable.

# How to play Spotify Playlist

Option 3 allows you to save a file containing Spotify links. To import this into Spotify you need to:

1) Open the Spotify application on your local machine

2) Create a new playlist 

3) Open the file containing your Spotify links

4) Highlight all links within this file and drag them into your new Spotify playlist.

