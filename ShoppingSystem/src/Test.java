import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        ShoppingSystem test=new ShoppingSystem();
        PassWord.isPassWordOk("sfjajFAAA465!@#");
        //System.out.println(PassWord.generateRandomPassWord());
        /*int choice;
        try {
            Scanner sc1=new Scanner(System.in);
            choice=sc1.nextInt();
        }catch (InputMismatchException exception){
            System.out.println("您输入的不是整型数据，请重新输入");
        }
        PassWord newPw=new PassWord();
        String pw=PassWord.generateRandomPassWord();
        System.out.println(pw);*/
        test.initialization();
    }

}
