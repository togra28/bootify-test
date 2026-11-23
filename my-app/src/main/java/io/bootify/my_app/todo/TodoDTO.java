package io.bootify.my_app.todo;

import jakarta.validation.constraints.Size;


public class TodoDTO {

    private Long id;

    @Size(max = 255)
    private String todo;

    @Size(max = 255)
    private String bemerkung;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(final String todo) {
        this.todo = todo;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(final String bemerkung) {
        this.bemerkung = bemerkung;
    }

}
