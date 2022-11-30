/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.loja.dtos;

import com.example.loja.models.Produto;
import java.io.Serializable;

/**
 *
 * @author eduardo
 */
public class ProdutoDTO implements Serializable{
    
    private Integer id;
    private String nome;
    private float valor;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer id) {
        this.id = id;
    }

    public ProdutoDTO(Integer id, String nome, float valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public Produto toProduto(Produto produto){
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setValor(this.valor);
        return produto;
    }
    
    public Boolean validate(){
        return this.id != null && !this.nome.equals("") && this.valor >= 0;
    }

}
