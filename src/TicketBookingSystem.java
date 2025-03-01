import java.util.concurrent.*;

public class TicketBookingSystem {
  private final ConcurrentHashMap<Integer, Seat> seats;
  private final ExecutorService executorService;

  public TicketBookingSystem() {
    seats = new ConcurrentHashMap<>();
    executorService = Executors.newFixedThreadPool(10);
    for (int i = 1; i <= 10; i++) {
      seats.put(i, new Seat(i, "available"));
    }
  }

  public boolean makeBooking(Booking booking) {
    Seat seat = seats.get(booking.getSeatNumber());
    if (seat != null && seat.getStatus().equals("available")) {
      if (seats.replace(booking.getSeatNumber(), seat,
                        new Seat(seat.getNumber(), "booked"))) {
        return true;
      }
    }
    return false;
  }

  public void bookTicket(Booking booking) {
    Runnable bookingTask = () -> {
      if (makeBooking(booking)) {
        System.out.println(
            (booking.isVip() ? "VIP Booking: " : "Regular Booking: ") +
            "Seat " + booking.getSeatNumber() + " confirmed.");
      } else {
        System.out.println("Error: Seat already booked.");
      }
    };
    Thread thread = new Thread(bookingTask);
    thread.setPriority(booking.isVip() ? Thread.MAX_PRIORITY
                                       : Thread.NORM_PRIORITY);
    executorService.execute(thread);
  }

  public static void main(String[] args) {
    TicketBookingSystem system = new TicketBookingSystem();
    Booking vipBooking = new Booking(1, 1, true);
    Booking regularBooking = new Booking(2, 2, false);
    system.bookTicket(vipBooking);
    system.bookTicket(regularBooking);
  }
}

class Seat {
  private int number;
  private String status;

  public Seat(int number, String status) {
    this.number = number;
    this.status = status;
  }

  public int getNumber() { return number; }
  public String getStatus() { return status; }
}

class Booking {
  private int userId;
  private int seatNumber;
  private boolean vip;

  public Booking(int userId, int seatNumber, boolean vip) {
    this.userId = userId;
    this.seatNumber = seatNumber;
    this.vip = vip;
  }

  public int getUserId() { return userId; }
  public int getSeatNumber() { return seatNumber; }
  public boolean isVip() { return vip; }
}
