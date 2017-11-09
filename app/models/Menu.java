package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String name;

    private Long order;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu menu;
    @OneToMany(mappedBy = "menu")
    private List<Menu> menuList;

    private String path;

    //bi-directional many-to-many association to Group
    @ManyToMany(mappedBy = "menus")
    private List<Group> groups;

    public static final Finder<Long, Menu> find = new Finder(Menu.class);

    public Menu() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrder() {
        return this.order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public static Menu findById(Long menuId) {
        return find.query().where()
                .eq("id",
                        menuId)
                .findUnique();
    }

    public static List<Menu> findByParentId(Long parentId) {
        return find.query().where()
                .eq("parent_id",
                        parentId)
                .findList();
    }
}