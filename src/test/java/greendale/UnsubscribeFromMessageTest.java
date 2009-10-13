package greendale;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class UnsubscribeFromMessageTest {
    
    @Test
    public void should_allow_to_unsubscribe_from_a_message_it_has_previously_subscribed() {
        PubSubHub hub = new PubSubHub();
        final MessageListener<DaMessage> messageListener = mock(MessageListener.class);
        hub.subscribeFor(DaMessage.class, messageListener);
        hub.unsubscribeFrom(DaMessage.class, messageListener);
        hub.send(new DaMessage());
        verify(messageListener, never()).onMessage((DaMessage) anyObject());
    }

    @Test
    public void should_throw_an_exception_when_trying_to_unsubscribe_from_a_message_it_has_never_subscribed_for() {
        PubSubHub hub = new PubSubHub();
        final MessageListener<DaMessage> messageListener = mock(MessageListener.class);
        try {
            hub.unsubscribeFrom(DaMessage.class, messageListener);
            fail("should never get here");
        } catch (Exception e) {
        }
    }
}
