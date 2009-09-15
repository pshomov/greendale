import org.junit.Test;
import static org.mockito.Mockito.*;

public class SubscribeForMessageTest {

    @Test
    public void should_be_able_to_subscribe_to_message_using_message_class(){
        PubSubHub hub = new PubSubHub();
        MessageListener<DaMessage> subscriber = null;
        hub.subscribeFor(DaMessage.class, subscriber);
    }

    @Test
    public void should_be_able_to_send_a_message_to_the_hub_using_message_class(){
        PubSubHub hub = new PubSubHub();
        hub.send(new Object());
    }

    @Test
    public void should_be_able_to_send_a_message_to_the_hub_and_the_subscriber_should_receive_it(){
        PubSubHub hub = new PubSubHub();
        MessageListener<DaMessage> subscriber = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, subscriber);
        hub.send(new DaMessage());
        verify(subscriber).onMessage(isA(DaMessage.class));
    }

    @Test
    public void should_be_able_to_send_a_message_to_the_hub_and_all_subscribers_should_receive_it(){
        PubSubHub hub = new PubSubHub();
        MessageListener<DaMessage> subscriber1 = mock(MessageListener.class);
        MessageListener<DaMessage> subscriber2 = mock(MessageListener.class);
        MessageListener<DaMessage> subscriber3 = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, subscriber1);
        hub.subscribeFor(DaMessage.class, subscriber2);
        hub.subscribeFor(DaMessage.class, subscriber3);
        hub.send(new DaMessage());
        verify(subscriber1).onMessage(isA(DaMessage.class));
        verify(subscriber2).onMessage(isA(DaMessage.class));
        verify(subscriber3).onMessage(isA(DaMessage.class));
    }
}

