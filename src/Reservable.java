public interface Reservable {
    void reserve(String reserverName);
    void cancelReservation();
    boolean isReserved();
    String getReservationHolder();
}
