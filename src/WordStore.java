public interface WordStore {
    void add(String word);

    int count(String word);

    void remove(String word);
}
