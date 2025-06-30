package dev.java10X.CadastroDeNinjas.Ninjas;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa e minha primeira mensagem nessa rota";
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninja = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninja);
    }

    @Operation(summary = "Lista o ninja por ID", description = "Rota lista um ninja pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "NInja nao encontrado")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.buscarNinjaPorId(id);
        if(ninja != null){
         return ResponseEntity.ok(ninja);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID: " + id + " nao foi encontrado");
        }

    }
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criacao do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO novoNinja= ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + ", (ID): " + novoNinja.getId());
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Altera o ninja por ID", description = "Rota altera um ninja por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja nao encontrado")
    })

    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuario manda o ID no caminho da requisicao")
            @PathVariable Long id,

            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisicao")
            @RequestBody NinjaDTO ninjaDTO
    ) {
        NinjaDTO ninja = ninjaService.atualizarNinja(id,ninjaDTO);
        if( ninja != null){
            return ResponseEntity.ok(ninja);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID: " + id + " nao foi encontrado");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable long id) {

        if(ninjaService.buscarNinjaPorId(id) != null){
        ninjaService.deletarNinja(id);
        return ResponseEntity.ok("Ninja com o ID: " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID: " + id + " nao foi encontrado");
        }

    }

}