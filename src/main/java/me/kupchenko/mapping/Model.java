package me.kupchenko.mapping;

import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "models")
public class Model {
    private Long id;
    private String name;

    @OrderBy("name ASC")
    private List<Obj> items;
}

class Obj {
    private String name;
}