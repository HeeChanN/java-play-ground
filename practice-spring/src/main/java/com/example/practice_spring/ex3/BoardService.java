package com.example.practice_spring.ex3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public int create(String title, String content, List<String> comments){
        Board board = new Board(title,content,comments);
        board = boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void deleteComment(Integer boardId,List<Integer> commentIds){
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new RuntimeException("error"));
        board.deleteComments(commentIds);
    }
}
