package model;

public class Item {
    private int id;
    private String nome;
    private String descricao;
    private boolean carregavel;
    private Item combinedItem;
    private int id_cena; // Adicionando o id_cena

    // Construtor com todos os parâmetros
    public Item(int id, String nome, String descricao, boolean carregavel, Item combinedItem, int id_cena) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.carregavel = carregavel;
        this.combinedItem = combinedItem;
        this.id_cena = id_cena;
    }

    // Construtor sem combinedItem
    public Item(int id, String nome, String descricao, boolean carregavel, int id_cena) {
        this(id, nome, descricao, carregavel, null, id_cena);
    }

    // Construtor que não precisa do combinedItem
    public Item(int id, String nome, String descricao, boolean carregavel) {
        this(id, nome, descricao, carregavel, null, 1); // Define um valor padrão para id_cena quando não for usado
    }

    // Getters e Setters
    public int getId_cena() {
        return id_cena; // Getter para id_cena
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCarregavel() {
        return carregavel;
    }

    public void setCarregavel(boolean carregavel) {
        this.carregavel = carregavel;
    }

    public Item getCombinedItem() {
        return combinedItem;
    }

    public void setCombinedItem(Item combinedItem) {
        this.combinedItem = combinedItem;
    }

    public int getIdCena() { // Getter para id_cena
        return id_cena;
    }

    public void setIdCena(int id_cena) { // Setter para id_cena
        this.id_cena = id_cena;
    }

    // Método para combinar itens
    public boolean canCombineWith(Item otherItem) {
        return combinedItem != null && combinedItem.equals(otherItem);
    }

    public Item combineWith(Item otherItem) {
        if (canCombineWith(otherItem)) {
            return combinedItem;
        }
        return null;
    }
}
