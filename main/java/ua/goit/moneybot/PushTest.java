package ua.goit.moneybot;
import lombok.Data;

@Data
public class PushTest {

    private int numbers;


    public static void main(String[] args) {
        PushTest test = new PushTest();
        test.setNumbers(10);
        System.out.println(test.getNumbers());

    }
}
