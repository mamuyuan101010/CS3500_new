package view;

public interface IViewListener {
  void handleAddStock(String name, Double quantity);
  void handleSellStock(String name, Double quantity);
}
