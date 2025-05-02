package com.example.practice_spring.ex3;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content, List<String> comments) {
        this.title = title;
        this.content = content;
        this.comments.addAll(comments.stream().map(o-> new Comment(o,this)).toList());

    }

    public void deleteComments(List<Integer> commentIds) {
        Set<Integer> idsToDelete = new HashSet<>(commentIds);
        comments.removeIf(comment -> idsToDelete.contains(comment.getId()));
    }
}
