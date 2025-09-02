package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;

import dev.paula.api_helpdesk_software.builder.RequestEntityBuilder;
import dev.paula.api_helpdesk_software.topic.TopicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "requests")
public class RequestEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateRequest;
    private String description;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;
    

    public RequestEntity() {
    }

    public RequestEntity(Long id, String name, LocalDate dateRequest, TopicEntity topic, String description){
        this.id = id;
        this.name = name;
        this.dateRequest = dateRequest;
        this.topic = topic;
        this.description =  description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static RequestEntityBuilder builder(){
        return new RequestEntityBuilder();
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

}
