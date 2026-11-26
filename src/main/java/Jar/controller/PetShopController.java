package Jar.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Jar.model.Produto;
import Jar.repository.ProdutoRepository;

@RestController
@RequestMapping("/api")
public class PetShopController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> dados) {
        if ("admin".equals(dados.get("usuario")) && "1234".equals(dados.get("senha"))) {
            return ResponseEntity.ok(Map.of("status", "ok"));
        }
        return ResponseEntity.status(401).body(Map.of("erro", "Utilizador ou palavra-passe inválidos"));
    }

    @GetMapping("/produtos")
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @PostMapping("/produtos")
    public Produto cadastrar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @PutMapping("/produtos/{id}/vender")
    public ResponseEntity<Produto> vender(@PathVariable Long id) {
        return repository.findById(id).map(p -> {
            if (p.getQuantidade() > 0) {
                p.setQuantidade(p.getQuantidade() - 1);
                repository.save(p);
            }
            return ResponseEntity.ok(p);
        }).orElse(ResponseEntity.notFound().build());
    }
}