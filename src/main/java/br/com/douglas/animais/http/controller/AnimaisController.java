package br.com.douglas.animais.http.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.douglas.animais.entity.Animais;
import br.com.douglas.animais.service.AnimaisService;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimaisController {

    @Autowired
    private AnimaisService animaisService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animais salvar(@RequestBody Animais animais){
        return animaisService.salvar(animais);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Animais> listaAnimais(){
        return animaisService.listaAnimais();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animais buscarAnimaisPorId(@PathVariable("id") Long id){
        return animaisService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animais nao encontrados."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAnimais(@PathVariable("id") Long id){
        animaisService.buscarPorId(id)
                .map(animais -> {
                    animaisService.removerPorId(animais.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animais nao encontrados."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAnimais(@PathVariable("id") Long id, @RequestBody Animais animais){
        animaisService.buscarPorId(id)
                .map(animaisBase -> {
                    modelMapper.map(animais, animaisBase);
                    animaisService.salvar(animais);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animais nao encontrados."));
    }


}
