import java.util.ArrayList;

public class UsuarioTwitter {
    private String id;
    private String screenName;
    private ArrayList<String> tags;
    private String avatar;
    private Long followersCount;
    private Long friendsCount;
    private String lang;
    private Long lastSeen;
    private String tweetId;
    private ArrayList<String> friends;

    public UsuarioTwitter() {
        this.id = "";
        this.screenName = "";
        this.tags = new ArrayList<>();
        this.avatar = "";
        this.followersCount = 0L;
        this.friendsCount = 0L;
        this.lang = "";
        this.lastSeen = 0L;
        this.tweetId = "";
        this.friends = new ArrayList<>();
    }

    public UsuarioTwitter(String id, String screenName, ArrayList<String> tags, String avatar,
                          Long followersCount, Long friendsCount, String lang, Long lastSeen,
                          String tweetId, ArrayList<String> friends) {
        this.id = id;
        this.screenName = screenName;
        this.tags = tags;
        this.avatar = avatar;
        this.followersCount = followersCount;
        this.friendsCount = friendsCount;
        this.lang = lang;
        this.lastSeen = lastSeen;
        this.tweetId = tweetId;
        this.friends = friends;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getAvatar() {
        return avatar;
    }

    public Long getFollowersCount() {
        return followersCount;
    }

    public Long getFriendsCount() {
        return friendsCount;
    }

    public String getLang() {
        return lang;
    }

    public Long getLastSeen() {
        return lastSeen;
    }

    public String getTweetId() {
        return tweetId;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    public void setFriendsCount(Long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "UsuarioTwitter{" +
                "id='" + id + '\'' +
                ", screenName='" + screenName + '\'' +
                ", tags=" + tags +
                ", avatar='" + avatar + '\'' +
                ", followersCount=" + followersCount +
                ", friendsCount=" + friendsCount +
                ", lang='" + lang + '\'' +
                ", lastSeen=" + lastSeen +
                ", tweetId='" + tweetId + '\'' +
                ", friends=" + friends +
                '}';
    }
}

