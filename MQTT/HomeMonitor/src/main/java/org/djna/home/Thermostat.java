package org.djna.home;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.google.gson.Gson;

import java.util.Date;

public class Thermostat {

    public static void main(String[] args) {

        String location = "hall";
        // TODO topic can be changed for some experiments
        String topic        = MqttConfig.topicRoot + "/" + location;


        String broker       = MqttConfig.broker;
        String clientId     = "Thermostat";

        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            ThermostatReading oneReading = new ThermostatReading(location, new Date().getTime(), 29);
            Gson gson = new Gson();
            String jsonPayload = gson.toJson(oneReading);
            System.out.println("Publishing message: "+jsonPayload);

            MqttMessage message = new MqttMessage(jsonPayload.getBytes());

            // TODO follow the articles to investigate changing QOS and retained ...
            int qos             = 2;
            // message.setQos(qos);
            // message.setRetained(true);

            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("exception "+me);
            me.printStackTrace();
        }
    }
}

