package com.exam.resource;

import com.exam.service.MessageService;
import com.exam.service.dto.MessageDTO;
import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/messages")
    public class MessageResource {

        private final MessageService messageService;

        public MessageResource(MessageService messageService) {
            this.messageService = messageService;
        }

        @GetMapping("/")
        public ResponseEntity<List<MessageDTO>> findAll() {
            return ResponseEntity.ok(messageService.findAll());
        }


        @GetMapping("/{id}")
        public ResponseEntity<MessageDTO> findById(@PathVariable Long id) {
            return ResponseEntity.ok(messageService.findOne(id));
        }

        @PostMapping("/")
        public ResponseEntity<MessageDTO> save(@RequestBody MessageDTO messageDTO) {
            return ResponseEntity.ok(messageService.save(messageDTO));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            messageService.delete(id);
            return ResponseEntity.ok().build();
        }

    }

