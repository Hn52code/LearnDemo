package com.zhn.demo.baseweb.servlet.chat;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserGroupManager {

    private Map<User, List<Group>> userGroupsMap = new ConcurrentHashMap<>();
    private Map<Group, List<User>> groupUsersMap = new ConcurrentHashMap<>();

    public Group createGroup(String name) {
        Group group = new Group(name);
        groupUsersMap.putIfAbsent(group, new ArrayList<>());
        return group;
    }

    public User createUser(String name, Session session) {
        User user = new User(name, session);
        userGroupsMap.putIfAbsent(user, new ArrayList<>());
        return user;
    }

}
