package Entities;

public class Like_Dislike {

    private int userlike;
    private int userliked;
    private int likedislike;

    public Like_Dislike(int userlike, int userliked, int likedislike) {
        this.userlike = userlike;
        this.userliked = userliked;
        this.likedislike = likedislike;
    }

    public int getUserlike() {
        return userlike;
    }

    public void setUserlike(int userlike) {
        this.userlike = userlike;
    }

    public int getUserliked() {
        return userliked;
    }

    public void setUserliked(int userliked) {
        this.userliked = userliked;
    }

    public int getLikedislike() {
        return likedislike;
    }

    public void setLikedislike(int likedislike) {
        this.likedislike = likedislike;
    }
}
