package br.com.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.book.entidades.LivroEntidade;
import br.com.book.servicos.LivroCrudServico;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

@RestController
@RequestMapping("biblioteca")
public class LivroController {

    @Autowired 
    private LivroCrudServico meuServico; //Injeção de depedência de MeuServico => meuServico podendo utilizar as funções;


    //Método de cadastro de um livro
    @PostMapping("/livro")
    public ResponseEntity<LivroEntidade> cadastrarLivro(@RequestBody LivroEntidade livro) {
        LivroEntidade livroSalvo = meuServico.criarLivro(livro); //livroSalvo tem o papel de armazenar o objeto Entidade retornado pela func criarLivro()
        return ResponseEntity.ok().body(livroSalvo);

    }


    //Método de buscar um livro por ID
    @GetMapping("/livro/{idLivro}")
    public ResponseEntity<Optional<LivroEntidade>> buscaLivroPorId(@PathVariable("idLivro") Long idLivro) {
        Optional<LivroEntidade> livro = meuServico.buscarLivroPorId(idLivro);
        if (livro.isPresent()) 
            return ResponseEntity.ok().body(livro);

        return ResponseEntity.notFound().build();    
    }
   

    //Buscar de um Livro por titulo 
     @GetMapping("/livro/titulo/{titulo}")
    public ResponseEntity<List<LivroEntidade>> buscarLivroPorTitulo(@PathVariable("titulo") String titulo) {
        List<LivroEntidade> livros = meuServico.buscarLivroPorTitulo(titulo);
        if (!livros.isEmpty())
            return ResponseEntity.ok().body(livros);
        else
            return ResponseEntity.ok().body(Collections.emptyList());
        }
    
    //Método para alterar um livro existente
    @PutMapping("/livro/{idLivro}")
    public ResponseEntity<LivroEntidade> atualizarLivro(@PathVariable("idLivro") Long idLivro, @RequestBody LivroEntidade livro) {
        livro.setId(idLivro);//Define o ID do livro com base na URL
        LivroEntidade livroAtualizado = meuServico.atualizaLivro(livro);
        return ResponseEntity.ok(livroAtualizado);
    }


    //Método para exclusão de livro existente
    @DeleteMapping("/livro/{idLivro}")
    public ResponseEntity<Void> excluirLivro(@PathVariable("idLivro") Long idLivro) {
        meuServico.deletarLivro(idLivro);
        return ResponseEntity.noContent().build();
    }


    //Método para listar todos os livros
    @GetMapping("/livros")
    public ResponseEntity<List<LivroEntidade>> listarLivros() {
        List<LivroEntidade> livros = meuServico.listarLivros();
        return ResponseEntity.ok(livros);
    }


}  
//Vies de Teste:
//Listar todos os Livros = localhost:8080/biblioteca/livros  GET
//Criação de livro =  localhost:8080/biblioteca/livro POST
//Exclusão, atulização ou busca de livro especifico por ID = localhost:8080/biblioteca/livro/{id} DELETE PUT e GET
//Buscade livro por titulo: localhost:8080/biblioteca/livro/titulo/{titulo}