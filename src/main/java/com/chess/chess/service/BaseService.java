package com.chess.chess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T,ID, R extends JpaRepository<T,ID>>{
    @Autowired
    protected R repositorio;

    public T save(T t) {
        return repositorio.save(t);
    }

    public Optional<T> findById(ID id) {
        return repositorio.findById(id);
    }

    public List<T> findAll() {
        return repositorio.findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    public T edit(T t) {
        return repositorio.save(t);
    }

    public void delete(T t) throws IOException {
        repositorio.delete(t);
    }

    public void deleteById(ID id) throws IOException {
        repositorio.deleteById(id);
    }

}