package Utils;

import java.util.Random;

/**
 * Класс, реализующий интерфейс BankNumberGenerator
 * Осуществляет генерацию случайных чисел для создания номера банковского счета
 */
public class MathRandomGenerator implements BankNumberGenerator {
    private static final int MIN = 10_000_000;
    private static final int MAX = 99_999_999;
    private final Random rand;

    public MathRandomGenerator() {
        this.rand = new Random();
    }

    public Integer bankAccountNumber() {
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }
}