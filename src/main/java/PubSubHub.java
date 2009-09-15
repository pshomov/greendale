import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PubSubHub {
    private Map<Class, Set<MessageListener>> messageSubscriptions = new HashMap<Class, Set<MessageListener>>();

    public <T> void subscribeFor(Class<T> messageClass, MessageListener<T> subscriber) {
        Set<MessageListener> listeners = messageSubscriptions.get(messageClass);
        if (listeners == null) {
            listeners = new HashSet<MessageListener>();
            messageSubscriptions.put(messageClass, listeners);
        }
        listeners.add(subscriber);
    }

    public void send(Object msg) {
        final Set<MessageListener> messageListenerSet = messageSubscriptions.get(msg.getClass());
        if (messageListenerSet != null) {
            for (MessageListener messageListener : messageListenerSet) {
                messageListener.onMessage(msg);
            }
        }
    }
}
