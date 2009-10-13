package greendale;

public interface MessageListener<Msg> {
    void onMessage(Msg daMessageMatcher);
}
