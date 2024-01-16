
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomInput {
    private String errorContent = "Invalid input. Please enter again: ";
    public String Regex(String outContent, String regularExpress, String errorContent){
        if(errorContent != null){
            this.errorContent = errorContent;
        }
        System.out.print(outContent);
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            Pattern pattern = Pattern.compile(regularExpress);
            Matcher matcher = pattern.matcher(userInput);
            if (matcher.matches()) {
                break;
            }else{
                System.out.print(this.errorContent);
            }
        }
        return userInput;
    }
    
    public String RegexBlankHandle(String outContent, String regularExpress, String errorContent){
        if(errorContent != null){
            this.errorContent = errorContent;
        }
        System.out.print(outContent);
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            Pattern pattern = Pattern.compile(regularExpress);
            Matcher matcher = pattern.matcher(userInput);
            
            Pattern blankPattern = Pattern.compile("^\\s*$");
            Matcher blankMatcher = blankPattern.matcher(userInput);
            if(blankMatcher.matches()){
                return null;
            }
            
            if (matcher.matches()) {
                break;
            }else{
                System.out.print(this.errorContent);
            }
        }
        return userInput;
    }
    
    public void SetErrorContentOffset(int offset){
        for(int i=0;i< offset;i++){
            errorContent = " "+errorContent;
        }
    }
}
