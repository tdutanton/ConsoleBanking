import Bank.Bank;
import Interaction.Interaction;
import Utils.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final IDGenerator idGenerator = new SequentialIDGenerator();
        final BankNumberGenerator bankNumberGenerator = new MathRandomGenerator();
        final Bank bank = new Bank(idGenerator, bankNumberGenerator);
        final Interaction interaction = new Interaction(bank, scanner);
        System.out.println("Добро пожаловать в консольный банк!");
        interaction.runMenu();
    }
}