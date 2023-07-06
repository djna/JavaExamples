package org.djna;
import org.djna.home.MqttConfig;
import org.eclipse.paho.client.mqttv3.*;

public class Monitor {

    private static final String BROKER = MqttConfig.broker;
    private static final String TOPIC = MqttConfig.topicRoot + "/#";

    public static void main(String[] args) {
        try {
            MqttClient client = new MqttClient(BROKER, MqttClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            // TODO vary other connection options
            options.setCleanSession(true);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message:");
                    System.out.println("Topic: " + topic);
                    System.out.println("QoS: " + message.getQos());
                    System.out.println("Retained: " + message.isRetained();
                    System.out.println("Message ID: " + message.getId());
                    System.out.println("Message: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used in this example
                }
            });

            client.connect(options);
            client.subscribe(TOPIC);

            System.out.println("Subscribed to topic: " + TOPIC);

            // Keep the program running until user interrupts it
            Thread.sleep(Long.MAX_VALUE);

            client.disconnect();
            System.out.println("Disconnected");
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
