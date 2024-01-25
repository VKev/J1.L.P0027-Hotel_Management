package Model;


import Utils.CustomInput;
import java.util.Optional;


public class Hotel {
    private String Hotel_id;
    private String Hotel_name;
    private int Hotel_Room_Available;
    private String Hotel_Address;
    private String Hotel_Phone;
    private int Hotel_Rating;
    
    public static String idRegex = ".*";
    public static String nameRegex = ".*";
    public static String roomAvailableRegex = "\\d+";
    public static String addressRegex = "^[a-zA-Z0-9\\s,'.-]+$";
    public static String phoneRegex = "^\\d{3,12}$";
    public static String ratingRegex = "^\\d+$";
    
    public void Input(){
        CustomInput customInput = new CustomInput();
        customInput.SetErrorContentOffset(2);
        
        Hotel_id = customInput.Regex("• Enter Hotel ID: ", idRegex ,null);
        Hotel_name = customInput.Regex("• Enter Hotel Name: ", nameRegex, null);
        Hotel_Room_Available =Integer.parseInt(customInput.Regex("• Enter Hotel Room Available: ", roomAvailableRegex, null));
        Hotel_Address = customInput.Regex("• Enter Hotel Address: ", addressRegex, null);
        Hotel_Phone = customInput.Regex("• Enter Hotel Phone(4-12 digit): ", phoneRegex, null);
        Hotel_Rating = Integer.parseInt(customInput.Regex("• Hotel Rating: ", ratingRegex, null));
    }
    
    public void Update(){
        CustomInput customInput = new CustomInput();
        customInput.SetErrorContentOffset(2);
        
        Hotel_id =  Optional.ofNullable( customInput.RegexBlankHandle("• Enter Hotel ID: ", idRegex ,null)).orElse(Hotel_id);
        Hotel_name = Optional.ofNullable(customInput.RegexBlankHandle("• Enter Hotel Name: ", nameRegex, null)).orElse(Hotel_name);
        Hotel_Room_Available = Integer.parseInt(Optional.ofNullable(customInput.RegexBlankHandle("• Enter Hotel Room Available: ", roomAvailableRegex, null)).orElse(String.valueOf(Hotel_Room_Available)));
        Hotel_Address =  Optional.ofNullable(customInput.RegexBlankHandle("• Enter Hotel Address: ", addressRegex, null)).orElse(Hotel_Address);
        Hotel_Phone = Optional.ofNullable(customInput.RegexBlankHandle("• Enter Hotel Phone(4-12 digit): ", phoneRegex, null)).orElse(Hotel_Phone);
        Hotel_Rating = Integer.parseInt(Optional.ofNullable(customInput.RegexBlankHandle("• Hotel Rating: ", ratingRegex, null)).orElse(String.valueOf(Hotel_Rating)));
    }
    
    public String GetId(){
        return Hotel_id;
    }
    
    public String GetName(){
        return Hotel_name;
    }
    
    public void ShowInfomation(){
        System.out.println("Hotel ID: " + Hotel_id);
        System.out.println("Hotel Name: " + Hotel_name);
        System.out.println("Hotel Room Available: " + Hotel_Room_Available);
        System.out.println("Hotel Address: " + Hotel_Address);
        System.out.println("Hotel Phone: " + Hotel_Phone);
        System.out.println("Hotel Rating: " + Hotel_Rating +" star");
    }
    
    public String ToString(){
        return Hotel_id+"|"+Hotel_name+"|"+Hotel_Room_Available+"|"+Hotel_Address+"|"+Hotel_Phone+"|"+Hotel_Rating;
    }
    
    public void InputFromString(String s){
        String data[] =  s.split("\\|");
        Hotel_id = data[0];
        Hotel_name = data[1];
        Hotel_Room_Available = Integer.parseInt(data[2]);
        Hotel_Address = data[3];
        Hotel_Phone = data[4];
        Hotel_Rating = Integer.parseInt(data[5]);
    }
    
}
