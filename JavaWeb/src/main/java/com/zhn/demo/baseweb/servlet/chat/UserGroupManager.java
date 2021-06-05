package com.zhn.demo.baseweb.servlet.chat;

import javax.websocket.Session;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserGroupManager {

    private static Map<Session, User> userMap = new ConcurrentHashMap<>();
    private static Map<String, Group> groupMap = new ConcurrentHashMap<>();

    /* 创建用户 */
    public User create(String userName, Session session) {
        User user = new User(userName, session);
        userMap.put(session, user);
        return user;
    }

    /* 添加用户 */
    public void addUser(String groupId, Session session) {
        Group group = groupMap.get(groupId);
        if (Objects.nonNull(group))
            group.join(userMap.get(session));
    }

    /* 删除群用户 */
    public void removeUser(String groupId, Session session) {
        Group group = groupMap.get(groupId);
        if (Objects.nonNull(group)) {
            boolean isNull = group.exit(userMap.get(session));
            if (isNull) groupMap.remove(groupId);
        }
    }

    /* 创建群 */
    public Group createGroup(String groupName, Session session) {
        User user = userMap.get(session);
        Group group = new Group(groupName, user);
        groupMap.put(group.getId(), group);
        return group;
    }

    /* 删除群 */
    public void deleteGroup(String groupId) {
        Group group = groupMap.get(groupId);
        if (group != null) {
            group.clear();
            groupMap.remove(groupId);
        }
    }

    /* 获取群的所有用户 */
    public Set<User> getGroupUser(String groupId) {
        return groupMap.get(groupId).getUsers();
    }

    /* 获取用户的所有群 */
    public Set<Group> getUserGroup(Session session) {
        return userMap.get(session).getGroups();
    }

}
