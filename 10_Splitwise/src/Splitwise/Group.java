package Splitwise;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Group {
    private final String groupId;
    private  final String name;
    private final List<User> members;
    private final Date createdAt;

    public Group(String name, List<User> members) {
        this.name = name;
        this.members = members;
        this.createdAt = new Date();
        this.groupId = UUID.randomUUID().toString();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }
}
