package br.com.douglas.animais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.douglas.animais.entity.Animais;
import br.com.douglas.animais.repository.AnimaisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimaisService {

    @Autowired
    private AnimaisRepository animaisRepository;

    public Animais salvar(Animais animais){
        return animaisRepository.save(animais);
    }

    public List<Animais> listaAnimais(){
        return animaisRepository.findAll();
    }

    public Optional<Animais> buscarPorId(Long id){
        return animaisRepository.findById(id);
    }

    public void removerPorId(Long id){
        animaisRepository.deleteById(id);
    }
}
