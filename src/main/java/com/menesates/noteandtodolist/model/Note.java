package com.menesates.noteandtodolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "T_NOTE")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteAndTodoSeqGen")
    @SequenceGenerator(name = "noteAndTodoSeqGen", sequenceName = "NOTEANDTODO_SEQUENCE")
    private Long id;
    @NotEmpty
    @Column(name = "HEADER")
    private String header;
    @Column(name = "BODY")
    private String body;
    @NotEmpty
    @Column(name = "USERNAME")
    private String username;
    @NotEmpty
    @Column(name = "CATEGORY")
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", username='" + username + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
