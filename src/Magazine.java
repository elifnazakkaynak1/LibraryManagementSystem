import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Magazine extends Material implements Borrowable {

    private boolean isBorrowed;
    private LocalDate borrowDate;
    private int loanPeriodDays;

    public Magazine(String type,String id,String title,String publisher,int publicationYear){
        super(type,id,title,publisher,publicationYear);
        isBorrowed=false;
        borrowDate=null;
        loanPeriodDays=14;
    }

    public boolean isBorrowed(){
        return isBorrowed;
    }
    public void setBorrowed(boolean borrowed){
        if(!isBorrowed){
            isBorrowed=true;
        }
        else{
            System.out.println("The magazine is already borrowed!");
        }
    }
    public LocalDate getBorrowDate(){
        return borrowDate;
    }
    public void setBorrowDate(LocalDate borrowDate){
        this.borrowDate =LocalDate.now();
    }
    public int getLoanPeriodDays(){
        return loanPeriodDays;
    }
    public void setLoanPeriodDays(int days){
        if (days<=0){
            System.out.println("Loan period must be greater than 0 days.");
        }
        else{
            this.loanPeriodDays=days;
        }
    }

    @Override
    public void borrow() {
        if (isBorrowed) {
            System.out.println("This magazine is already borrowed!");
        } else {
            isBorrowed = true;
            borrowDate = LocalDate.now();
            System.out.println("The magazine is successfully borrowed!");
        }
    }

    @Override
    public void returnItem() {
        if (!isBorrowed) {
            System.out.println("This magazine is already in library!");
        } else {
            isBorrowed = false;
            borrowDate = null;
            System.out.println("The magazine is successfully returned!");

        }
    }

    @Override
    public boolean isAvailableToBorrow() {
        return !isBorrowed;
    }

    @Override
    public int getRemainingDays() {
        if (!isBorrowed) {
            return 0;
        } else {
            long passed_days = DAYS.between(borrowDate, LocalDate.now());
            return (loanPeriodDays - (int) passed_days);
        }
    }

    public String toFileFormat(){
        return String.join(",",getType(), getId(),getTitle(),getAuthorOrPublisherOrCreator(),String.valueOf(getPublicationYear()),String.valueOf(isBorrowed()),String.valueOf(getBorrowDate()),String.valueOf(getLoanPeriodDays()));
    }
    public String toString() {
        if (!isBorrowed) {
            return ("Id:" + getId() + " |Title:" + getTitle() + " |Author:" + getAuthorOrPublisherOrCreator() + " |Year:" + getPublicationYear() + " |Borrowed:No");
        }
        else {
            return ("Id:" + getId() + " |Title:" + getTitle() + " |Author:" + getAuthorOrPublisherOrCreator() + " |Year:" + getPublicationYear() + " |Borrowed:Yes (remaining "+getRemainingDays()+" days");
        }
    }
}