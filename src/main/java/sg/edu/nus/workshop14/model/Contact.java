package sg.edu.nus.workshop14.model;

import java.util.Random;
import java.io.Serializable;

/**
 * Plain java obejct represent the Contact information 
 * of the address book
 */
public class Contact implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private String email;
        private int phoneNumber;
        private String id;

        public Contact(){
                this.id = this.generateId(8);
        }

        public Contact(String name, String email, int phoneNumber){
                this.id = this.generateId(8);
                this.name = name;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }

        private synchronized String generateId(int numchars){
                Random r = new Random();
                StringBuffer sb = new StringBuffer();
                while(sb.length() < numchars){
                        sb.append(Integer.toHexString(r.nextInt()));
                }
                return sb.toString().substring(0, numchars);
        }

        public String getName(){
                return this.name;
        }   
        
        public void setName(String name){
                this.name = name;
        }

        public String getEmail(){
                return this.email;
        }   
        
        public void setEmail(String email){
                this.email = email;
        }

        public int getPhoneNumber(){
                return this.phoneNumber;
        }   
        
        public void setPhoneNumber(int phoneNumber){
                this.phoneNumber = phoneNumber;
        }

        public String getId(){
                return this.id;
        }   
        
        public void setId(String id){
                this.id = id;
        }
}