public abstract class Material {
    private String type;
    private String id;
    private String title;
    private String authorOrPublisherOrCreator;
    private int publicationYear;

    public Material(String type,String id,String title,String AuthorOrPublisherOrCreator,int publicationYear){
        this.type=type;
        this.id=id;
        this.title=title;
        this.authorOrPublisherOrCreator=AuthorOrPublisherOrCreator;
        this.publicationYear=publicationYear;
    }
    String getType(){
        return type;
    }
    String getId(){
        return id;
    }
    String getTitle(){
        return title;
    }
    String getAuthorOrPublisherOrCreator(){
        return authorOrPublisherOrCreator;
    }
    int getPublicationYear(){
        return publicationYear;
    }

    String toFileFormat() {
        return null;
    }
}
