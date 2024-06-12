package org.spring_example.components;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
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
