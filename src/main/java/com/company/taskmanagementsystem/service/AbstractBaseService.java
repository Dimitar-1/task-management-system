package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.NotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractBaseService<T, ID extends Serializable> {

    int save(T model) throws BusinessException;

    List<T> findAll();

    Optional<T> findById(ID modelId) throws NotFoundException;

    T update(ID old, T model) throws NotFoundException, BusinessException;

    void deleteById(ID modelId) throws NotFoundException;

}