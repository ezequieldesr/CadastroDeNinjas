package dev.java10X.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninja = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninja);
        return "listarNinjas"; //retornar o nome da pagina que redenrisa
    }

    @GetMapping("/listar/{id}")
    public String listarNinjaPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.buscarNinjaPorId(id);
        if(ninja != null){
            model.addAttribute("ninjas",ninja);
            return "detalhesNinja";
        }else{
            model.addAttribute("mensagem","Ninja nao encontrado");
            return "listarNinjas";
        }

    }



    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable long id) {
        ninjaService.deletarNinja(id);
        return "redirect:/ninjas/ui/listar";
    }

}
