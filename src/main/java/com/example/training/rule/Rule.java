package com.example.training.rule;

public class Rule {
    private String id;
    private String name ;

    public Rule(String id) {
        this.id = id;
    }

    public Rule() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
