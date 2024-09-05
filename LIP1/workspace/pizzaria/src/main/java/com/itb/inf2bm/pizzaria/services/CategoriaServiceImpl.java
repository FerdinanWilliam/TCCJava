package com.itb.inf2bm.pizzaria.services;

import com.itb.inf2bm.pizzaria.exceptions.BadRequest;
import com.itb.inf2bm.pizzaria.exceptions.NotFound;
import com.itb.inf2bm.pizzaria.model.Categoria;
import com.itb.inf2bm.pizzaria.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria salvarCategoria(Categoria categoria) {
        if(!categoria.validarCategoria()){
            throw new BadRequest(categoria.getMensagemErro());
        }
        return  categoriaRepository.save(categoria);
    }


    @Override
    public List<Categoria> listarTodosCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria listaCategoriaPorId(Long id) {
        try{

            return categoriaRepository.findById(id).get();
        }catch(Exception ex){
            throw new NotFound("Categoria não encontrada com o id " + id);        }
    }

    @Override
    @Transactional
    public boolean deletarCategoria(Long id) {
        if(categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
        }else{
            throw new NotFound("Categria não encontrada com o id " + id);
        }

        return true;
    }

    @Override
    @Transactional
    public Categoria atualizarCategoria(Categoria categoria, Long id) {
        try{
            if(!categoria.validarCategoria()){
                throw new BadRequest(categoria.getMensagemErro());
            }
            Categoria categoriaBd = categoriaRepository.findById(id).get();
            categoriaBd.setNome(categoria.getNome());
            categoriaBd.setDescricao(categoria.getDescricao());
            return categoriaRepository.save(categoriaBd); // save : dupla função - update para objeto existente

        }catch (Exception ex){
            throw new NotFound("Categoria não encontrada com o id " + id);
        }
    }


    // final: Um atributo que não será alterado, valor final definitivo
    // @Autowired injeção de dependência, funcional + Não é 100% de accuryce


    // Utilizando o construtor da classe para INJETAR A DEPENDÊNCIA.


}
