import java.util.*;
class Card {
  private char symbol;
  private int number;

  public Card(char symbol, int number) {
    this.symbol = symbol;
    this.number = number;
  }

  public char getSymbol() { return symbol; }

  public int getNumber() { return number; }

  @Override
  public String toString() {
    return symbol + " " + number;
  }
}

class EXP2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Map<Character, Set<Card>> cardsMap = new HashMap<>();

    System.out.print("Enter the number of cards: ");
    int n = scanner.nextInt();
    int symbols = 0, cards = 0;

    for (int i = 0; i < n; i++) {
      System.out.println("\nEnter details for card " + (i + 1) + "-");
      System.out.print("Enter symbol: ");
      char symbol = scanner.next().charAt(0);
      System.out.print("Enter number: ");
      int number = scanner.nextInt();

      Card newCard = new Card(symbol, number);

      if (!cardsMap.containsKey(symbol)) {
        cardsMap.put(symbol, new HashSet<>());
        symbols++;
      }
      cardsMap.get(symbol).add(newCard);
    }

    for (Map.Entry<Character, Set<Card>> entry : cardsMap.entrySet()) {
      System.out.println("Symbol: " + entry.getKey() +
                         ", Cards: " + entry.getValue());
    }
    System.out.println("Number of Symbols: " + cardsMap.size());
    System.out.println("Number of Cards: " +
                       cardsMap.values().stream().mapToInt(Set::size).sum());
  }
}
