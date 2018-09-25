package com.exam.service;

import com.exam.model.Message;
import com.exam.repository.MessageRepository;
import com.exam.service.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;


    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public MessageDTO findOne(Long id) {
        return messageRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> findAll() {
        return messageRepository.findAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    public MessageDTO save(MessageDTO messageDTO) {
        Message entity = toEntity(messageDTO);
        return toDto(messageRepository.save(entity));
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    public Message toEntity(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setOwner(messageDTO.getOwner());
        message.setContent(messageDTO.getContent());
        return message;
    }

    public MessageDTO toDto(Message message) {
        if (message == null) {
            return  null;
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setOwner(message.getOwner());
        messageDTO.setContent(message.getContent());

        return messageDTO;
    }

    public List<MessageDTO> toDto(List<Message> messages) {
        return messages.stream().map(this::toDto).collect(Collectors.toList());
    }

}