package dev.java10X.CadastroDeNinjas.Ninjas;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa e minha primeira mensagem nessa rota";
    }

    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado com sucesso!";
    }

    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjaPorId(@PathVariable Long id) {
        return ninjaService.buscarNinjaPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarNinja() {
        return "Ninja atualizado com sucesso!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarNinja() {
        return "Ninja deletado com sucesso!";
    }

}