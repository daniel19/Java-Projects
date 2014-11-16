public class LongestSong implements Command<CD> {
    private Song longSong = new Song("Default Song", "0:00");
    @Override
    public void execute(CD item) {
        Song localMax = item.getSong(0);
        for(int i=0; i < item.getNumberOfTracks(); i++){
            if(item.getSong(i).getLength() > localMax.getLength()){
                localMax = item.getSong(i);
            }
        }

        if(localMax.getLength() > longSong.getLength()){
            longSong = localMax;
        }
    }

    public Song getLongestSong(){return longSong;};
}
