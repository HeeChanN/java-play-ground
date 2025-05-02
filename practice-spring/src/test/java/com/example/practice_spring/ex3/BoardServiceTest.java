package com.example.practice_spring.ex3;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@SuppressWarnings("NonAsciiCharacters")
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    TransactionTemplate tx;

    @Test
    @Transactional
    void cascade_persist_테스트(){
        //given
        int boardId = boardService.create(
                "test title",
                "test content",
                List.of("A", "B", "C")
        );

        //then
        System.out.println("----------------------------------------------");
        Optional<Board> board = boardRepository.findById(boardId);
        board.ifPresent(o->{
            o.getComments().forEach(System.out::println);
        });
    }

    @Test
    void orphanRemoval_삭제_테스트() {

        int boardId = tx.execute(status -> boardService.create("t","c",List.of("A","B","C")));

        tx.executeWithoutResult(transactionStatus -> {
            System.out.println("두번째 트랜잭션 시작");
            Board board = boardRepository.findById(boardId).orElseThrow();
            List<Integer> ids = board.getComments().stream()
                    .filter(c -> !"B".equals(c.getContent()))
                    .map(Comment::getId)
                    .toList();
            boardService.deleteComment(boardId, ids);
            System.out.println();
        });

        tx.executeWithoutResult(status -> {
            System.out.println("세번째 트랜잭션 시작");
            Board b = boardRepository.findById(boardId).orElseThrow();
            assertThat(b.getComments()).hasSize(1);
        });
    }

    @Test
    void Comment_100건_한번에_삭제_테스트(){
        int boardId = tx.execute(status -> {
            List<String> comments = IntStream.rangeClosed(1, 100)
                    .mapToObj(i -> "comment-" + i)
                    .toList();

            return boardService.create(
                    "batch test title",
                    "batch test content",
                    comments
            );
        });

        tx.executeWithoutResult(transactionStatus -> {
            System.out.println("두번째 트랜잭션 시작");
            List<Integer> allCommentIds = boardRepository.findById(boardId).orElseThrow()
                    .getComments().stream()
                    .map(Comment::getId)
                    .toList();

            boardService.deleteComment(boardId, allCommentIds);
        });

        tx.executeWithoutResult(status -> {
            Board reloaded = boardRepository.findById(boardId).orElseThrow();
            assertThat(reloaded.getComments()).isEmpty();
        });
    }
}
