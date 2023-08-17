package model;

public class PostDTO {

    private  int userId;
    private  int id;
    private  String title;
    private  String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public PostDTO() {
    }

    private PostDTO(PostBuilder postBuilder) {
        this.userId = postBuilder.userId;
        this.id = postBuilder.id;
        this.title = postBuilder.title;
        this.body = postBuilder.body;
    }


    public static class PostBuilder{
        int userId;
        int id;
        String title;
        String body;

        public PostBuilder setUserId(int userId){
            this.userId = userId;
            return this;
        }

        public PostBuilder setId(int id){
            this.id = id;
            return this;
        }

        public PostBuilder setTitle(String title){
            this.title=title;
            return this;
        }

        public PostBuilder setBody(String body){
            this.body = body;
            return this;
        }

        public PostDTO build(){
            return new PostDTO(this);
        }
    }
}
