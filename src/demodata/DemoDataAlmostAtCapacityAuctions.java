package demodata;

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
        StorageIO storage = new StorageIO(fileName, true);
        AuctionCalendar calendar = storage.getCalendar();

        // non-profit to add an auction successfully
        NonProfitContact nonProfit1 = new NonProfitContact("nonprof1", "The Human Fund");
        // non-profit to try and add an auction and fails 
        NonProfitContact nonProfit2 = new NonProfitContact("nonprof2", "The Fund for People");
        
        Auction addedAuction;
        LocalDate dateOfAdd;
        for (int i = 1; i < AuctionCalendar.DEFAULT_MAXIMUM_FUTURE_AUCTIONS; i++) {
            addedAuction = new Auction(LocalDate.now().plusDays(30 + i), nonProfit1);
            dateOfAdd = addedAuction.getDate();
            nonProfit1.addAuction(addedAuction);
            calendar.submitAuction(addedAuction, dateOfAdd.getDayOfMonth(), dateOfAdd.getMonthValue(), 
            		dateOfAdd.getYear());
        }

        storage.storeUser(nonProfit1);
        storage.storeUser(nonProfit2);
        storage.setCalendar(calendar);
    }

}
