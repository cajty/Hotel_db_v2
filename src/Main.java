
import ui.Menu;
import util.LoginUser;

import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Menu.menuOfLogin();
        if(LoginUser.getUserId() != null && LoginUser.getUserId() == 3){
            Menu.menuOfAdmin();
        } else if (LoginUser.getUserId() != null) {
            Menu.menuOfChoose();

        }else {
            Menu.menuOfLogin();
        }



    }
}