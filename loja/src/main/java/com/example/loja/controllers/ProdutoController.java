/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.loja.controllers;

import com.example.loja.dtos.ProdutoDTO;
import com.example.loja.models.Produto;
import com.example.loja.repositories.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author eduardo
 */
@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoController(ProdutoRepository produtoRepository){
       this.produtoRepository = produtoRepository;
    }


    @GetMapping("")
    public ModelAndView index(){
        List<Produto> lp = this.produtoRepository.findAll();
        ModelAndView mv = new ModelAndView("produtos/index");
        mv.addObject("lp",lp);
        return mv;
    }
    @GetMapping("/new")
    public ModelAndView nnew(Produto produto) {
        return new ModelAndView("produtos/new");
    }

    @PostMapping("")
    public ModelAndView create(ProdutoDTO pdto) {
        if (!pdto.validate()) {
            return new ModelAndView("redirect:/produtos/new");
        } else {
            Produto produto = new Produto();
            produto = pdto.toProduto(produto);
            this.produtoRepository.save(produto);
            return new ModelAndView("redirect:/produtos");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, Produto produto) {
        Optional<Produto> optional = this.produtoRepository.findById(id);

        if (optional.isPresent()) {
            produto = optional.get();
            ModelAndView mv = new ModelAndView("/produtos/edit");
            mv.addObject("produtoID", id);
            mv.addObject("produto", produto);
            return mv;
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Integer id, ProdutoDTO pdto) {
        if (!pdto.validate()) {
            ModelAndView mv = new ModelAndView("redirect:/produtos/" + id + "/edit");
            mv.addObject("produtoID", id);
            return mv;
        } else {
            Optional<Produto> optional = this.produtoRepository.findById(id);

            if (optional.isPresent()) {
                Produto produto = optional.get();
                produto = pdto.toProduto(produto);
                this.produtoRepository.save(produto);
                return new ModelAndView("redirect:/produtos");
            } else {
                return new ModelAndView("/error");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        try {
            this.produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/produtos");
    }
}
