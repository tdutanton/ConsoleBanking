package Utils;

/**
 * Класс, реализующий интерфейс IDGenerator для генерации ID сущностей
 * Реализует простой счетчик с автоинкрементом
 */
public class SequentialIDGenerator implements IDGenerator {
    private int current = 1;

    @Override
    public synchronized Integer nextId() {
        return current++;
    }
}
