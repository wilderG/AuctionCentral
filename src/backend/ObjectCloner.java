package backend;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Creates and returns a deep-copy of an object.
 * Precondition: the object and any contained objects must be serializable.
 * @author Dave Miller (javaworld.com)
 * @author Jared Malone
 */
public class ObjectCloner
{
   /** Private constructor prevents instantiation of this class. */
   private ObjectCloner(){}
   
   /**
    * Returns a deep-copy of a serializable object.
    * @param theOldObject original object
    * @return a copy of the original object
    * @throws Exception if object is not serializable
    */
   static public Object deepCopy(Object theOldObject) throws Exception
   {
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;
      try
      {
         ByteArrayOutputStream bos = 
               new ByteArrayOutputStream(); 
         oos = new ObjectOutputStream(bos);

         // serialize and pass the old object
         oos.writeObject(theOldObject);  
         oos.flush();               
         ByteArrayInputStream bin = 
               new ByteArrayInputStream(bos.toByteArray()); 
         ois = new ObjectInputStream(bin);                  
         
         // return the new object
         return ois.readObject(); 
      }
      catch(Exception e)
      {
         System.out.println("Exception in ObjectCloner = " + e);
         throw(e);
      }
      finally
      {
         oos.close();
         ois.close();
      }
   }
   
}
