package demooject;

import java.util.List;

/**
 * 创作应约的个人或团队
 */
public class Artist {
    private String name;//艺术家名字

    private List<Artist> members;//乐队成员,可为空

    private String origin;//来自

    public List<Artist> getMembers() {
        return members;
    }

    public void setMembers(List<Artist> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
