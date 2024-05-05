import java.util.Stack;

public class ProcessingTheString {
    //Обрабатываем введённое нами выражение
    public static String ExpressionToRPN(String input) {
        String transExpression = ""; // преобразованная в польскую нотацию выражание
        Stack<Character> stack = new Stack<>(); //стек для работы с двумя числами
        int priority; //переменаня для отпределения приоретета операции
        int amountOfNumbers = 0;

        for (int i = 0; i < input.length(); i++) {
            priority = getPriority(input.charAt(i)); //определяем приоритет

            if (amountOfNumbers > 2)
                return ("ИСКЛЮЧЕНИЕ! Введено больше трёх значений!");
            if (priority == 0)  {
                transExpression += input.charAt(i); // Добавляем число
                amountOfNumbers++;
            }
            else if (priority > 0) {
                transExpression += ' ';

                while (!stack.empty()) {
                    /** пока стек не пуст
                     * проверяем больше либо ривен приоритет верхнего значения в стеке чем текущий приоритет(1 либо 2)**/
                    if(getPriority(stack.peek()) >= priority) {
                        transExpression += stack.pop(); //если больше то удаляем элемент из стека и добавляем в изменённое выражение(ОПН)
                    }
                    else break;
                }
                stack.push(input.charAt(i)); //добавляем текущий элемент в стек
            }

        }
        while (!stack.empty()) transExpression += stack.pop(); // пока стек не пуст заполсяем строку значениями из стека при этом удаляем из него сначения
        return  transExpression; //возвращаем уже обработанное выражение
    }

    //Производим вычисление суммы
    public static int RPNtoAnswer(String rpn) throws Exception {
        /** В ЭТОТ МЕТОД НЕОБХОДИМО ПЕРЕДАТЬ УЖЕ ПРЕОБРАЗОВАННОЕ ВЫРАЖЕНИЕ **/

        String operand = "";
        Stack<Integer> stack = new Stack<>();


        for (int i = 0; i < rpn.length(); i++) {
             if (rpn.charAt(i) == ' ') continue; // выражение разделено пробелами чтоб было понятоно что число, а что символ

             if (getPriority(rpn.charAt(i)) == 0) { //если символ из rpn цифра тогда цикл ждёт появления пробела и прерывает прерывает своё выполнения
                 while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0)  { //ждём пробела
                     operand += rpn.charAt(i++); //добавляем в операнд цифру
                     if (i == rpn.length()) break; // если дошли до конца rpn то естественно нужно выпрыгнуть из цикла
                 }
                 if (Integer.parseInt(operand) < 1 || Integer.parseInt(operand) > 10) {
                     throw new Exception("Исключение! Число больше допустимого диапазона");
                 }
                 stack.push(Integer.parseInt(operand));//добавляем в стек преобразованное в число выражение
                 operand = ""; //так сказать обнуляем операнд
             }

             if(getPriority(rpn.charAt(i)) > 0) { // тут непосредственно происходит операция над числами
                 int a = stack.pop(), b = stack.pop(); // присваиваем переменным два первых числа из стека

                 switch (rpn.charAt(i)) {
                     case '+':
                         stack.push(b + a);
                         break;
                     case '-':
                         stack.push(b - a);
                         break;
                     case '*':
                         stack.push(b * a);
                         break;
                     case '/':
                         stack.push(b / a);
                         break;
                 }
             }
        }


        return stack.pop(); //возвращаем сумму всего выражения
    }

    //Определяем приоритет операций
    public static int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 2;
        }

        else if(token == '+' || token == '-') {
            return 1;
        }


        else return 0; // Это для чисел
    }

}
