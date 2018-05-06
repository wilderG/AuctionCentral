package view;

import java.time.LocalDate;

import backend.StorageIO;
import model.Auction;
import model.AuctionCalendar;
import model.NonProfitContact;




/**
 * 
 * @author Steven Golob 
 * @version May 6, 2018
 */
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

        // nonprofit to add an auction successfully
        NonProfitContact nonProfit1 = new NonProfitContact("nonprof1", "The Human Fund");
        // nonprofit to try and add an auction and fails 
        NonProfitContact nonProfit2 = new NonProfitContact("nonprof2", "The Fund for People");
        storage.storeUser(nonProfit1);
        storage.storeUser(nonProfit2);
        storage.setCalendar(calendar);
    }

}
