package me.oktop.java8inaction.modernjava;

public class OopAndFpExample {
    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService(new Addition(), new Subtraction());
        System.out.println(calculatorService.calculate(1, 2));
    }
}

class Addition implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplication implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}

class Division implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

interface Calculation {
    int calculate(int num1, int num2);
}

class CalculatorService {
    private final Calculation calculation1;
    private final Calculation calculation2;

    CalculatorService(Calculation calculation1 , Calculation calculation2) {
        this.calculation1 = calculation1;
        this.calculation2 = calculation2;
    }

    public int calculate(int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            calculation1.calculate(num1, num2);
        }
        throw new IllegalArgumentException("invalid input num2 : " + num1 + " num2 : " + num2);
    }

    public int compute(int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            calculation2.calculate(num1, num2);
        }
        throw new IllegalArgumentException("invalid input num2 : " + num1 + " num2 : " + num2);
    }
}

class FpCalculatorService {
    public int calculate(Calculation calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        }
        throw new IllegalArgumentException("invalid input num1 : " + num1 + " num2 : " + num2);
    }
}
