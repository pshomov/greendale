package greendale;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class SubscribeForMessageTest {
    private PubSubHub hub;

    @Test
    public void should_be_able_to_subscribe_to_message_using_message_class() {
        MessageListener<DaMessage> listener = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, listener);
    }

    @Test
    public void should_be_able_to_send_a_message_to_the_hub_using_message_class() {
        hub.send(new Object());
    }

    @Test
    public void should_be_able_to_send_a_message_to_the_hub_and_the_subscriber_should_receive_it() {
        MessageListener<DaMessage> listener = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, listener);
        hub.send(new DaMessage());
        verify(listener).onMessage(isA(DaMessage.class));
    }

    @Test
    public void should_be_able_to_send_a_message_to_the_hub_and_all_subscribers_should_receive_it() {
        MessageListener<DaMessage> listener1 = mock(MessageListener.class);
        MessageListener<DaMessage> listener2 = mock(MessageListener.class);
        MessageListener<DaMessage> listener3 = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, listener1);
        hub.subscribeFor(DaMessage.class, listener2);
        hub.subscribeFor(DaMessage.class, listener3);
        hub.send(new DaMessage());
        verify(listener1).onMessage(isA(DaMessage.class));
        verify(listener2).onMessage(isA(DaMessage.class));
        verify(listener3).onMessage(isA(DaMessage.class));
    }

    @Test
    public void should_allow_listeners_to_subscribe_only_once_for_a_certain_message() {
        MessageListener<DaMessage> listener = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, listener);
        try {
            hub.subscribeFor(DaMessage.class, listener);
            fail();
        } catch (SubscribtionException e) {
        }
    }

    @Test
    public void should_not_allow_null_as_message_listiners() {
        try {
            hub.subscribeFor(DaMessage.class, null);
            fail();
        } catch (SubscribtionException e) {
        }
    }

    @Test
    public void should_not_allow_null_as_message_to_subscribe_to() {
        try {
            hub.subscribeFor(null, mock(MessageListener.class));
            fail();
        } catch (SubscribtionException e) {
        }
    }

    @Before
    public void setUp() throws Exception {
        hub = new PubSubHub();
    }
}

