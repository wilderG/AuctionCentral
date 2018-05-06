package view;

import java.time.LocalDate;

import backend.StorageIO;
import model.Auction;
import model.AuctionCalendar;
import model.NonProfitContact;

public class DemoDataAlmostAtCapacityAuctions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String fileName = "data/storage.dat";

        //STORE
        StorageIO storage = new StorageIO(fileName);
        AuctionCalendar calendar = storage.getCalendar();

        Auction addedAuction;
        LocalDate dateOfAdd;
        for (int i = 1; i < AuctionCalendar.MAXIMUM_FUTURE_AUCTIONS; i++) {
            addedAuction = new Auction(LocalDate.now().plusDays(30 + i), 0, 0, "");
            dateOfAdd = addedAuction.getDate();
            calendar.submitAuction(addedAuction, dateOfAdd.getDayOfMonth(), dateOfAdd.getMonthValue(), dateOfAdd.getYear());
        }


        NonProfitContact nonProfit1 = new NonProfitContact("nonprof1", "The Human Fund");
        storage.storeUser(nonProfit1);
        NonProfitContact nonProfit2 = new NonProfitContact("nonprof2", "The Fund for People");
        storage.storeUser(nonProfit2);
        storage.setCalendar(calendar);
    }

}
