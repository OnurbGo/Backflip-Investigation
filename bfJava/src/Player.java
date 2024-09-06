import java.util.ArrayList;
import java.util.List;

public class Player {
    private String login;
    private int currentSceneId;
    private List<Item> inventory;

    // Construtor
    public Player(String login, int currentSceneId) {
        this.login = login;
        this.currentSceneId = currentSceneId;
        this.inventory = new ArrayList<>(); // Inicializa o inventário
    }

    // Método para adicionar um item ao inventário
    public void addItemToInventory(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    // Outros métodos e getters/setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCurrentSceneId() {
        return currentSceneId;
    }

    public void setCurrentSceneId(int currentSceneId) {
        this.currentSceneId = currentSceneId;
    }

    // Método para obter o inventário
    public List<Item> getInventory() {
        return inventory;
    }
}