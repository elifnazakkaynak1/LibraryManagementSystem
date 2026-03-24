public class DVD extends Material{

    private int durationMinutes;
    private String genre;

    public DVD(String type,String id,String title,String creator,int publicationYear){
        super(type,id,title,creator,publicationYear);
        this.durationMinutes=durationMinutes;
        this.genre=genre;
    }

    public int getDurationMinutes(){
        return durationMinutes;
    }

    public void setDurationMinutes(int minutes){
        if(minutes>0){
            durationMinutes=minutes;
        }
        else{
            System.out.println("The duration cannot be smaller than 0.");
        }
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }

    public void watch(){
        System.out.println("Now playing: "+getTitle()+" ("+getAuthorOrPublisherOrCreator()+") - Duration: "+getDurationMinutes()+" minutes");
    }

    public String toFileFormat(){
        return String.join(",",getType(), getId(),getTitle(),getAuthorOrPublisherOrCreator(),String.valueOf(getPublicationYear()),String.valueOf(getDurationMinutes()),getGenre());
    }

    public String toString() {
        return ("Id:" + getId() + " |Title:" + getTitle() + " |Director:" + getAuthorOrPublisherOrCreator() + " |Year:" + getPublicationYear() + " |Genre: " +getGenre() + " |Duration: " + getDurationMinutes());
    }
}