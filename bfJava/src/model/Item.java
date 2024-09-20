package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {
    private int id;
    private String nome;
    private String descricao;
    private boolean carregavel;
    private Item combinedItem;

    // Construtor com todos os parâmetros
    public Item(int id, String nome, String descricao, boolean carregavel, Item combinedItem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.carregavel = carregavel;
        this.combinedItem = combinedItem;
    }

    // Construtor que não precisa do combinedItem
    public Item(int id, String nome, String descricao, boolean carregavel) {
        this(id, nome, descricao, carregavel, null);
    }

    // Getters e Setters
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

    // Metodo para combinar itens
    public boolean canCombineWith(Item otherItem) {
        // Se você tiver lógica específica para combinação, adicione aqui
        return combinedItem != null && combinedItem.equals(otherItem);
    }

    public Item combineWith(Item otherItem) {
        if (canCombineWith(otherItem)) {
            return combinedItem; // Retorna o novo item criado pela combinação
        }
        return null;
    }
}