import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	//Силно се съмнявам, че разбрах правилно задачата... ако е така, моля, кажете ми, за да мога да се коригирам.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		TextChat chat = new TextChat();
		User u1 = new SimpleUser(chat,"Иван");
		User u2 = new SimpleUser(chat,"Йоана");
		User u3 = new SimpleUser(chat,"Стоян");
		
		
		chat.addUser(u1);
		chat.addUser(u2);
		chat.addUser(u3);		
		for(int i=0;i<1;i++) {
			String a =sc.nextLine();
			if(a.contains("esc")) i++;
			
			chat.GetIndex(0).sendMessage(a);
			
			i--;
		}
		
	}

}	
	abstract class User {
		Chat chat;
		String name;
		
		public User(Chat chat, String name) {
			this.chat = chat;
			this.name = name;
		}
		public String GetName(){
			return name;
		}
		
		public void sendMessage(String message) {
			chat.sendMessage(message, this);
		}
		abstract void getMessage(String message);
		
		public String toString() {
			return "User [name = " + "name" + " ]";	
		}
	}
	
	
	
	
	class SimpleUser extends User{
		public SimpleUser (Chat chat, String name) {
			super(chat, name);
		}
		
		public void getMessage(String message) {
			System.out.println("Потребител " + GetName() + " получава съобщение '" + message +"'");
		}
	}	
	
	interface Chat {
		void sendMessage(String message, User user);
	}
	

	class TextChat implements Chat{
		List<User> users = new ArrayList<>();
		boolean bot = false;
		int x=0;
		
		public void addUser(User u) {
			users.add(u);
		}
		
		public  User GetIndex(int in) {
			return users.get(in);
		}
		
		public void sendMessage(String message, User user) {
			
			for(User u : users) {
				if(message.contains("addBot")==true) bot = true;
				
				if(u!= user) {
					if( bot == true  && message.contains("cat")!=false) {
						message = "написа забранена дума 'cat' ";
						x++;
					}
					u.getMessage(user.GetName() + ": " + message);
				}
			}
			if( x>0) users.remove(user);x=0;
		}
		
	}


		
