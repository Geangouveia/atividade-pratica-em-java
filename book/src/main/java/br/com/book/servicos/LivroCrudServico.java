package br.com.book.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.book.entidades.LivroEntidade;
import br.com.book.repositorios.LivroRepository;

import java.util.List;
import java.util.Optional;



@Service
public class LivroCrudServico {
    @Autowired
    private LivroRepository livroRepository;

    // Método para criar um livro

    public LivroEntidade criarLivro(LivroEntidade livro) {
        try {
           
            return livroRepository.save(livro);
        } catch (Exception e) {
            // Tratamento de exceção
            System.out.println("Erro ao criar livro " + e.getMessage());
            // Retorna null porde ser uma maneira simples de indicar que ocorreu um erro e
            // não foi possível retornar o valor desejado.
            
        }
        return null;
    }   

    // Método para atualizar um livro existente
    public LivroEntidade atualizaLivro(LivroEntidade livro) {
        try {
            return livroRepository.save(livro);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro " + e.getMessage());
            return null;
        }
    }

    // Método para buscar livro pelo ID
    public Optional<LivroEntidade> buscarLivroPorId(Long id) {
        try {
            return livroRepository.findById(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro por Id " + e.getMessage());
            throw e;// Quando é retornado um Optional vazio é útil em situações que um método espera
                                    // um Optional, mas ele não tem valor para retornar e indica que nenhum valor
                                    // foi encontrado.
        }
    }

    public List<LivroEntidade> buscarLivroPorTitulo(String titulo) {
        try {
            return livroRepository.findByTitulo(titulo);
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro por título de livro " + e.getMessage());
            throw e;
        }
    }

    // Método para listar todos os livros
    public List<LivroEntidade> listarLivros() {
        try {
            return livroRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar todos os livros" + e.getMessage());
            return null;
        }
    }

    // Método para deletar um livro pelo ID
    public void deletarLivro(Long id) {
        try {
            livroRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao tentar deletar livro" + e.getMessage());
            // void não retorna nada
        }
    }


    public boolean camposObrigatoriosPreenchidos(LivroEntidade livro) {
        // Verifica se os campos obrigatórios estão preenchidos
        if (livro != null &&
            livro.getTitulo() != null && !livro.getTitulo().isEmpty() &&
            livro.getAutorPrincipal() != null && !livro.getAutorPrincipal().isEmpty() &&
            livro.getAnoPublicacao() != 0 && livro.getEdicao() != 0) {
            // Se todos os campos obrigatórios estiverem preenchidos, retorna true
            return true;
        } else {
            // Se algum campo obrigatório estiver vazio ou igual a zero, retorna false
            return false;
        }
    }


}
