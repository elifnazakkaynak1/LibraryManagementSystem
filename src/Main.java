import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Library library;
    private static Scanner scanner;

    public Main(){
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.startLoop();
    }

    public void startLoop(){

        int selection=-1;

        while(selection != 0){
            DisplayMenu();

            try{
                selection= scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                        library.addBook();
                        break;
                    case 2:
                        library.addMagazine();
                        break;
                    case 3:
                        library.addDVD();
                        break;
                    case 4:
                        library.listAllMaterials();
                        break;
                    case 5:
                        library.listMaterialsByType();
                        break;
                    case 6:
                        library.borrowMaterial();
                        break;
                    case 7:
                        library.returnMaterial();
                        break;
                    case 8:
                        library.reserveBook();
                        break;
                    case 9:
                        library.cancelReservation();
                        break;
                    case 0:
                        System.out.println("You are existing the program. See you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0-5.");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("ERROR: Please just enter a number between 0-8.");
                scanner.nextLine();
                selection=-1;
            }
        }
        scanner.close();
    }

    public static void DisplayMenu(){
        System.out.println("---Main menu---");
        System.out.println("1.Add Book");
        System.out.println("2.Add Magazine");
        System.out.println("3.Add DVD");
        System.out.println("4.List All Materials");
        System.out.println("5.List by Type");
        System.out.println("6.Borrow Material");
        System.out.println("7.Return Material");
        System.out.println("8.Reserve Book");
        System.out.println("9.Cancel Reservation");
        System.out.println("0.Exit");
        System.out.println("Choice: ");
    }
}
