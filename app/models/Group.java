package models;

import io.ebean.Finder;
import io.ebean.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_group")
public class Group extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String description;

    @Column(name="group_code")
    private String groupCode;

    @Column(name="group_name")
    private String groupName;

    //bi-directional many-to-many association to Menu
    @ManyToMany
    @JoinTable(
            name="group_menu"
            , joinColumns={
            @JoinColumn(name="group_id")
    }
            , inverseJoinColumns={
            @JoinColumn(name="menu_id")
    }
    )
    private List<Menu> menus;

    public static final Finder<Long, Group> find = new Finder(Group.class);

    public Group() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public static Group findById(Long groupId) {
        return find.query().where()
                .eq("id",
                        groupId)
                .findUnique();
    }
}