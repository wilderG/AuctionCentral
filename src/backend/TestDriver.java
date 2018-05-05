package backend;

import model.User;

/**
 * Test driver for persistent storage. You can create an auction and user the auction belongs to.
 * Then call addUser to store both objects. If you rerun the program and call testload, both
 * objects and their relationship will be present in memory.
 * @author Jared
 */
public class TestDriver {

	public static void main(String[] args) {
		StorageIO storage = new StorageIO("data/storage.dat");
		User user, copy;
				
		/* to create a user uncomment here */
		
//		user = new User("tester", "Sam Tester");
//		storage.storeUser("tester", user);
//		storage.writeData();
		
		
		/* to load a user and print details */
		user = storage.getUser("tester");
		System.out.println(user.getDisplayName());
	
//		copy = null;
//		
//		try {
//			copy = (User) ObjectCloner.deepCopy(user);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("Cloned user: " + copy.getDisplayName());
	}
	
}
