package br.com.washington.application_events.services;

public interface SMSService {

    void send(String phone, String message);
}
