package org.djna;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.djna.home.MqttConfig;
import org.djna.home.ThermostatReading;
import org.eclipse.paho.client.mqttv3.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Monitor {

    private static final String BROKER = MqttConfig.broker;
    private static final String TOPIC = MqttConfig.topicRoot + "/#";

    public static void main(String[] args) {
        try {
            MqttClient client = new MqttClient(BROKER, MqttClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            // TODO vary other connection options
            options.setCleanSession(true);

            // Create a Gson object
            Gson gson = new Gson();

            // Define the type of the Map
            Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
            // Define type for a payload object
            //Type readingType = new TypeToken<ThermostatReading>() {}.getType();

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
                    System.out.println("Retained: " + message.isRetained());
                    System.out.println("Message ID: " + message.getId());
                    String payloadString = new String(message.getPayload());
                    System.out.println("Message: " + payloadString );

                    // Parse the JSON string into a HashMap
                    Map<String, Object> resultMap = gson.fromJson( payloadString, mapType);

                    // Print the parsed HashMap
                    for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }

                    try {
                       ThermostatReading reading = gson.fromJson( payloadString, ThermostatReading.class);
                       System.out.println("Read : " + reading);
                    } catch( Throwable t){
                        System.out.println("Problem : " + t);
                    }

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
