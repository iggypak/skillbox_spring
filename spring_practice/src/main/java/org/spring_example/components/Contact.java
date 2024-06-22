package org.spring_example.components;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Contact {

    private String name;


    private String number;

    private String mail;

    public Contact(){

    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s", name, number, mail);
    }
}
