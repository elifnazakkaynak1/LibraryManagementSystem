import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Book extends Material implements Borrowable,Reservable {

    private boolean isBorrowed;
    private LocalDate borrowDate;
    private int loanPeriodDays;
    private boolean reserved;
    private String reservationHolder;
    private LocalDate reservationDate;

    public Book(String type,String isbn,String title,String author,int publicationYear){
        super(type,isbn,title,author,publicationYear);
        this.isBorrowed=false;
        this.borrowDate=null;
        this.loanPeriodDays=14;
        this.reserved=false;
        this.reservationHolder=null;
        this.reservationDate=null;
    }

    public boolean isBorrowed(){
        return isBorrowed;
    }
    public void setBorrowed(boolean borrowed){
        if(!borrowed){
            this.borrowDate=null;
        }
        else{
            this.isBorrowed= true;
        }
    }
    public LocalDate getBorrowDate(){
        return borrowDate;
    }
    public void setBorrowDate(LocalDate date){
        if(date != null && date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Error!!!");
        }
        else {
            this.borrowDate=date;
        }
    }
    public int getLoanPeriodDays(){
        return loanPeriodDays;
    }
    public void setLoanPeriodDays(int days){
        if (days <=0){
            throw new IllegalArgumentException("Loan period must be positive!");
        }
        else{
            this.loanPeriodDays=days;
        }
    }

    @Override
    public void reserve(String reserverName) {
        if(reserved){
            System.out.println("This book is already reserved!");
        }
        else{
            reserved=true;
            reservationDate=LocalDate.now();
            reservationHolder=reserverName;
            System.out.println("This book is successfully reserved by "+reserverName+ "!");
        }
    }

    @Override
    public void cancelReservation() {
        if(!reserved){
            System.out.println("This book is not reserved yet!");
        }
        else{
            reserved=false;
            reservationHolder=null;
            reservationDate=null;
            System.out.println("The reservation is cancelled successfully!");
        }
    }

    public boolean isReserved(){
        return reserved;
    }
    public void setReserved(boolean reserved){
        this.reserved=reserved;
        if(!reserved){
            this.reservationHolder=null;
            this.reservationDate=null;
        }
    }
    public String getReservationHolder(){
        return reservationHolder;
    }
    public void setReservationHolder(String name){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Error!");
        }
        else{
            this.reservationHolder=name.trim();
            this.reserved=true;
        }
    }
    public LocalDate getReservationDate(){
        return reservationDate;
    }
    public void setReservationDate(LocalDate date){
        if(date != null && date.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Error!!!");
        }
        else {
            this.reservationDate=date;
            this.reserved=true;
        }
    }

    @Override
    public void borrow() {
        if(isBorrowed){
            System.out.println("This book is already borrowed!");
        }
        else if (reserved){
            System.out.println("This book cannot be borrowed, it is already reserved!");
        }
        else{
            isBorrowed=true;
            borrowDate=LocalDate.now();
            System.out.println("This book is successfully borrowed: "+getTitle());
        }
    }

    @Override
    public void returnItem() {
        if(!isBorrowed){
            System.out.println("This book is already at the library!");
        }
        else{
            isBorrowed=false;
            borrowDate=null;
            System.out.println("This book is successfully returned!");
        }
    }

    @Override
    public boolean isAvailableToBorrow() {
        return !isBorrowed && !isReserved();
    }

    @Override
    public int getRemainingDays() {
        if (borrowDate == null){
            return 0;
        }
        else{
            long passed_days = DAYS.between(borrowDate, LocalDate.now());
            return (loanPeriodDays - (int) passed_days);
        }
    }

    public String toString() {
        if (!isBorrowed) {
            return ("ISBN:" + getId() + " |Title:" + getTitle() + " |Author:" + getAuthorOrPublisherOrCreator() + " |Year:" + getPublicationYear() + " |Borrowed:No");
        }
        else {
            return ("ISBN:" + getId() + " |Title:" + getTitle() + " |Author:" + getAuthorOrPublisherOrCreator() + " |Year:" + getPublicationYear() + " ||Borrowed:Yes (remaining "+getRemainingDays()+" days");
        }
    }

    public String toFileFormat(){

        return String.join(",",getType(), getId(),getTitle(),getAuthorOrPublisherOrCreator(),String.valueOf(getPublicationYear()),String.valueOf(isBorrowed()),String.valueOf(getBorrowDate()),String.valueOf(getLoanPeriodDays()),String.valueOf(isReserved()),String.valueOf(getReservationHolder()),String.valueOf(getReservationDate()));
    }

}
