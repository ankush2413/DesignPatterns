package parkingsystem;

class PaymentReceipt {
    String ticketId;
    double amount;
    long entryTime;
    long exitTime;

    public PaymentReceipt(String ticketId, double amount, long entryTime, long exitTime) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    @Override
    public String toString() {
        return "Receipt [Ticket: " + ticketId + ", Cost: $" + amount + "]";
    }
}