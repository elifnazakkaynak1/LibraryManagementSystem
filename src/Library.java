import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Library {

    private final ArrayList<Material> materials;
    private static final String FILE_NAME = "library.txt";
    private static Scanner scanner = new Scanner(System.in);

    public Library(){
        this.materials =new ArrayList<>();
        loadFromFile();
    }

    public void addBook(){
        System.out.println("Book Addition Process is started...");
        System.out.println("Enter the id:");
        String id = scanner.nextLine();

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        System.out.println("Enter the author:");
        String author = scanner.nextLine();

        System.out.println("Enter the publication year:");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();

        Material newBook= new Book("BOOK",id,title,author,publicationYear);
        materials.add(newBook);
        System.out.println("Book is added: "+newBook.getTitle());
        saveToFile();
    }
    public void addMagazine(){
        System.out.println("Magazine Addition Process is started...");
        System.out.println("Enter the id:");
        String id = scanner.nextLine();

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        System.out.println("Enter the publisher:");
        String publisher = scanner.nextLine();

        System.out.println("Enter the publication year:");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();

        Material newMagazine= new Magazine("MAGAZINE",id,title,publisher,publicationYear){};
        materials.add(newMagazine);
        System.out.println("Magazine is added: "+newMagazine.getTitle());
        saveToFile();
    }
    public void addDVD(){
        System.out.println("DVD Addition Process is started...");
        System.out.println("Enter the id:");
        String id = scanner.nextLine();

        System.out.println("Enter the title:");
        String title = scanner.nextLine();

        System.out.println("Enter the director:");
        String director = scanner.nextLine();

        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        scanner.nextLine();

        Material newDVD= new DVD("DVD",id,title,director,year){};
        materials.add(newDVD);
        System.out.println("DVD is added: "+newDVD.getTitle());
        saveToFile();
    }

    public void listAllMaterials(){
        if(!materials.isEmpty()) {
            for (int i = 0; i < materials.size(); i++) {
                System.out.println((i+1) + "." + materials.get(i));
            }
        }
        else{
            System.out.println("The list is empty!!!");
        }
    }
    public void borrowMaterial(){
        System.out.println("Enter the id that belongs to the material you want to borrow: ");
        String id =scanner.nextLine();

        boolean materialFound=false;

        for(Material m : materials){
            if(Objects.equals(m.getId(), id)){
                materialFound=true;
                if(m instanceof Borrowable){
                    Borrowable b= (Borrowable) m;
                    if(b.isAvailableToBorrow()){
                        ((Borrowable) m).borrow();
                        System.out.println(m.getTitle()+" is borrowed!");
                        saveToFile();
                    }
                    else{
                        System.out.println("This material is not available to borrow because it is already reserved or borrowed!");
                    }
                }
                else{
                    System.out.println("This material cannot be borrowed because it is not borrowable material!");
                }
                return;
            }
        }
        if(!materialFound){
            System.out.println("Material cannot be found.");
        }

    }

    public void returnMaterial(){
        System.out.println("Enter the id that belongs to the material you want to return: ");
        String id =scanner.nextLine();
        for (Material m : materials){
            if(m.getId().equals(id)){
                if(m instanceof Borrowable){
                    Borrowable b= (Borrowable) m;
                    System.out.println(m.getTitle()+ " is returned!");
                    saveToFile();
                }
                else{
                    System.out.println("This material cannot be returned!");
                }
                return;
            }
        }
        System.out.println("Material cannot be found.");
    }
    public void reserveBook(){
        System.out.println("Enter the id that belongs to the material you want to reserve: ");
        String id =scanner.nextLine();
        System.out.println("Enter the name of the reserver: ");
        String reserverName =scanner.nextLine();
        for(Material m : materials){
            if(m.getId().equals(id)){
                if(m instanceof Reservable){
                    Reservable r= (Reservable) m;
                    r.reserve(reserverName);
                    saveToFile();
                }
                else{
                    System.out.println("This material cannot be reserved.");
                }
                return;
            }
        }

        System.out.println("Material cannot be found with this ID.");

    }
    public void cancelReservation(){
        System.out.println("Enter the id that belongs to the material you want to cancel reservation: ");
        String id =scanner.nextLine();
        for(Material m : materials){
            if(m.getId().equals(id)){
                if(m instanceof Reservable){
                    ((Reservable) m).cancelReservation();
                    saveToFile();
                }
                else{
                    System.out.println("This material's reservation cannot be cancelled!");
                }
                return;
            }
        }

        System.out.println("Material cannot be found with this ID.");

    }

    private void saveToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Material m: materials){
                bw.write(m.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("File Reader Error:" + e.getMessage());
        }
    }

    private void loadFromFile(){
        File file =new File(FILE_NAME);

        if(!file.exists()){
            return;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line=br.readLine())!=null){
                String[] arr = line.split(",");
                if(arr.length==11){
                    Book b =new Book(arr[0],arr[1],arr[2],arr[3],Integer.parseInt(arr[4]));
                    materials.add(b);
                }
                else if(arr.length==7){
                    DVD d= new DVD(arr[0],arr[1],arr[2],arr[3],Integer.parseInt(arr[4])) {};
                    materials.add(d);
                }
                else if(arr.length==8){
                    Magazine m= new Magazine(arr[0],arr[1],arr[2],arr[3],Integer.parseInt(arr[4])) {};
                    materials.add(m);
                }
            }
        }catch (IOException e) {
            System.out.println("File Reader Error: "+e.getMessage());
        }
    }

    public void listMaterialsByType() {
        System.out.println("Enter the type of the material you want to list:");
        String type=scanner.nextLine();
        type= type.toUpperCase(java.util.Locale.ENGLISH);

        switch (type){
            case "BOOK":
                for (Material item : materials) {
                    if ((item.getType()).equals("BOOK")) {
                        System.out.println(item);
                    }
                }
                break;

            case "MAGAZINE":
                for (Material item : materials) {
                    if ((item.getType()).equals("MAGAZINE")) {
                        System.out.println(item);
                    }
                }
                break;

            case "DVD":
                for (Material item : materials) {
                    if ((item.getType()).equals("DVD")) {
                        System.out.println(item);
                    }
                }
                break;
        }
    }
}
