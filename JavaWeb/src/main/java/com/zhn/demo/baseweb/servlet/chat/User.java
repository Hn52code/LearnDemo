package com.zhn.demo.baseweb.servlet.chat;

import javax.websocket.Session;
import java.util.*;

public class User {

    /* 用户名 */
    private String name;
    /* 用户通讯session */
    private Session session;
    /* 用户所有群集合 */
    private Set<Group> groups;

    public User(String name, Session session) {
        this.name = name;
        this.session = session;
        this.groups = new HashSet<>();
    }

    /* 加入群 */
    public void join(Group group) {
        groups.add(group);
    }

    /* 退出群 */
    public void exit(Group group) {
        groups.remove(group);
    }

    /* 退出所有群 */
    public void clearGroup() {
        if (groups.isEmpty()) return;
        for (Group group : groups) {
            group.exit(this);
        }
    }

    public String getName() {
        return name;
    }

    public Session getSession() {
        return session;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public int getCount() {
        return groups.size();
    }

//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (!(object instanceof User)) return false;
//        User user = (User) object;
//        return Objects.equals(getName(), user.getName()) &&
//                Objects.equals(getSession(), user.getSession());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), getSession());
//    }

}
