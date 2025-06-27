package dev.java10X.CadastroDeNinjas.Ninjas;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Essa e minha primeira mensagem nessa rota";
    }

    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja criado com sucesso!";
    }

    @GetMapping("/listar")
    public String listarNinjas(){
        return "Ninjas listados com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarNinja(){
        return "Ninja atualizado com sucesso!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarNinja(){
        return "Ninja deletado com sucesso!";
    }

}