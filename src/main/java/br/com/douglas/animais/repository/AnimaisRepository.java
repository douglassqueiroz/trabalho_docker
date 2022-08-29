package br.com.douglas.animais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglas.animais.entity.Animais;

public interface AnimaisRepository extends JpaRepository<Animais, Long> {
}
