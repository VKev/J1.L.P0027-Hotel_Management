
import java.util.ArrayList;



public class HotelManager {
     private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
     private int choice;
     
     private FileManager fileManager;

     public void Start(){
        InputDataFromFile();
        
        do{
            CreateMenu();
            ChoiceHandle();
        }while(choice >0 && choice <7);
        
        End();
     }
     private void InputDataFromFile(){
        fileManager = new FileManager();
        fileManager.CreateFile("Hotel.dat");
        ArrayList<String> lines = fileManager.GetLines();
        for(String line : lines){
            Hotel newHotel = new Hotel();
            newHotel.InputFromString(line);
            hotelList.add(newHotel);
        }
     }
     private void End(){
        fileManager.CloseFile();
     }
     private void ChoiceHandle(){
         switch(this.choice){
            case 1: AddHotel(); break;
            case 2: CheckExistHotelLogic(); break;
            case 3: UpdateHotel(); break;
            case 4: DeleteHotel(); break;
            case 5: SearchHotel(); break;
            case 6: DisplayHotel(); break;
            default: System.out.println("Program Exited");
        }
     }
     private void CreateMenu(){
         System.out.println("");
         System.out.println("1) Add new Hotel.");
         System.out.println("2) Check exits Hotel.");
         System.out.println("3) Update Hotel infomation.");
         System.out.println("4) Delete Hotel.");
         System.out.println("5) Search Hotel.");
         System.out.println("6) Display Hotel list.");
         System.out.println("7) Press any others choice to quit.");
         
         CustomInput customInput = new CustomInput();
         this.choice = Integer.parseInt( customInput.Regex("Enter your choice: ", "^\\d+$", null) );
     }
     
     private void AddHotel(){
         CreateTitle("Add Hotel");
         Hotel newHotel = new Hotel();
         newHotel.Input();
         if(FindHotelIndexById(newHotel.GetId())==-1){
            hotelList.add(newHotel);
            fileManager.Writeln(newHotel.ToString());
            System.out.println("-----Success-----");
         }else
            System.out.println("-----Fail-----");
     }
     private void CheckExistHotelLogic(){
        CreateTitle("Check Hotel Exist");
        CustomInput customInput = new CustomInput();
        String checkId = customInput.Regex("Enter hotel ID: ", Hotel.idRegex, null);
        if (FindHotelIndexById(checkId) != -1)
            System.out.println("Exist Hotel");
        else 
            System.out.println("No Hotel Found!");
     }
     private int FindHotelIndexById(String checkId){
        for(int i=0; i< hotelList.size(); i++){
            if( hotelList.get(i).GetId().equals(checkId) ){
                return i;
            }
         }
        return -1;
     }
     private void UpdateHotel(){
         CreateTitle("Update Hotel");
         CustomInput customInput = new CustomInput();
         customInput.SetErrorContentOffset(3);
         
         String searchId =  customInput.Regex("Enter Hotel ID: ", Hotel.idRegex, null);
         
         int hotelIndex = FindHotelIndexById(searchId);
         if(hotelIndex == -1){
             System.out.println("Hotel does not exist");
             return;
         }
         
         hotelList.get(hotelIndex).Update();
         
         ArrayList<String> lines = fileManager.GetLines();
         lines.set(hotelIndex,  hotelList.get(hotelIndex).ToString());
         fileManager.Update(lines);
         System.out.println("Result:");
         hotelList.get(hotelIndex).ShowInfomation();
     }
     private void DeleteHotel(){
         CreateTitle("Delete Hotel");
         CustomInput customInput = new CustomInput();
         customInput.SetErrorContentOffset(3);
         
         String searchId =  customInput.Regex("Enter Hotel ID: ", Hotel.idRegex, null);
         
         int hotelIndex = FindHotelIndexById(searchId);
         if(hotelIndex == -1){
             System.out.println("Hotel does not exist");
             return;
         }
         String sure = customInput.Regex("Do you ready want to delete this hotel(y/n): ", "^[YyNn]$", null);
         if(sure.toLowerCase().equals("n")){
             System.out.println("-----Fail-----");
             return;
         }
         
         hotelList.remove(hotelIndex);
         
         ArrayList<String> lines = fileManager.GetLines();
         lines.remove(hotelIndex);
         fileManager.Update(lines);
         System.out.println("-----Success-----");
     }
     
     private void SearchHotel(){
         CreateTitle("Search Hotel");
         
         System.out.println("1. Search Hotel by ID.");
         System.out.println("2. Search Hotel by Name.");

         CustomInput customInput = new CustomInput();
         customInput.SetErrorContentOffset(3);
         int choice =  Integer.parseInt( customInput.Regex("   Enter your choice: ", "^[12]$", null) );
                 
         switch(choice){
            case 1: SearchById(); break;
            case 2: SearchByName(); break;
         }
     }
     private void SearchById(){
         CustomInput customInput = new CustomInput();
         customInput.SetErrorContentOffset(3);
         
         String searchString =  customInput.Regex("   Enter Hotel ID: ", Hotel.idRegex, null);
         
         ArrayList<Hotel> searchedHotels = new ArrayList<Hotel>();
         for(int i=0; i< hotelList.size();i++){
             if(hotelList.get(i).GetId().toLowerCase().contains(searchString.toLowerCase())){
                searchedHotels.add(hotelList.get(i));
             }
         }
         
         searchedHotels.sort((h1,h2) -> h2.GetId().compareTo(h1.GetId()));
         for(Hotel hotel :searchedHotels){
            System.out.println();
            hotel.ShowInfomation();
         }
     } 
     private void SearchByName(){
         CustomInput customInput = new CustomInput();
         customInput.SetErrorContentOffset(3);
         
         String searchName =  customInput.Regex("   Enter Hotel Name: ", Hotel.nameRegex, null);
         
         Hotel result = null;
         for(Hotel hotel : hotelList){
             if(hotel.GetName().equals(searchName)){
                 result = hotel;
                 System.out.println();
                 hotel.ShowInfomation();
                 break;
             }
         }
         if(result == null)
             System.out.println("No Hotel Found!");
     }
     
     private void DisplayHotel(){
         CreateTitle("Display Hotel List");
         ArrayList<Hotel> displayHotels = hotelList;
         displayHotels.sort((h1,h2) -> h2.GetName().compareTo(h1.GetName()));
         for(Hotel hotel : displayHotels){
             System.out.println();
             hotel.ShowInfomation();
         }
         
         CreateTitle("-----------------");
     }
     
     private void CreateTitle(String title){
          System.out.print("\n-----" + title + "-----\n");
     }

}
