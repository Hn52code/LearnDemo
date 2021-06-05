package com.zhn.demo.baseweb.servlet.chat;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Group {

    private UUID id;
    private String name;
    private Set<User> users;

    public Group(String name, User user) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.users = new HashSet<>();
        this.join(user);
    }

    /* 群添加用户 */
    public void join(User user) {
        user.join(this);
        this.users.add(user);
    }

    /* 群剔除用户 */
    public boolean exit(User user) {
        user.exit(this);
        this.users.remove(user);
        /* 判断群内用户是否为0 */
        return this.users.size() == 0;
    }

    /* 群清空用户 */
    public void clear() {
        if (users.isEmpty()) return;
        for (User user : users) {
            user.exit(this);
        }
        this.users.clear();
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public int getCount() {
        return users.size();
    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (!(object instanceof Group)) return false;
//        Group group = (Group) object;
//        return Objects.equals(getId(), group.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId());
//    }
}
