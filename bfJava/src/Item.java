public class Item {
    private String name;
    private boolean loadable;

    // Construtor
    public Item(int id, String name, String descricao, boolean loadable) {
        this.name = name;
        this.loadable = loadable;
    }

    // Método para verificar se o item pode ser carregado
    public boolean isCarregavel() {
        return loadable;
    }

    // Getter para o nome do item
    public String getName() {
        return name;
    }
}
