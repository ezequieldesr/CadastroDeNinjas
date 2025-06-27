package dev.java10X.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listarMissoes(){
        return "Missoes listadas com sucesso!";
    }

    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao criada com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarMissao(){
        return "Missao atualizada com sucesso!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarMissao(){
        return "Missao deletada com sucesso!";
    }
}
