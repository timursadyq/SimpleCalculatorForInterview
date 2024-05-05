import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); //ввод строки арифметического выражения с клавиатуры
        try {
            System.out.print(ProcessingTheString.RPNtoAnswer(ProcessingTheString.ExpressionToRPN(input)));
        }
        catch (Throwable s) {
            System.out.print("Ошибка!");
        }
    }
}